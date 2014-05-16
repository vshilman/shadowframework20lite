package connection.tcp.client;

import java.util.List;

public interface ITCPClient {

	public abstract Object getAnswer();

	public abstract void send(List<Object> toSend);

	public abstract void closeConnection();

}