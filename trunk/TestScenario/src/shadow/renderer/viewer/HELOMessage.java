package shadow.renderer.viewer;

import java.util.ArrayList;

/* handles HELOMessage */

public class HELOMessage extends Messages {

	private final static String messType = "HELO";
	private String messAttrib;
	private int attrNumber = 1;
	private static int myId;

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
			MyCharModel mychar = MyCharModel.getInstance();
			mychar.setMyId(myId);
			System.out.println("L'ID assegnato è " + mychar.getMyId());
			
		}
	}
	
	
	public String sendMess(){
		String messaggio = "";
		String spc = " ";
		
		messaggio = messType;
		return messaggio;
		
	}
	
	

}
