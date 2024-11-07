// UDP Client
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // Create a UDP socket
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String clientMessage = "Hello from UDP Client! Shrikrishna";
            sendData = clientMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); 
            String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + serverMessage);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
