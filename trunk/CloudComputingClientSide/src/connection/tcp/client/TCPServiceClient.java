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
		String codedMessage=Mediator.getMed().getCoder().convert(message);
		try {
			out.writeChars(codedMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obtainAnswer();
		
	}
	@Override
	public void send(User user) {
		String codedUser=Mediator.getMed().getCoder().convert(user);
		try {
			out.writeChars(codedUser);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obtainAnswer();
	}
	@Override
	public void send(List<String> messageList) {
		String codedList=Mediator.getMed().getCoder().convert(messageList);
		try {
			out.writeChars(codedList);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obtainAnswer();
	}

	@Override
	public void send(String message, User updatingUser) {
		String codedMessage=Mediator.getMed().getCoder().convert(message);
		String codedUser=Mediator.getMed().getCoder().convert(updatingUser);
		try {
			out.writeChars(codedMessage);
			out.writeChars(codedUser);
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
