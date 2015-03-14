package connection.tcp.client;


public interface ITCPClient {


	public String getAnswer();
	
	public String getSecondMessage();
	
	public void send(String message);
	
	public void send(String codedMessage, String codedSecond);


	public void closeConnection();

}