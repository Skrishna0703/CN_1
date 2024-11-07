
// TCP Server
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Server is listening on port 6789...");

            Socket connectionSocket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientMessage = inFromClient.readLine();
            System.out.println("Received from client: " + clientMessage);

            String serverMessage = "Hello from Server!";
            outToClient.writeBytes(serverMessage + '\n');

            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
