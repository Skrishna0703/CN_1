import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        int port = 12345;
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for a connection...");

            socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage = in.readLine();
            System.out.println("Client: " + clientMessage);

            out.println("Hello from Server!");

            receiveFile(socket);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void receiveFile(Socket socket) throws IOException {
        // Receiving file
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        FileOutputStream fos = new FileOutputStream("received_file"); // Name the file to be saved

        byte[] buffer = new byte[4096];
        int bytesRead;

        System.out.println("Receiving file...");

        while ((bytesRead = dis.read(buffer)) > 0) {
            fos.write(buffer, 0, bytesRead);
        }

        fos.close();
        System.out.println("File received successfully!");
    }
}
