import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class BoardClient {
    final static String CRLF = "\r\n";
    private PrintWriter out;
    private BufferedReader in;

    // Important string values
    private String fromServer;
    private String lastAction;
    private String responseStatus = "body";

    // Java Swing Objects references for modification
    private JTextArea textArea;
    JSpinner spinX;
    JSpinner spinY;
    JSpinner spinColour;

    public BoardClient(String IP, int port, JTextArea textFrame,
                       JSpinner spinX, JSpinner spinY, JSpinner spinColour) {
        textArea = textFrame;
        try {
            runClient(IP, port);
        } catch (Exception e){
            System.out.println(e);
        }

    }

    public void runClient(String IP, int port) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;


//        BoardClient(int portNumber){
            try {
                socket = new Socket(IP, port);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Don't know host");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection");
                System.exit(1);
            }
//        }

        // Reads input from user in command line for testing setup
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            processServerInput(fromServer);
            if (fromServer.equals("Bye."))
                break;


            // takes command line input, parses input to be one of the method types, sends method evaluation result to server through out.println(request)
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                System.out.println("======================================");
                String request = inputParser(fromUser);
                System.out.println("======================================");
                out.println(request);
            }
        }
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }

    public String inputParser(String fromUser) {
        String request = "DISCONNECT";
        switch(fromUser) {
            case "POST":
                request = POST(1, 1, 5, 5, "Blue", "Hello World");
                break;
            case "GET":
                request = GET("Blue", 1, 1, "Hello World");
                break;
            case "PIN":
                request = PIN(1, 1);
                break;
            case "UNPIN":
                request = UNPIN(1, 1);
                break;
            case "CLEAR":
                request = CLEAR();
                break;
            case "SHAKE":
                request = SHAKE();
                break;
            case "CONNECT":
                request = CONNECT();
                break;
            default:
                DISCONNECT();
        }
        return request;
    }

    public String POST(int x, int y, int width, int height, String colour, String message) {
        String method = "POST ";
        String parameters = x + " " + y + width + " " + height + " " + colour + " " + message;

//        String header = method.concat(parameters).concat(CRLF);
//        String body = "<x-coordinate> <y-coordinate> <width> <height> <colour> <message>";

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String GET() {
        String method = "GET ";
        String parameters = "";

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String GET(String colour, int x, int y, String refersTo) {
        String method = "GET ";
        String parameters = colour + " " + x + " " + y + " " + refersTo;

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }
    public String PIN(int x, int y) {
        String method = "PIN ";
        String parameters = x + " " + y;

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String UNPIN(int x, int y) {
        String method = "UNPIN ";
        String parameters = x + " " + y;

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String CLEAR() {
        String method = "CLEAR ";
        String parameters = "";


        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String SHAKE() {
        String method = "SHAKE ";
        String parameters = "";


        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public String CONNECT() {
        String method = "CONNECT ";
        String parameters = "";

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);
        return request;
    }

    public void DISCONNECT() {
        String method = "DISCONNECT ";
        String parameters = "";

        updateLastAction(method);

        String request = method.concat(parameters);
        transmitRequest(request);

        terminateConnection();
    }

    public void transmitRequest(String request) {
        out.println(request);
    }

    private void processServerInput(String response) {
        String[] response_array;
        if (responseStatus.equalsIgnoreCase("body")) {
            response_array = response.split(" ");
            switch (response_array[1]) {
                case "100":
                    responseStatus = "100";
                    break;
                case "200":
                    successfulAction();
                    break;
                case "201":
                    responseStatus = "201";
                    break;
                case "300":
                    unsuccessfulAction();
                    break;
            }
        } else {
            response_array = response.split("!.!");
            switch (responseStatus) {
                case "100":
                    connectionEstablished(response_array);
                    break;
                case "201":
                    showResponseBody(response_array);
                    break;
            }

            responseStatus = "body";

        }
    }

    /**
     * Sets the SpinX max value to Width of board
     * sets SpinY max value to height of board
     * Sets the possible values of colours to set of Colours
     * @param response_array - array of the delimited response body from a successful connection
     */
    private void connectionEstablished(String[] response_array) {
        // sets maximum value for X coordinate to value returned in server response
        spinX.setModel(new SpinnerNumberModel(-1, -1,
                Integer.parseInt(response_array[0]), 1));

        // sets maximum value for y coordinate to value returned in server response
        spinX.setModel(new SpinnerNumberModel(-1, -1,
                Integer.parseInt(response_array[1]), 1));

        // creates array of colours from server response and assigns the created array to the spinColour wheel
        String[] colours = Arrays.copyOfRange(response_array, 2, response_array.length);
        spinColour.setModel(new SpinnerListModel(colours));

        printOutput("Connected to server");
    }

    /**
     * Prints out response of GET request to textArea in GUI
     * @param response_array - body of response to GET request containing variable amount of items
     */
    private void showResponseBody(String[] response_array) {
        String output = "";
        for (String item: response_array) {
            output = output.concat(item + "\n");
        }

        printOutput(output);
    }

    private void successfulAction() {
        printOutput(lastAction + "Action Successful");
    }

    private void unsuccessfulAction() {
        printOutput(lastAction + "Action Unsuccessful");
    }

    public void updateLastAction(String action) {
        lastAction = action;
    }

    public void printOutput(String output) {
        textArea.setText(output);
    }

    public void terminateConnection() {
        fromServer = null;
    }
}

class InvalidInputException extends Exception {}