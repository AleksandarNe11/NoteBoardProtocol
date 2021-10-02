import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class BoardClient {
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

        // Reads input from user in command line
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye."))
                break;

            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
            }
        }
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
