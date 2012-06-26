package shadow.renderer.viewer;

import java.util.ArrayList;

public class QUITMessage extends Messages {

	private final static String messType = "QUIT";
	int attrNumber = 1;

	public boolean matchType(String type){

		super.setType(messType);
		return super.matchType(type);
		
	}

	
	public void matchedMess(ArrayList<String> attrib){
		
		if (attrib.size() == attrNumber){
			
			
			CharHandler charHandler = CharHandler.getInstance();
			charHandler.removeChar(Integer.parseInt(attrib.get(0)));
			
			
		}
		
	}
	
}


