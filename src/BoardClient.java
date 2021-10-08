import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class BoardClient implements Runnable{
    final static String CRLF = "\r\n";
    private PrintWriter out;
    private BufferedReader in;

    // Important string values
    private String fromServer;
    private String lastAction;
    private String responseStatus = "body";
    private final String IP;
    private final int port;

    // Java Swing Objects references for modification
    private final JTextArea textArea;
    JSpinner spinX;
    JSpinner spinY;
    JSpinner GetSpinX;
    JSpinner GetSpinY;
    JSpinner spinColour;

    public BoardClient(String IP, int port, JTextArea textFrame,
                       JSpinner spinX, JSpinner spinY, JSpinner GetSpinX, JSpinner GetSpinY, JSpinner spinColour) {
        this.textArea = textFrame;
        this.IP = IP;
        this.port = port;
        this.spinX = spinX;
        this.spinY = spinY;
        this.GetSpinX = GetSpinX;
        this.GetSpinY = GetSpinY;
        this.spinColour = spinColour;

    }

    @Override
    public void run() {
        try {
            runClient();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void runClient() throws IOException{
        Socket socket = null;
        out = null;
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

        while (true) {
            if (in.ready()) {
                if ((fromServer = in.readLine()) != null) {
                    if (!fromServer.equals("")) {
                        System.out.println("Server: " + fromServer);
                        processServerInput(fromServer);
                    }
                    if (fromServer.equals("Bye."))
                        break;
                }
            }
        }
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }

    public String inputParser(String fromServer) {
        String request = "DISCONNECT";
        switch(fromServer) {
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
        String parameters = x + " " + y + " " + width + " " + height + " " + colour + " " + message;

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
        if (refersTo.equals(" ") || refersTo.equals("")) {
            refersTo = "null";
        }
        if (colour.equals(" ")) {
            colour = "null";
        }
        
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
        String[] body_array;
        String[] header_array = response.split(" ");
        if (header_array[0].equals("100") || header_array[0].equals("200")
                ||  header_array[0].equals("201") ||  header_array[0].equals("300"))
            responseStatus = "body";
        if (responseStatus.equalsIgnoreCase("body")) {
            switch (header_array[0]) {
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
            body_array = response.split("!.!");
            switch (responseStatus) {
                case "100":
                    connectionEstablished(body_array);
                    break;
                case "201":
                    showResponseBody(body_array);
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
        // sets maximum value for X coordinate to value returned in server response
        GetSpinX.setModel(new SpinnerNumberModel(-1, -1,
                Integer.parseInt(response_array[0]), 1));
        System.out.printf("Set SpinX bound to %d \n",  Integer.parseInt(response_array[0]));

        // sets maximum value for y coordinate to value returned in server response
        spinY.setModel(new SpinnerNumberModel(-1, -1,
                Integer.parseInt(response_array[1]), 1));
        // sets maximum value for y coordinate to value returned in server response
        GetSpinY.setModel(new SpinnerNumberModel(-1, -1,
                Integer.parseInt(response_array[1]), 1));
        System.out.printf("Set SpinY bound to %d \n",  Integer.parseInt(response_array[1]));

        // creates array of colours from server response and assigns the created array to the spinColour wheel
        String[] colours = Arrays.copyOfRange(response_array, 2, response_array.length + 1);
        colours[colours.length-1] = " ";
        System.out.println("Set spinColours to: \n" + Arrays.toString(colours));
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