import java.io.* ;
import java.net.* ;
import java.util.* ;

public class BoardRequest implements Runnable {
    Socket socket;
    final static String CRLF = "\r\n";

    public BoardRequest(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void processRequest() throws IOException {
        // Instantiate the input and output streams
        InputStream is = socket.getInputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        PrintWriter textOut = new PrintWriter(socket.getOutputStream(), true);

        //Send Text to Client
        textOut.println("Connection Established to server. BoardRequest Running");

        // Define a reader to enable request parsing
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Constantly Loop listening for input from the client
        String headerLine;
        while ((headerLine = br.readLine()) != null) {
            // Parse method type and pass string token and method type to invokeMethod for further behaviour
            StringTokenizer tokens = new StringTokenizer(headerLine);
            String method = tokens.nextToken();
            System.out.println(method);
            invokeMethod(method, tokens);
            // Send some sort of response to client
            textOut.println("Please enter next method call");
        }

        // Close socket connection to client
        objectOut.close();
        is.close();
        socket.close();
    }

    private void invokeMethod(String method, StringTokenizer tokens) {
        try {
            switch(method) {
                case "POST":
                    POST(tokens);
                    break;
                case "GET":
                    GET(tokens);
                    break;
                case "PIN":
                    PIN(tokens);
                    break;
                case "UNPIN":
                    UNPIN(tokens);
                    break;
                case "CLEAR":
                    CLEAR();
                    break;
                case "SHAKE":
                    SHAKE();
                    break;
                case "CONNECT":
                    CONNECT();
                    break;
                case "DISCONNECT":
                    DISCONNECT();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void POST(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens == 5) {
            while (tokens.hasMoreTokens()) {
                System.out.println(tokens.nextToken());
            }
            System.out.println("======================================");
        } else {
            throw new InvalidRequestParametersException();
        }
    }

    private void GET(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens == 0) {
            System.out.println("GET request with no request parameters");
            System.out.println("======================================");
        } else if (numTokens == 3) {
            System.out.println("GET request with 3 request parameters");
            System.out.println("======================================");
        } else {
            throw new InvalidRequestParametersException();
        }
    }
    private void PIN(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens == 2) {
            System.out.println("PIN request to following coordinates");
            System.out.printf("x coordinate: %s\n", tokens.nextToken());
            System.out.printf("y coordinate: %s\n", tokens.nextToken());
            System.out.println("======================================");
        } else {
            throw new InvalidRequestParametersException();
        }
    }

    private void UNPIN(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens == 2) {
            System.out.println("UNPIN request to following coordinates");
            System.out.printf("x coordinate: %s\n", tokens.nextToken());
            System.out.printf("y coordinate: %s\n", tokens.nextToken());
            System.out.println("======================================");
        } else {
            throw new InvalidRequestParametersException();
        }
    }

    private void CLEAR() {
        System.out.println("CLEAR request - All notes cleared");
    }

    private void SHAKE() {
        System.out.println("SHAKE request - All unpinned notes cleared");
    }

    private void CONNECT() {
        System.out.println("I think this method is redundant");
    }

    private void DISCONNECT() {
        System.out.println("Disconnecting now");
    }
}

class InvalidRequestParametersException extends Exception {}
