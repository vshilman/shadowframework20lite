package connection.tcp.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPServiceClient implements ITCPClient {

	private Socket clientSocket;
	private String answer;
	private InputStreamReader in;
	private DataOutputStream out;

	public TCPServiceClient(String ip) {
		try {
			clientSocket = new Socket(ip, 3000);
			in = new InputStreamReader(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void obtainAnswer() {
		BufferedReader reader= new BufferedReader(in);
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAnswer() {
		return answer;
	}
	
	@Override
	public void send(String message){
		try {
			out.writeBytes(message+ '\n'); 
			out.writeBytes(""+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		obtainAnswer();
	}

	@Override
	public void closeConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
