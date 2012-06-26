package shadow.renderer.viewer;

import java.util.ArrayList;

import shadow.math.SFVertex3f;

// contains data of the

public class MyCharModel {

	private static final MyCharModel instance = new MyCharModel();
	private static int myId;
	private static String myName;
	private static String myState = "Handshake non completato";
	private static SFVertex3f myPosition;
	private static ArrayList<ClientComm> objListComm = new ArrayList<ClientComm>();

	private static ArrayList<ModelNotif> objListViewer = new ArrayList<ModelNotif>();

	private MyCharModel() {
	}

	public static MyCharModel getInstance() {
		return instance;
	}

	public static MyCharModel getInstance(ClientComm me) {
		
		objListComm.add(me);
		return instance;
		
	
	
	
	}
	
	public static MyCharModel getInstance(ModelNotif me) {
		
		objListViewer.add(me);
		return instance;

	}

	public int getMyId() {

		return myId;

	}

	public void setMyId(int id) {

		myId = id;
		myState = "Handshake completato";
		notifyViewer();
		notifyComm();

	}

	public String getMyState() {

		return myState;

	}

	public void notifyViewer() {

		for (ModelNotif obs : objListViewer) {
			obs.myCharNotif();
		}

	}

	public void notifyComm() {

		for (ClientComm obs : objListComm) {
			obs.notifyState();
		}

	}
	
	public void setMyPosition(SFVertex3f position) {

		myPosition = position;

	}

	public SFVertex3f getMyPosition() {

		return myPosition;

	}

}
