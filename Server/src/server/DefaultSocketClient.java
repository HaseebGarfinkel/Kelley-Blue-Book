package server;

import java.io.*;
import java.net.Socket;

public class DefaultSocketClient extends Thread {
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientSocket;
    private BuildCarModelOptions protocol;

    public DefaultSocketClient(Socket clientSocket) {

        this.clientSocket = clientSocket;

    }

    public void handleConnection(Socket s1) {

        try {
            this.out = new ObjectOutputStream(s1.getOutputStream());
            this.in = new ObjectInputStream(s1.getInputStream());
        } catch(IOException e) {
            System.exit(1);
        }

        this.protocol = new BuildCarModelOptions();

        String menu = "\nWelcome\n0: end connection\n1: upload automobile\n2: configure automobile";

        try {
            do {
                sendOutput(menu);

                int request = Integer.parseInt(in.readObject().toString());
            
                if (request == 0) {
                    break;
                }

                sendOutput(protocol.setRequest(request));

                if (request == 1 || request == 2) {
                    handleInput(request);
                }

            } while (in.readObject() != null);

            in.close();

        } catch (Exception e) {

        }
 

    }

    public void sendOutput(Object object) {

        try {
            out.writeObject(object);
        } catch(IOException e) {
            System.exit(1);
        }
    }

    public void handleInput(int request) {

        Object fromClient = null;
        Object toClient = null;

        try {

            switch (request) {


                case 1:
                    fromClient = in.readObject();
                    toClient = this.protocol.processRequest(fromClient);
                    sendOutput(toClient);
                    break;
                case 2:
                    fromClient = Integer.parseInt(in.readObject().toString());
                    toClient = protocol.processRequest(fromClient);
                    sendOutput(toClient);
                    break;
                    
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void run() {

        handleConnection(clientSocket);

        try {
            out.close();
            clientSocket.close();
        } catch(IOException e) {

        }


    }
    
}
