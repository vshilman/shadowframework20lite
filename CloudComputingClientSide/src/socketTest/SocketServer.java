package socketTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	private static ServerSocket welcomeSocket;

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		welcomeSocket= new ServerSocket(3000);
		Socket connectionSocket = welcomeSocket.accept();
		DataOutputStream outToClient = new DataOutputStream(
				connectionSocket.getOutputStream());
		BufferedReader inFromClient = new BufferedReader(
				new InputStreamReader(connectionSocket.getInputStream()));

		while (true) {
	//		connectionSocket.bind(connectionSocket.getRemoteSocketAddress());
		System.out.println(connectionSocket.getLocalSocketAddress());
			System.out.println("kebab1");
			clientSentence = inFromClient.readLine();
			System.out.println("kebab2");
			int i=0;
			while (!clientSentence.isEmpty()) {
				System.out.println("kebab4 count:"+ i);
				System.out.println("Received: " + clientSentence);
				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				System.out.println("sending back :"+capitalizedSentence);
				outToClient.writeBytes(capitalizedSentence);
				
				System.out.println("Sended");
				break;
				
			}
			if (clientSentence.equals("exit")) {
				break;
			}

		}
		
	}
}
