package messages;
import model.*;

import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class CLSTMessage extends Messages {

	
	private final static String messType = "CLST";

	
	private ArrayList<String> messAttrib;
	private int attrNumber = 12;
	private static int myId;
	
public CLSTMessage(SFVertex3f pos, SFMatrix3f dir){
	    MyAvatarHandler mychar = MyAvatarHandler.getInstance();
        myId = mychar.getMyId();
        messAttrib = new ArrayList<String>();
        messAttrib.add(Float.toString(pos.getX()));
		messAttrib.add(Float.toString(pos.getY()));
		messAttrib.add(Float.toString(pos.getZ()));
		messAttrib.add(Float.toString(dir.getA()));
     	messAttrib.add(Float.toString(dir.getB()));
		messAttrib.add(Float.toString(dir.getC()));
		messAttrib.add(Float.toString(dir.getD()));
		messAttrib.add(Float.toString(dir.getE()));
		messAttrib.add(Float.toString(dir.getF()));
    	messAttrib.add(Float.toString(dir.getG()));
		messAttrib.add(Float.toString(dir.getH()));
		messAttrib.add(Float.toString(dir.getI()));


		
	}
	@Override
	public void matchedMess(ArrayList<String> attrib) {
		
		if(attrib.size() == attrNumber){
			
			MyAvatarHandler mychar = MyAvatarHandler.getInstance();
			SFVertex3f position = new SFVertex3f(Float.parseFloat(attrib.get(0)), Float.parseFloat(attrib.get(1)),Float.parseFloat(attrib.get(2)));
			SFMatrix3f direction = mychar.getMyDirection();
			direction.setA(Float.parseFloat(attrib.get(3)));
			direction.setB(Float.parseFloat(attrib.get(4)));
			direction.setC(Float.parseFloat(attrib.get(5)));
			direction.setD(Float.parseFloat(attrib.get(6)));
			direction.setE(Float.parseFloat(attrib.get(7)));
			direction.setF(Float.parseFloat(attrib.get(8)));
			direction.setG(Float.parseFloat(attrib.get(9)));
			direction.setH(Float.parseFloat(attrib.get(10)));
			direction.setI(Float.parseFloat(attrib.get(11)));

			mychar.setMyInitialPosAndDir(position, direction);
			
		}
		
	
	}
	
	public boolean matchType(String type) {

		super.setType(messType);
		return super.matchType(type);

	}
	
	public String sendMess(){
		String messaggio = "";
		String spc = " ";

		messaggio = messType;
		for(int i = 0; i < messAttrib.size(); i++){
			
			messaggio = messaggio + spc + messAttrib.get(i);
		}

		return messaggio;
		
	}

}
