import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; 
        int port = 12345;               
        String filename = "sample.txt"; 

        Socket socket = null;

        try {
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to the server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Hello from Client!");

            String serverMessage = in.readLine();
            System.out.println("Server: " + serverMessage);

            sendFile(socket, filename);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendFile(Socket socket, String filename) throws IOException {
        // Sending file
        FileInputStream fis = new FileInputStream(filename);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int bytesRead;

        System.out.println("Sending file...");

        while ((bytesRead = fis.read(buffer)) > 0) {
            dos.write(buffer, 0, bytesRead);
        }

        fis.close();
        System.out.println("File sent successfully!");
    }
}
