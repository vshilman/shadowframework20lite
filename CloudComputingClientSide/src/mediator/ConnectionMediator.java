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
import connection.tcp.client.TCPServiceClient;
import connection.tcp.server.TCPServersManager;

public class ConnectionMediator {
	private static String answer;
	private static String secondMessage;
	private static Connector connection;
	private static ITCPClient serviceClient;
	private static TCPServersManager serverManager;
	private static HashMap<String, User> onlinePlayers;
	
	public ConnectionMediator() {
	}

	static{
		connection=new Connector("192.168.1.5");
		serverManager= new TCPServersManager();
		onlinePlayers=new HashMap<String, User>();
	}
	public HashMap<String, User> getServiceMap(){
		return Mediator.getMed().getComputator().getServiceMap();
	}
	public HashMap<String, User> getVisitorsMap(){
		return Mediator.getMed().getComputator().getVisitorsMap();
	}
	
	public void sendRequestOnService(String ip,String codedMessage, String codedSecond){
		serviceClient=new TCPServiceClient(ip);
		List<String> messageList= new ArrayList<String>();
		messageList.add(codedMessage);
		messageList.add(codedSecond);
		serviceClient.send(Mediator.getMed().getCoder().convert(messageList));
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	
	public void sendRequestOnService(String ip, String codedOne){
		serviceClient=new TCPServiceClient(ip);
		System.out.println("Ci sono");
		serviceClient.send(codedOne);
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	public void openServiceServer(){
		serverManager.openServiceServer();
	}
	public void closeServiceServer(){
		serverManager.closeServiceServer();
	}
	public void sendAnswerOnService(String objectsToSend){
		serverManager.getServiceServer().sendAnswer(objectsToSend);
	}
	public String getAns(){
		return answer;
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
