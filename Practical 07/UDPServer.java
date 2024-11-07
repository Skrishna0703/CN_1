import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        int port = 12345; 
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(port);
            System.out.println("Server is listening on port " + port);
            byte[] buffer = new byte[1024];
            DatagramPacket filenamePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(filenamePacket);
            String filename = new String(filenamePacket.getData(), 0, filenamePacket.getLength());
            System.out.println("Receiving file: " + filename);

            FileOutputStream fos = new FileOutputStream(filename);
            while (true) {

                DatagramPacket dataPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(dataPacket);

                if (dataPacket.getLength() == 0) {
                    break;
                }
                fos.write(dataPacket.getData(), 0, dataPacket.getLength());
            }

            fos.close();
            System.out.println("File " + filename + " received successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
