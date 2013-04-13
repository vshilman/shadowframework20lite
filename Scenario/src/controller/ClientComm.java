package controller;

import controller.*;
import commons.*;
import interfaces.*;
import viewer.*;
import model.*;
import messages.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

public class ClientComm extends WebSocketClient implements ModelMyAvatar {

	private double myID;
	private String myName;
	private MessageWSParser connectionWS;
	private MyAvatarHandler myChar;
	private String param;

	public ClientComm(URI serverUri, Draft draft) {

		super(serverUri, draft);
		this.connect();

		connectionWS = new MessageWSParser();
		myChar = MyAvatarHandler.getInstance(this);

	}

	public ClientComm(String param) throws URISyntaxException {

		this(new URI("ws://127.0.0.1:3000/"), new Draft_10());
		this.param = param;

	}

	public void sendInit() {

		HELOMessage sendHELO = new HELOMessage(param);

		this.send(sendHELO.sendInit());
		System.out.println("Inviato HELO con la PASS..");

	}

	public void sendMyPos() {

		CLSTMessage sendMyState = new CLSTMessage(myChar.getMyPosition(),
				myChar.getMyDirection());

		this.send(sendMyState.sendMess());

	}

	public void notifyState() {

	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {

		System.out.println("Connessione chiusa");
		System.exit(0);

	}

	@Override
	public void onError(Exception arg0) {

	}

	@Override
	public void onMessage(String data) {

		String message = data;
		new MessageWSParser().parseMessage(message);

	}

	@Override
	public void onOpen(ServerHandshake arg0) {

		System.out.println("Connesso al server..in attesa dell'handshake");
		sendInit();

	}

	@Override
	public void notifyConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyChgState(float code) {

		sendMyPos();

	}

}
