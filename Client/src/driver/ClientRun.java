package driver;

import client.DefaultSocketClient;

public class ClientRun {
    
    public static void main(String args[]) {

        DefaultSocketClient s1 = new DefaultSocketClient("127.0.0.1", 4444);
        s1.run();
    }
}
