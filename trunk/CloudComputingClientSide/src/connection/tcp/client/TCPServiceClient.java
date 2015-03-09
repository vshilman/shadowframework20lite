package connection.tcp.client;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import mediator.Mediator;

public class TCPServiceClient implements ITCPClient {

	private Socket clientSocket;
	private Object answer;
	private XMLDecoder decoder;
	private DataOutputStream out;

	public TCPServiceClient(String ip) {

		try {
			clientSocket = new Socket(ip, 3000);
			out = new DataOutputStream(clientSocket.getOutputStream());
			decoder = new XMLDecoder(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//TODO: Tesi ferma al controllo ed implementazione di nuovi OBTAIN ANSWER che mi diano DIRETTAMENTE quello di cui ho bisogno!
	//TODO: finire implementazione di CheckAns + Implementazione regole di gioco + grafica -> tesi finita
	private void obtainAnswer() {
		answer = decoder.readObject();
	}

	@Override
	public Object getAnswer() {
		return answer;
	}

	@Override
	public void send(String message) throws IOException {
		Mediator.getMed().getCoder().convertMessage(message);
		out.writeChars(message);
		obtainAnswer();
		
	}
	@Override
	public void send(List<String> toSend)  throws IOException{
		for (int i = 0; i < toSend.size(); i++) {
			out.writeChars((toSend.get(i)));
		}
		out.flush();
		obtainAnswer();
	}

	@Override
	public void closeConnection() {
		try {
			out.close();
			decoder.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
