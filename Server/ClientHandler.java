package Server;
import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler extends Thread {

    Socket socket;
    List<String> files;

    public ClientHandler(Socket socket, List<String> files) {
        this.socket = socket;
        this.files = files;
    }

    @Override
    public void run() {
        System.out.println("Client accepted");

        System.out.println("Local Socket: " + socket.getLocalSocketAddress());
        System.out.println("Remote Socket: " + socket.getRemoteSocketAddress());

        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome to StreamServer!");

            String line = "";
            while (true){
                if ((line = in.readLine()) == null){
                    System.out.println("Client is not responding");
                    break;
                }else if (line.equalsIgnoreCase("exit")) {
                    System.out.println("Client has requested exit");
                    out.println("Bye");
                    break;
                } else {

                    System.out.println("Sending file list to client...");
                    System.out.println("Client: " + line);
                    for (String x : files){
                        out.println(x);
                    }
                    out.println("");
                    
                }
                
                //System.out.println("Client: " + line);
            }

            System.out.println("terminate connection...");
            socket.close();
            System.out.println("Connection closed.");

            out.close();
            in.close();

        } catch(IOException ex){
            System.out.println(ex);
        }
    }
}