package connection.tcp.client;

public interface ITCPClient {

	public abstract Object getAnswer();

	public abstract void send(Object toSend);

	public abstract void closeConnection();

}