package connection.tcp.client;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class TCPGamingClient implements ITCPClient {

	private Socket clientSocket;
	private XMLEncoder encoder;
	private XMLDecoder decoder;
	private Object answer;

	public TCPGamingClient(String ip) {
		try {
			clientSocket = new Socket(ip, 3333);
			encoder = new XMLEncoder(clientSocket.getOutputStream());
			decoder = new XMLDecoder(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void obtainAnswer(String action) {
		answer = decoder.readObject();
	}

	@Override
	public Object getAnswer() {
		return answer;
	}

	@Override
	public void send(List<Object> toSend) {
		for (int i = 0; i < toSend.size(); i++) {
			encoder.writeObject(toSend.get(i));
		}
		encoder.flush();
		obtainAnswer();
	}

	@Override
	public void closeConnection() {
		try {
			encoder.close();
			decoder.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
