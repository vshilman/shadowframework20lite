package shadow.renderer.viewer;

import java.util.ArrayList;

import shadow.math.SFVertex3f;

public class POSNMessage extends Messages{
	
	
	
	private final static String messType = "POSN";
	private ArrayList<String> messAttrib;
	private int attrNumber = 4;	
	private String id;

	
	public POSNMessage(){
		
		super();
		
	}
	
	public POSNMessage(int id, SFVertex3f position){
		messAttrib = new ArrayList<String>();
		
		this.id = Integer.toString(id);
		this.messAttrib.add(""+ position.getX());
		this.messAttrib.add(""+ position.getY());
		this.messAttrib.add(""+ position.getZ());

		
	}
	
	
	public boolean matchType(String type){

		super.setType(messType);
		return super.matchType(type);
		
	}
	
	public void matchedMess(ArrayList<String> attrib){
		
		float x;
		float y;
		float z;
		int id;
		
		CharHandler charHandler = CharHandler.getInstance();
		MyCharModel mychar = MyCharModel.getInstance();
	
		if (attrib.size() == attrNumber){
			

			id = Integer.parseInt(attrib.get(0));
		  	x = Float.parseFloat(attrib.get(1));
		  	y = Float.parseFloat(attrib.get(2));
		  	z = Float.parseFloat(attrib.get(3));

	if(mychar.getMyId() != id){	  	
		
		    charHandler.setPositionAt(id, x, y, z);
	}
		    
		}
	}
	
	
	public String sendMess(){
		
		String messaggio = "";
		String spc = " ";
		messaggio = "POSN" + spc + id + spc + messAttrib.get(0) + spc + messAttrib.get(1) + spc + messAttrib.get(2);
		return messaggio;
		
		
	}
	
	
}
