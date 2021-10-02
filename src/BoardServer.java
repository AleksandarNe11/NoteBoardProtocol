import java.net.*;
import java.io.*;

public class BoardServer {
    public static void main(String[] args) throws IOException {
        // parse port value from command line args
        int port = Integer.parseInt(args[0]);

        // Try to listen on port input from IO
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
            BoardRequest request = new BoardRequest(clientSocket);
            // Create a new thread to process the request.
            Thread thread = new Thread(request);
            // Start the thread.
            thread.start();
        }

    }
}
