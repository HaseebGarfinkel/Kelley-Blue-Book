package client;

import java.io.*;
import java.net.Socket;

public class DefaultSocketClient extends Thread {
    
    private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader stdIn;
	private Socket socket;
	private String host;
	private int port;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;

	public DefaultSocketClient(String host, int port) {
		this.host = host;
		this.port = port;
	}


	public void establishConnection() {
		try {

			this.socket = new Socket(host, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader (System.in));
			clientFTP = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();

		}
		catch (IOException e) {
			System.exit(1);
		}
	}

	public void handleConnection() {

		Object fromServer, toServer = null;

		try {

			while ((fromServer = in.readObject()) != null) {

				System.out.println(fromServer.toString());

				if (clientProtocol.isAutomobile(fromServer)) {
					clientProtocol.configureAuto(fromServer);
                }
				toServer = stdIn.readLine();
				if (toServer.toString().contains(".prop")) {
					toServer = clientFTP.loadPropertiesFile(toServer.toString());
				}
				if (toServer.toString().contains(".txt")) {
					toServer = clientFTP.loadTextFile(toServer.toString());
				}

				sendOutput(toServer);
				System.out.println("");

				if (toServer.equals("0")) {
					break;
				}
			}

			in.close();

		}
		catch (ClassNotFoundException e) {
			System.exit(1);
		}
		catch (IOException e) {
			System.exit(1);
		}

	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.exit(1);
		}
	}

	@Override
	public void run() {
		establishConnection();
		handleConnection();
		try {
			out.close();
			stdIn.close();
			socket.close();
		}
		catch (IOException e) {
			System.exit(1);
		}
	}
    
}
