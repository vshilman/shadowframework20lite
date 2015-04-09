package connection.tcp.client;


public interface ITCPClient {


	public String getAnswer();
	
	public String getSecondMessage();
	
	public void send(String message);
	

	public void closeConnection();

}