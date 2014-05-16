package connection.tcp.client;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.net.Socket;

public class TCPServiceClient implements ITCPClient {

	private Socket clientSocket;
	private Object answer;
	private XMLDecoder decoder;
	private XMLEncoder encoder;

	public TCPServiceClient(String ip) {

		try {
			clientSocket = new Socket(ip, 3000);
			encoder = new XMLEncoder(clientSocket.getOutputStream());
			decoder = new XMLDecoder(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void obtainAnswer() {
		answer = decoder.readObject();
	}

	@Override
	public Object getAnswer() {
		return answer;
	}

	@Override
	public void send(Object toSend) {
		encoder.writeObject(toSend);
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
