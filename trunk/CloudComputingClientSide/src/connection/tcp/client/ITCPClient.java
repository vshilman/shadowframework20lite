package connection.tcp.client;


public interface ITCPClient {


	public String getAnswer();
	
	public void send(String message);

	public void closeConnection();

}