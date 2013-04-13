package messages;

import model.*;
import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

/* handles HELOMessage */

public class HELOMessage extends Messages {

	private final static String messType = "HELO";
	private String messAttrib;
	private int attrNumber = 13;
	private static int myId;

	public HELOMessage(String param) {

		this.messAttrib = param;

	}

	public HELOMessage() {

	}

	public boolean matchType(String type) {

		super.setType(messType);
		return super.matchType(type);

	}

	public String sendInit() {

		String messComplete;

		messComplete = messType + " " + messAttrib;

		return messComplete;

	}

	public void matchedMess(ArrayList<String> attrib) {

		if (attrib.size() == attrNumber) {

			myId = Integer.parseInt(attrib.get(0));
			MyAvatarHandler mychar = MyAvatarHandler.getInstance();

			mychar.setMyId(myId);
			System.out.println("Il mio id è " + myId);
			SFVertex3f position = new SFVertex3f(
					Float.parseFloat(attrib.get(1)), Float.parseFloat(attrib
							.get(2)), Float.parseFloat(attrib.get(3)));

			SFMatrix3f direction = new SFMatrix3f();
			direction.setA(Float.parseFloat(attrib.get(4)));
			direction.setB(Float.parseFloat(attrib.get(5)));
			direction.setC(Float.parseFloat(attrib.get(6)));
			direction.setD(Float.parseFloat(attrib.get(7)));
			direction.setE(Float.parseFloat(attrib.get(8)));
			direction.setF(Float.parseFloat(attrib.get(9)));
			direction.setG(Float.parseFloat(attrib.get(10)));
			direction.setH(Float.parseFloat(attrib.get(11)));
			direction.setI(Float.parseFloat(attrib.get(12)));
			mychar.setMyInitialPosAndDir(position, direction);

		}
	}

	public String sendMess() {
		String messaggio = "";
		String spc = " ";

		messaggio = messType;
		return messaggio;

	}

}
