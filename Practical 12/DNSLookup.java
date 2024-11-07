import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DNSLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an IP address or a URL: ");
        String userInput = scanner.nextLine().trim();

        // Check if input is an IP address
        if (isIPAddress(userInput)) {
            // Lookup URL from IP
            try {
                InetAddress inetAddress = InetAddress.getByName(userInput);
                System.out.println("The URL for IP address " + userInput + " is: " + inetAddress.getHostName());
            } catch (UnknownHostException e) {
                System.out.println("Could not find a URL for the IP address: " + userInput);
            }
        } else {
            // Lookup IP address from URL
            try {
                InetAddress inetAddress = InetAddress.getByName(userInput);
                System.out.println("The IP address for the URL " + userInput + " is: " + inetAddress.getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println("Could not resolve the URL: " + userInput);
            }
        }

        scanner.close();
    }

    // Function to check if the input is a valid IP address
    private static boolean isIPAddress(String input) {
        String[] parts = input.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        try {
            for (String part : parts) {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
