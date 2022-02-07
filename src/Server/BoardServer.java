import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class BoardServer {
    public static void main(String[] args) throws IOException {
        // Parse program arguments into variables to instantiate socket on port
        // and create Board object
        int argCounter = 0;
        int port = 4444, width = 0, height = 0;
        ArrayList<String> colours = new ArrayList<>();
        for (String output: args) {
            switch (argCounter)  {
                case 0:
                    port = Integer.parseInt(args[argCounter]);
                    break;
                case 1:
                    width = Integer.parseInt(args[argCounter]);
                    break;
                case 2:
                    height = Integer.parseInt(args[argCounter]);
                    break;
                default:
                    colours.add(args[argCounter]);
            }
            argCounter++;
        }
        // Instantiate board object from commandline args
        Board board = new Board(width, height, colours);

        // Try to listen on port given from commandline args
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            System.out.printf("Server is Live Listening on port %d. \n", port);
        } catch (IOException e) {
            System.err.printf("Could not listen on port: %d.", port);
            System.exit(1);
        }

        // Process requests in an infinite loop
        while (true) {
            System.out.printf("Listening for Client Socket %d. \n", port);
            // Listen for a TCP connection request.
            Socket clientSocket = serverSocket.accept();
            // Construct an object to process the HTTP request message.
            BoardRequest request = new BoardRequest(clientSocket, board);
            // Create a new thread to process the request.
            Thread thread = new Thread(request);
            // Start the thread.
            thread.start();
        }

    }
}
