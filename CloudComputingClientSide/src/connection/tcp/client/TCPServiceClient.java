package connection.tcp.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPServiceClient implements ITCPClient {

	private Socket clientSocket;
	private String answer;
	private String secondMessage;
	private InputStreamReader in;
	private DataOutputStream out;
	private String ip;

	public TCPServiceClient(String ip) {
		this.ip=ip;
		try {
			System.out.println("Entro nel costruttore");
			clientSocket = new Socket(ip, 3000);
			System.out.println("Socket Creato Correttamente");
			in = new InputStreamReader(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("creati i lettori");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void obtainAnswer() {
		System.out.println("istanzio lettore");
		BufferedReader reader= new BufferedReader(in);
		System.out.println("lettore istaziato Correttamente");
		try {
			System.out.println("tentativo di lettura flusso dati");
			answer = reader.readLine();
			System.out.println("letto Correttamente: "+answer);
//			String tmp= reader.readLine();
//			if (!tmp.isEmpty()) {
//				secondMessage=tmp;
//			}
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
		System.out.println(clientSocket.isConnected()+"---"+clientSocket.getRemoteSocketAddress().toString());
		int i=0;
		
		System.out.println("entro per invio di "+message+" a "+ip);
		try {
			out.writeBytes(message+ '\n'); 
			out.writeBytes(""+'\n');
//			out.writeBytes(message);
//			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("inviati");
		System.out.println("richiesta di risposta");
		obtainAnswer();
		System.out.println("risposta ottenuta");
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
