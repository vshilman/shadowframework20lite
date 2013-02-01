
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

public class ClientComm extends WebSocketClient implements InputObserver  {

	
	private double myID;
	private String myName;
	private MessageWSParser connectionWS;
	private MyCharModel myChar;
	private String param;


	
	public ClientComm( URI serverUri , Draft draft ) {
		
		super( serverUri, draft );
		this.connect();
		connectionWS = new MessageWSParser();
		myChar = MyCharModel.getInstance(this);
		
	}
	
	public ClientComm(String param) throws URISyntaxException {
		
		this(new URI("ws://127.0.0.1:3000/"), new Draft_10());
		this.param = param;
		
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



	public void sendInit() {

		HELOMessage sendHELO = new HELOMessage(this.param);
		
      this.send(sendHELO.sendInit());
		System.out.println("Inviato HELO..");

	}

	public void sendMyPos() {

		POSNMessage sendMyPOSN = new POSNMessage(myChar.getMyId(), myChar.getMyPosition());
      
		this.send(sendMyPOSN.sendMess());

	}
	

	public void notifyState(int state){
		
		 if(state == 1){
			
			System.out.println("Received ID");
			
		}else if(state == 2){
			
			System.out.println("Received Position");

			
		}
		
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

}



	
	
	
	

