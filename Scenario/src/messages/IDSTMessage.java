package messages;

import java.util.ArrayList;

import model.AvatarsHandler;
import model.MyAvatarHandler;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class IDSTMessage extends Messages{

	private final static String messType = "IDST";
	private int attrNumber = 7;	

	
	public boolean matchType(String type){

		super.setType(messType);
		return super.matchType(type);
		
	}
	
	public void matchedMess(ArrayList<String> attrib){
		
		SFVertex3f pos;
		SFMatrix3f dir;
		int id;
		int listSize;

		AvatarsHandler charHandler = AvatarsHandler.getInstance();
	
		if (attrib.size() == attrNumber){
			
			listSize = Integer.parseInt(attrib.get(0));
			for(int i = 0; i <= listSize; i++){
				
				pos = new SFVertex3f();
				dir = new SFMatrix3f();
				
				id = Integer.parseInt(attrib.get(i));
			  	pos.setX(Float.parseFloat(attrib.get(i + 1)));
			  	pos.setY(Float.parseFloat(attrib.get(i + 2)));
			  	pos.setZ(Float.parseFloat(attrib.get(i + 3)));
			  	dir.setA(Float.parseFloat(attrib.get(i + 4)));
			  	dir.setB(Float.parseFloat(attrib.get(i + 5)));
			  	dir.setC(Float.parseFloat(attrib.get(i + 6)));
				dir.setD(Float.parseFloat(attrib.get(i + 7)));
			  	dir.setE(Float.parseFloat(attrib.get(i + 8)));
			  	dir.setF(Float.parseFloat(attrib.get(i + 9)));
			 	dir.setG(Float.parseFloat(attrib.get(i + 10)));
			  	dir.setH(Float.parseFloat(attrib.get(i + 11)));
			  	dir.setI(Float.parseFloat(attrib.get(i + 12)));
			  	
				charHandler.setPositionAt(id, pos, dir);
			  	
				System.out.println("Nuovo PERS");
				
			}
			
	
		}
	}
}
