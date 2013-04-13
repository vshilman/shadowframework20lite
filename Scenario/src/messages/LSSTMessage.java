package messages;
import model.*;

import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class LSSTMessage extends Messages{
	
	
	
	private final static String messType = "LSST";
	private ArrayList<String>messAttrib;
	private int attrNumber = 8;	
	private String id;

	
	public LSSTMessage(){
		
		super();
		
	}
	
	public LSSTMessage(int id, SFVertex3f position, SFVertex3f direction){
		messAttrib = new ArrayList<String>();
		
		this.id = Integer.toString(id);
		this.messAttrib.add(""+ position.getX());
		this.messAttrib.add(""+ position.getY());
		this.messAttrib.add(""+ position.getZ());
		this.messAttrib.add(""+ direction.getX());
		this.messAttrib.add(""+ direction.getY());
		this.messAttrib.add(""+ direction.getZ());
		
		
	}
	
	
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
		MyAvatarHandler mychar = MyAvatarHandler.getInstance();
	
		if (attrib.size() >= attrNumber){
			
			listSize = Integer.parseInt(attrib.get(0));
			for(int i = 1; i <= listSize; i++){
				
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
			  	
			  	
				
			}
			
	
		}
	}
	
	
	public String sendMess(){
		
		String messaggio = "";
		String spc = " ";
		messaggio = "MYPS" + spc + messAttrib.get(1) + spc + messAttrib.get(2) + spc + messAttrib.get(3) + spc + messAttrib.get(4) + spc + messAttrib.get(5) + spc + messAttrib.get(6);
		return messaggio;
		
		
	}
	
	
}
