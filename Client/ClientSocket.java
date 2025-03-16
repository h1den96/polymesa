package Client;

import java.net.*;
import java.io.*;

public class ClientSocket {

    static final int portNumber = 6666;
    static final String serverHost = "localhost";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(serverHost, portNumber);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            // Get the welcome message from the server
            System.out.println(in.readLine());

            String message;
            while (true) {
                System.out.print("Enter your message: ");
                message = userInput.readLine();
                out.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String received;
                StringBuilder fullResponse = new StringBuilder();
                while ((received = in.readLine()) != null && !received.isEmpty()) {
                    fullResponse.append(received).append("\n");
                }

                System.out.println("Files received from server:");
                System.out.println(fullResponse.toString());
            }

            System.out.println("Terminating connection...");
            System.out.println("Connection closed");

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
