package socketTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {

	
	
	public static void main(String argv[]) throws Exception  {   
		String sentence;   
		String modifiedSentence;   
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));   
		Socket clientSocket = new Socket("192.168.1.5",3333);   
		System.out.println("Sended to: "+clientSocket.getInetAddress());
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());   
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   
		sentence = inFromUser.readLine();   
		outToServer.writeBytes(sentence + '\n'); 
		outToServer.writeBytes(""+'\n');
		modifiedSentence = inFromServer.readLine();   
		System.out.println("FROM SERVER: " + modifiedSentence); 
	}
}
