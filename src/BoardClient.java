import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class BoardClient {
    final static String CRLF = "\r\n";
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;


//        BoardClient(int portNumber){
            try {
                socket = new Socket("127.0.0.1", 4444);
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
            if (fromServer.equals("Bye."))
                break;

            // takes command line input, parses input to be one of the method types, sends method evaluation result to server through out.println(request)
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                System.out.println("======================================");
                String request = inputParser(fromUser);
                System.out.println(request);
                System.out.println("======================================");
                out.println(request);
            }
        }
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }

    public static String inputParser(String fromUser) {
        String request;
        switch(fromUser) {
            case "POST":
                request = POST();
                break;
            case "GET":
                request = GET();
                break;
            case "PIN":
                request = PIN();
                break;
            case "UNPIN":
                request = UNPIN();
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
                request = DISCONNECT();
        }
        return request;
    }

    public static String POST() {
        String method = "POST ";
        String parameters = "<x coordinate> <y coordinate> <width> <height> <colour> <message> ";

        String header = method.concat(parameters).concat(CRLF);
//        String body = "<x-coordinate> <y-coordinate> <width> <height> <colour> <message>";

        return header;
    }

    public static String GET() {
        String method = "GET ";
        String parameters = "";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String GET(String colour, String contains, String refersTo) {
        String method = "GET ";
        String parameters = "<colour> <contains> <refersTo>";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }
    public static String PIN() {
        String method = "PIN ";
        String parameters = "<x coordinate> <y coordinate>";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String UNPIN() {
        String method = "UNPIN ";
        String parameters = "<x coordinate> <y coordinate>";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String CLEAR() {
        String method = "CLEAR ";
        String parameters = "";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String SHAKE() {
        String method = "SHAKE ";
        String parameters = "";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String CONNECT() {
        String method = "CONNECT ";
        String parameters = "";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }

    public static String DISCONNECT() {
        String method = "DISCONNECT ";
        String parameters = "";

        String header = method.concat(parameters).concat(CRLF);

        return header;
    }
}
