package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DefaultServerSocket extends Thread {
    
    private int port;
    private ServerSocket server;

    public DefaultServerSocket(int port) {

        this.port = port;

        try {
            this.server = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println("Error: Could not listen on port: " + this.port);
            System.exit(1);
        }
    }
    
    @Override
    public void run() {



        while (true) {

            Socket clientSocket = null;
            try {

                clientSocket = server.accept();

            } catch (IOException e) {
                System.err.println("Error: Could not establish connection with client.");
                System.exit(1);
            }

            new DefaultSocketClient(clientSocket).start();
        }
    }
}
