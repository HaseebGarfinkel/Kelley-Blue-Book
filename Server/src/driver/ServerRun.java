package driver;

import server.DefaultServerSocket;

public class ServerRun {
    
    public static void main(String args[]) {

        DefaultServerSocket s1 = new DefaultServerSocket(4444);
        s1.run();
        
        }
}
