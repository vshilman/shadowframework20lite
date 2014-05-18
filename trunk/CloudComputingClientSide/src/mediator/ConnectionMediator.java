package mediator;

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
	private static Object answer;
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
	public HashMap<String, User> getServiceMap(){
		return Mediator.getMed().getComputator().getServiceMap();
	}
	public HashMap<String, User> getPlayersMap(){
		return Mediator.getMed().getComputator().getPlayersMap();
	}
	public HashMap<String, User> getVisitorsMap(){
		return Mediator.getMed().getComputator().getVisitorsMap();
	}
	public void sendRequestOnService(String ip, List<Object> objectsToSend){
		serviceClient=new TCPServiceClient(ip);
		serviceClient.send(objectsToSend);
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
	public Object getAns(){
		return answer;
	}
	public Connector getConnection() {
		return connection;
	}
	


	
	
	public void logout(){
		Mediator.getMed().getComputator().validateLogout();
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
