package connection.tcp.client;

import java.io.IOException;
import java.util.List;

public interface ITCPClient {

	public abstract Object getAnswer();

	public void send(String message) throws IOException;
	
	public abstract void send(List<String> toSend)  throws IOException;

	public abstract void closeConnection();

}