package shadow.renderer.viewer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import de.roderick.weberknecht.WebSocket;
import de.roderick.weberknecht.WebSocketConnection;
import de.roderick.weberknecht.WebSocketException;
import de.roderick.weberknecht.WebSocketMessage;

public class ClientComm implements InputObserver {

	private WebSocket websocket;
	private double myID;
	private String myName;
	private MessageWSParser connectionWS;
	private MyCharModel myChar;

	public ClientComm() throws WebSocketException, URISyntaxException {

		URI url = new URI("ws://127.0.0.1:3000/");
		websocket = new WebSocketConnection(url);
		websocket.setEventHandler(new WebSocketEventHandler());
		websocket.connect();
		connectionWS = new MessageWSParser();
		myChar = MyCharModel.getInstance(this);
		
	}

	@Override
	public void keyUpdate(KeyEvent e) {

		float code = e.getKeyCode();

		if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {

			sendMyPos();
		}

	}

	@Override
	public void mouseUpdate(MouseEvent e) {

	}

	public class WebSocketEventHandler implements
			de.roderick.weberknecht.WebSocketEventHandler {

		@Override
		public void onClose() {

			System.out.println("Connessione chiusa");

			System.exit(0);

		}

		@Override
		public void onMessage(WebSocketMessage data) {

			String message = data.getText();
			new MessageWSParser().parseMessage(message);

		}

		@Override
		public void onOpen() {

			System.out.println("Connesso al server..in attesa dell'handshake");
			sendInit();

		}
	}

	public void sendInit() {

		HELOMessage sendHELO = new HELOMessage();

		try {
			websocket.send(sendHELO.sendMess());

		} catch (WebSocketException e) {

			e.printStackTrace();

		}

		System.out.println("Inviato HELO..");

	}

	public void sendMyPos() {

		POSNMessage sendMyPOSN = new POSNMessage(myChar.getMyId(),
				myChar.getMyPosition());

		try {

			websocket.send(sendMyPOSN.sendMess());

		} catch (WebSocketException e1) {

			e1.printStackTrace();
		}

	}
	
	public void notifyState(){
		
		System.out.println("Sending position...");
		sendMyPos();

		
		
	}

}
