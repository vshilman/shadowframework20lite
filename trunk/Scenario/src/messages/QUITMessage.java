package messages;
import model.*;
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
			
			
			AvatarsHandler charHandler = AvatarsHandler.getInstance();
			charHandler.removeChar(Integer.parseInt(attrib.get(0)));
			
			
		}
		
	}
	
}


