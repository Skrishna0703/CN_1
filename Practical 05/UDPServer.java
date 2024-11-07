
// UDP Server
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Create a UDP socket that listens on port 9876
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Server is running on port 9876...");

            while (true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + clientMessage);

                String serverMessage = "Hello from UDP Server!";
                sendData = serverMessage.getBytes();

                InetAddress clientIPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
