package connection.tcp.client;

import java.util.List;

import utils.User;

public interface ITCPClient {


	public String getAnswer();
	
	public String getSecondMessage();
	
	public void send(String message);
	
	public void send(List<String> messageList);
	
	public void send(User user);

	public void send(String message, User updatingUser);


	public void closeConnection();

}