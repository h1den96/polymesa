package Server;

import java.net.*;
import java.nio.file.Paths;
import java.util.List;
import java.io.*;

public class StreamServer{
    public static void main(String[] args) {

        int portNumber = Integer.parseInt(args[0]);

        String path = "/home/h1den/Downloads/TwinPeaksS03/";

        try{

            String[] extensions = {"avi", "mp4", "mkv"};
            List<String> files = FileFinder.findFiles(Paths.get(path), extensions, path);

            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is now running");

            while (true) {
                System.out.println("-----------------------------");
                System.out.println("waiting for a client...");
                Socket clientSocket = serverSocket.accept();
                
                for (String x : files){
                    System.out.println(x + ", ");
                }

                ClientHandler clientHandler = new ClientHandler(clientSocket, files);
                clientHandler.start();
            }
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }
}