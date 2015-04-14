package mediator;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import utils.User;
import connection.Connector;
import connection.tcp.client.ITCPClient;
import connection.tcp.client.TCPGamingClient;
import connection.tcp.client.TCPServiceClient;
import connection.tcp.server.TCPServersManager;

public class ConnectionMediator {
	private static String answer;
	private static String gamingAnswer;
	private static String secondMessage;
	private static Connector connection;
	private static ITCPClient serviceClient;
	private static ITCPClient gamingClient;
	private static TCPServersManager serverManager;
	private static HashMap<String, User> onlinePlayers;
	
	public ConnectionMediator() {
	}

	static{
		connection=new Connector("192.168.1.4");
		serverManager= new TCPServersManager();
		onlinePlayers=new HashMap<String, User>();
	}
	public HashMap<String, User> getServiceMap(){
		return Mediator.getMed().getComputator().getServiceMap();
	}
	
	public void sendRequestOnService(String ip,String codedMessage, String codedSecondMessage){
		serviceClient=new TCPServiceClient(ip);
		List<String> messageList= new ArrayList<String>();
		messageList.add(codedMessage);
		messageList.add(codedSecondMessage);
		serviceClient.send(Mediator.getMed().getCoder().convert(messageList));
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	
	public void sendRequestOnService(String ip, String codedMessage){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(codedMessage);
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	public void sendRequestOnGaming(String ip, String codedMessage){
		gamingClient=new TCPGamingClient(ip);
		gamingClient.send(codedMessage);
		gamingAnswer=gamingClient.getAnswer();
		gamingClient.closeConnection();
	}
	public void openServiceServer(){
		serverManager.openServiceServer();
	}
	public void closeServiceServer(){
		serverManager.closeServiceServer();
	}
	public void sendAnswerOnService(String messageEncoded){
		serverManager.getServiceServer().sendAnswer(messageEncoded);
	}
	public void sendAnswerOnGaming(String messageEncoded){
		serverManager.getGamingServer().sendAnswer(messageEncoded);
	}
	public String getAns(){
		return answer;
	}
	public String getGamingAnswer(){
		return gamingAnswer;
	}
	public String getSecondAns(){
		return secondMessage;
	}
	public Connector getConnection() {
		return connection;
	}
	public String getMyIp(){
		String myIp="";
		try {
			myIp=Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return myIp;
	}
	public HashMap<String, User> getOnlinePlayers() {
		return onlinePlayers;
	}
	public void setOnlineMap(HashMap<String, User> map){
		onlinePlayers.putAll(map);
		Mediator.getMed().getComputator().updateOnlineMap(map);
	}

	
	public void updateOnlineMap(HashMap<String, User> onlineMap) {
		Set<String> nickOnline=onlinePlayers.keySet();
		for (String string : nickOnline) {
			String nick = (String) string;
			serviceClient=new TCPServiceClient(onlinePlayers.get(nick).getIp());
			serviceClient.send(Mediator.getMed().getCoder().convert(onlinePlayers));
			serviceClient.closeConnection();
		}
	}



	
}
