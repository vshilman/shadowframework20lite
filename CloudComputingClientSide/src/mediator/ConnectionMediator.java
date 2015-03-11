package mediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	private static ConnectionMediator cMed;
	private static HashMap<String, User> onlinePlayers;
	
	public ConnectionMediator() {
	}

	static{
		connection=new Connector("localhost");
		serverManager= new TCPServersManager();
		onlinePlayers=new HashMap<String, User>();
		
		
	}
//	public void addOnlinePlayer(User user){
//		onlinePlayers.put(user.getNick(), user);
//	}
	public HashMap<String, User> getServiceMap(){
		return Mediator.getMed().getComputator().getServiceMap();
	}
//	public HashMap<String, User> getPlayersMap(){
//		return Mediator.getMed().getComputator().getPlayersMap();
//	}
	public HashMap<String, User> getVisitorsMap(){
		return Mediator.getMed().getComputator().getVisitorsMap();
	}
	
	public void sendRequestOnService(String ip,String message, User user){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(message,user);
		answer=serviceClient.getAnswer();
		secondMessage=serviceClient.getSecondMessage();
		serviceClient.closeConnection();
	}
	
	public void sendRequestOnService(String ip, User user){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(user);
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	public void sendRequestOnService(String ip, List<String> messageList){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(messageList);
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	public void sendRequestOnService(String ip, String message){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(message);
		answer=serviceClient.getAnswer();
		serviceClient.closeConnection();
	}
	public void openServiceServer(){
		serverManager.openServiceServer();
	}
	public void closeServiceServer(){
		serverManager.closeServiceServer();
	}
	public void sendAnswerOnService(Object objectsToSend){
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
	public HashMap<String, User> getOnlinePlayers() {
		return onlinePlayers;
	}
	public void setOnlineMap(HashMap<String, List<String>> rawMap){
		Set<String>keySet=rawMap.keySet();
		List<String> list=new ArrayList<String>();
		for (int i = 0; i < rawMap.size(); i++) {
			list.add(keySet.iterator().next());
			list.add(rawMap.get(list.get(0)).get(0));
			list.add(rawMap.get(list.get(0)).get(1));
			onlinePlayers.clear();
			onlinePlayers.put(list.get(0), Mediator.getMed().getComputator().generateUser(list));
		}
	}


	
	
	
//	private void activateDeamon(){
//		thread.setDaemon(true);
//		thread.start();
//	}
	
	public void updateUsers(List<Object> objectsToSend) {
		Set<String> nickOnline=onlinePlayers.keySet();
		for (Iterator iterator = nickOnline.iterator(); iterator.hasNext();) {
			String nick = (String) iterator.next();
			serviceClient=new TCPServiceClient(onlinePlayers.get(nick).getIp());
			serviceClient.send(objectsToSend);
			serviceClient.closeConnection();
		}
	}



	
}
