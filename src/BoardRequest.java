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
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        // Define a reader to enable request parsing
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        // Parse Header line to get method and call invokeMethod passing Tokenizer for next phase
        String headerLine = br.readLine();
        StringTokenizer tokens = new StringTokenizer(headerLine);
        String method = tokens.nextToken();
        invokeMethod(method, tokens);

    }

    private void invokeMethod(String method, StringTokenizer tokens) {
        try {
            switch(method) {
                case "POST":
                    POST(tokens);
                case "GET":
                    GET(tokens);
                case "PIN":
                    PIN();
                case "UNPIN":
                    UNPIN();
                case "CLEAR":
                    CLEAR();
                case "SHAKE":
                    SHAKE();
                case "CONNECT":
                    CONNECT();
                case "DISCONNECT":
                    DISCONNECT();

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
        } else {
            throw new InvalidRequestParametersException();
        }
    }

    private void GET(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens == 0) {
            while (tokens.hasMoreTokens()) {
                System.out.println("GET request with no request parameters");
            }
        } else if (numTokens == 3) {
            System.out.println("GET request with 3 request parameters");
        } else {
            throw new InvalidRequestParametersException();
        }
    }
    private void PIN() {

    }

    private void UNPIN() {

    }

    private void CLEAR() {

    }

    private void SHAKE() {

    }

    private void CONNECT() {

    }

    private void DISCONNECT() {

    }
}

class InvalidRequestParametersException extends Exception {}
