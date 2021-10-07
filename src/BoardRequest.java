import java.io.* ;
import java.net.* ;
import java.util.* ;

public class BoardRequest implements Runnable {
    Socket socket;
    final static String CRLF = "\r\n";
    final static String DELIM = "!.!";
    final Board board;

    public BoardRequest(Socket socket, Board board) {
        this.socket = socket;
        this.board = board;
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
        // ** Change this to a String parsing stream **
        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
        PrintWriter textOut = new PrintWriter(socket.getOutputStream(), true);

        //Send Text to Client
        textOut.println("Connection Established \n");

        // Define a reader to enable request parsing
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Constantly Loop listening for input from the client
        String headerLine;
        while ((headerLine = br.readLine()) != null) {
            try {
                // Parse method type and pass string token and method type to invokeMethod for further behaviour
                StringTokenizer tokens = new StringTokenizer(headerLine);
                String method = tokens.nextToken();
                System.out.println(method);
                String response = invokeMethod(method, tokens);
                // Send some sort of response to client
                textOut.println(response);

                // if method
                if (method.equalsIgnoreCase("DISCONNECT")) break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Close socket connection to client
        objectOut.close();
        is.close();
        socket.close();
    }

    private String invokeMethod(String method, StringTokenizer tokens) {
        String response = "";
        try {
            switch(method) {
                case "POST":
                    response = POST(tokens);
                    break;
                case "GET":
                    response = GET(tokens);
                    break;
                case "PIN":
                    response = PIN(tokens);
                    break;
                case "UNPIN":
                    response = UNPIN(tokens);
                    break;
                case "CLEAR":
                    response = CLEAR();
                    break;
                case "SHAKE":
                    response = SHAKE();
                    break;
                case "CONNECT":
                    response = CONNECT();
                    break;
                case "DISCONNECT":
                    response = DISCONNECT();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            return "300 Client Error";
        }

        return response;
    }

    private String POST(StringTokenizer tokens) throws InvalidRequestParametersException {
        int numTokens = tokens.countTokens();
        if (numTokens >= 6) {
            int xcoord, ycoord, width, height;
            String colour, message;
            xcoord = Integer.parseInt(tokens.nextToken());
            ycoord = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            colour = tokens.nextToken();
            message = tokens.nextToken();
            while (tokens.hasMoreTokens()) {
                message = message.concat(tokens.nextToken());
            }

            this.board.addNote(
                    new Note(xcoord, ycoord, width,
                            height, colour, message,
                            false)
            );

            return "200 Request OK";
        } else {
            throw new InvalidRequestParametersException();
        }
    }


    private String GET(StringTokenizer tokens) throws InvalidRequestParametersException {
        // Instantiates variables for use assuming proper token amount
        ArrayList<String> localNoteArray;
        String response = "201 Request Response \n";

        // Checks for proper token amount and calls respective getNotes method dependent
        // on number of request parameters storing it in localNoteArray
        int numTokens = tokens.countTokens();
        if (numTokens == 0) {
            localNoteArray = this.board.getNotes();
        } else if (numTokens >= 4) {
            String colour = tokens.nextToken();
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            String refersTo = tokens.nextToken();
            localNoteArray = this.board.getNotes(colour, x, y, refersTo);

        } else {
            throw new InvalidRequestParametersException();
        }

        // concatenates the notes into the body of the response
        for (String noteString: localNoteArray) {
            response = response.concat(noteString + DELIM);
        }

        return response;
    }
    private String PIN(StringTokenizer tokens) throws InvalidRequestParametersException {
        // Checks for proper token amount and calls pinNotes method if valid
        int numTokens = tokens.countTokens();
        if (numTokens == 2) {
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            this.board.pinNotes(x, y);
        } else {
            throw new InvalidRequestParametersException();
        }

        // returns the response header
        return "200 Request OK";
    }

    private String UNPIN(StringTokenizer tokens) throws InvalidRequestParametersException {
        // Checks for proper token amount and calls pinNotes method if valid
        int numTokens = tokens.countTokens();
        if (numTokens == 2) {
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            this.board.unpinNotes(x, y);
        } else {
            throw new InvalidRequestParametersException();
        }

        // returns the response header
        return "200 Request OK";
    }

    private String CLEAR() {
        System.out.println("CLEAR request - All notes cleared");
        this.board.clearNotes();
        return "200 Request OK";
    }

    private String SHAKE() {
        System.out.println("SHAKE request - All unpinned notes cleared");
        this.board.shakeNotes();
        return "200 Request OK";
    }

    private String CONNECT() {
        System.out.println("CONNECTION request - Sending Board parameters to client");
        String response = "100 continue \n";
        response = response.concat(this.board.getWidth() + DELIM + this.board.getHeight());

        for (String colour: this.board.getColours()) {
            response =  response.concat(DELIM + colour);
        }

        return response;
    }

    private String DISCONNECT() {
        System.out.println("Disconnecting now");
        return "200 Request OK";
    }
}

class InvalidRequestParametersException extends Exception {}
