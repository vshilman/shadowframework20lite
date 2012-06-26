package shadow.renderer.viewer;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class MessageWSParser {

	private String commandType;
    ArrayList<String> commandAttr;
    ArrayList<Messages> listMessages = new ArrayList<Messages>();
	
  
    public String connectionState = "WAITING";
	
    
    
    public MessageWSParser(){
    	
    	// we add the messages to match
    	
    	this.addMessage(new HELOMessage());
    	this.addMessage(new POSNMessage());
    	this.addMessage(new QUITMessage());

    	
    }
    
	public int parseMessage(String message){
	
		String token;
		int i = 0;
		commandAttr = new ArrayList<String>();
		StringTokenizer mess = new StringTokenizer(message);
		while(mess.hasMoreTokens()){
			
			i = i + 1 ;
			token = mess.nextToken();
		
			if(i == 1 ){
			
				commandType = token;
			
				
			}else {
			
			commandAttr.add(token);
			
			}
		
		}
		
	
		
		matchMessage(commandType);

		
		
		return 0;
		
		
		
	}
	
	
	
	public String getWebSocketState(){
		
		
		return connectionState;
		
		
	}
	
	public void changeWebSocketState(String state){
		
		
		connectionState = state;
		
		
	}

	
	public void matchMessage(String commandType){
		
		Iterator<Messages> itr = listMessages.iterator();
		boolean bool;
		Messages matchedMess;
		while(itr.hasNext()){
			
			matchedMess = itr.next();
			bool = matchedMess.matchType(commandType);
		
			if (bool == true){
				
				
				matchedMess.matchedMess(commandAttr);

			}
			
			
		}
		
	}
	
	
	
	public void addMessage(Messages message){
		
		
		listMessages.add(message);
		
	}
}
