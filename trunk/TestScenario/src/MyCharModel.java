
import java.util.ArrayList;

import shadow.math.SFVertex3f;

// states
// 1: connected
// 2: helo received
// 3: position received

public class MyCharModel {

	private static final MyCharModel instance = new MyCharModel();
	private static int myId;
	private static String myName;
	private static SFVertex3f myPosition;
	private static ArrayList<ClientComm> objListComm = new ArrayList<ClientComm>();

	private static ArrayList<CameraController> objListViewer = new ArrayList<CameraController>();

	private MyCharModel() {
	}

	public static MyCharModel getInstance() {
		return instance;
	}

	public static MyCharModel getInstance(ClientComm me) {
		
		objListComm.add(me);
		return instance;
	
	}
	
	public static MyCharModel getInstance(CameraController me) {
		
		objListViewer.add(me);
		return instance;

	}

	public int getMyId() {

		return myId;

	}

	public void setMyId(int id) {

		myId = id;
		notifyComm(1);

	}

	

	public void notifyViewer() {

		for (CameraController obs : objListViewer) {
			obs.modelNotif();
		}

	}

	public void notifyComm(int state) {

		for (ClientComm obs : objListComm) {
			obs.notifyState(state);
		}

	}
	
	public void setMyPosition(SFVertex3f position) {

		myPosition = position;
		

	}

	public SFVertex3f getMyPosition() {

		return myPosition;

	}

	
	public void setMyInitialPosition(SFVertex3f position) {
		myPosition = position;
		notifyViewer();
		notifyComm(2);
	}

}
