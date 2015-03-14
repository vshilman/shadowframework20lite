package connection.tcp.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import mediator.Mediator;
import utils.User;

public class TCPServiceClient implements ITCPClient {

	private Socket clientSocket;
	private String answer;
	private String secondMessage;
	private DataInputStream in;
	private DataOutputStream out;

	public TCPServiceClient(String ip) {

		try {
			clientSocket = new Socket(ip, 3000);
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//TODO: Tesi ferma al controllo ed implementazione di nuovi OBTAIN ANSWER che mi diano DIRETTAMENTE quello di cui ho bisogno!
	//TODO: finire implementazione di CheckAns + Implementazione regole di gioco + grafica -> tesi finita
	private void obtainAnswer() {
		BufferedReader reader= new BufferedReader(new InputStreamReader(in));
		try {
			answer = reader.readLine();
			String tmp= reader.readLine();
			if (!tmp.isEmpty()) {
				secondMessage=tmp;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getAnswer() {
		return answer;
	}
	@Override
	public String getSecondMessage(){
		return secondMessage;
	}
	
	@Override
	public void send(String message){
		try {
			out.writeChars(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obtainAnswer();
		
	}

	@Override
	public void send(String codedMessage, String codedSecond) {
		try {
			out.writeChars(codedMessage);
			out.writeChars(codedSecond);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obtainAnswer();
	}
	
	@Override
	public void closeConnection() {
		try {
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
