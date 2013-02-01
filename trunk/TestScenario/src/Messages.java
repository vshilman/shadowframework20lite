
import java.util.ArrayList;

public abstract class Messages {
	
	private static int myId;
    private String messType = "";
	
	

    
	public boolean matchType(String type) {
		
		boolean bool = false;

		if (type.compareTo(messType) == 0) {

			bool = true;

		}

		return bool;

	}
	
	
	public void matchedMess(ArrayList<String> attrib){
		
		
		
	}

	public void setType(String mess) {
	
		messType = mess;
		
	}
	
	
	
}
