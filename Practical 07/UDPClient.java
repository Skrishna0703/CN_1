import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 12345;                  
        String filename = "sample.txt";    

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();

            byte[] filenameBytes = filename.getBytes();
            DatagramPacket filenamePacket = new DatagramPacket(filenameBytes, filenameBytes.length,
                    InetAddress.getByName(serverAddress), port);
            socket.send(filenamePacket);

            FileInputStream fis = new FileInputStream(filename);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                DatagramPacket dataPacket = new DatagramPacket(buffer, bytesRead,
                        InetAddress.getByName(serverAddress), port);
                socket.send(dataPacket);
            }

            DatagramPacket endPacket = new DatagramPacket(new byte[0], 0,
                    InetAddress.getByName(serverAddress), port);
            socket.send(endPacket);

            fis.close();
            System.out.println("File " + filename + " sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
