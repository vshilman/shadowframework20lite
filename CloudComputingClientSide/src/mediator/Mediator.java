package mediator;

import graphics.FileMenu;
import graphics.GameMenu;
import graphics.proxy.IProxyGraphic;
import graphics.proxy.LoggingPanel;
import graphics.proxy.ProxyGraphic;
import graphics.proxy.RoomsPanel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Substring;
import utils.User;
import connection.Connector;
import connection.tcp.client.ITCPClient;
import connection.tcp.client.TCPServiceClient;
import connection.tcp.server.TCPServersManager;

public class Mediator {


	private static final String SUCCESS = "Success!";
	private static final String NOBODY = "nobody";
	private static final String DISCONNECTED = "Disconnected!";
	private static Mediator med = new Mediator();
	private static FileMenu fileMenu;
	private static GameMenu gameMenu;
	private static Connector connection;
	private static ProxyGraphic proxyPanel;
	private static IProxyGraphic logPanel;
	private static IProxyGraphic roomsPanel;
	private static ITCPClient gamingClient;
	private static ITCPClient serviceClient;
	private static HashMap<String, String> tables;
	private static JFrame frame;
	private static User user;
	private static User me;
	private static Thread thread;
	private static HashMap<String, User> serviceMap;
	private static HashMap<String, User> playersMap;
	private static HashMap<String, User> visitorsMap;
	private static HashMap<String, User> onlinePlayers;
	private static TCPServersManager serverManager;
	private static String pass;
	
	public Mediator() {
	}

	static {
		pass="";
		me= new User("", "", "java");
		tables=new HashMap<String,String>();
		frame= new JFrame();
		logPanel= new LoggingPanel();
		roomsPanel= new RoomsPanel();
		connection=new Connector("localhost");
		fileMenu= new FileMenu(frame, connection);
		gameMenu= new GameMenu();
		proxyPanel= new ProxyGraphic(logPanel);
		serverManager= new TCPServersManager();
		serviceMap= new HashMap<String, User>();
		playersMap= new HashMap<String, User>();
		visitorsMap= new HashMap<String, User>();
		onlinePlayers= new HashMap<String, User>();
	}

	public FileMenu getFileMenu(){
		return fileMenu;
	}
	public GameMenu getGameMenu(){
		return gameMenu;
	}
	public JFrame getFrame(){
		return frame;
	}
	public HashMap<String, User> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(HashMap<String, User> serviceMap) {
		Mediator.serviceMap = serviceMap;
	}
	
	
	public Connector getConnection() {
		return connection;
	}

	public JPanel getMainPanel() {
		return proxyPanel.setUpPanel();
	}

	public static Mediator getMed() {
		return med;
	}

	public void validateLogin(String ans, String nick, String pass){
		if (ans.equals(SUCCESS)) {
			setNick(nick);
			setPass(pass);
			connection.getWelcome();
			serverManager.openServiceServer();
		}else{
			JDialog dialog= new JDialog();
			dialog.add(new JLabel(ans));
			dialog.setSize(300,100);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	}
	
	public void checkAns(String ans){
		if (ans.equals(DISCONNECTED)) {
			changePanel(logPanel);
		}
	}
	
	public void checkAns(List<String> answer) {
		
		if (answer.get(0).equals(NOBODY)) {
			connection.setMyselfAsWelcomer();
			serviceMap.put("welcome", me);
			serviceMap.put("dealer", me);
			changePanel(roomsPanel);
		}else if(answer.get(2).equals("java")) {
			serviceMap.put("welcomer", generateUser(answer));
			System.out.println(serviceMap.get("welcomer").getIp());
			serviceClient=new TCPServiceClient(serviceMap.get("welcomer").getIp());
			serviceClient.send("WHO_IS_DEALER");
			serviceMap.put("dealer", (User)serviceClient.getAnswer());
			serviceClient.closeConnection();
			serviceClient=new TCPServiceClient(serviceMap.get("dealer").getIp());
			serviceClient.send("GET_TABLES_MAP");
			tables.putAll((HashMap<String, String>)serviceClient.getAnswer());
			changePanel(roomsPanel);
		}else if (answer.get(2).equals("html")) {
			connection.setMyselfAsWelcomer();
			serviceMap.put("welcomer", me);
			//TODO: CONTACT WELCOMER AND ASK FOR DEALER
			//come sopra
			changePanel(roomsPanel);
		}
		
		
	}
	
	public void logout(){
//		thread.interrupt();
		if (me.getNick().isEmpty()) {
			JDialog dialog= new JDialog();
			dialog.add(new JLabel("You must be connected!"));
			dialog.setSize(300,100);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}else{
			connection.login(me.getNick(), pass);
			changePanel(logPanel);
			setNick("");
			setPass("");
			serverManager.closeServiceServer();
		}
	}
//	private void activateDeamon(){
//		thread.setDaemon(true);
//		thread.start();
//	}
	
	public HashMap<String, String> getTables(){
		return tables;
	}

	public void addNewTable(String tableName){
		
		User dealer=serviceMap.get("dealer");
		String tableNameComposed=tableName+"$$||$$"+me.getNick();
		serviceMap.put(tableNameComposed, me);
		if (!me.getNick().equals(dealer.getNick())) {
			serviceClient=new TCPServiceClient(dealer.getIp());	
			serviceClient.send("NEW_TABLE"+tableNameComposed/*+NOME DEL TAVOLO*/);
			tables.putAll((HashMap<String, String>)serviceClient.getAnswer());
			proxyPanel.refreshPanel();
			serviceClient.closeConnection();
			//TODO:EVENTUALMENTE DA FINIRE
		}else {
			tables.put(tableNameComposed, me.getNick());
			Set<String> nickOnline=onlinePlayers.keySet();
			proxyPanel.refreshPanel();
			for (Iterator iterator = nickOnline.iterator(); iterator.hasNext();) {
				String nick = (String) iterator.next();
				serviceClient=new TCPServiceClient(onlinePlayers.get(nick).getIp());
				serviceClient.send("UPDATE_TABLE_MAP"+tables);
				serviceClient.closeConnection();
			}
			
		}
		
		
		
	}
	public String getNick() {
		return me.getNick();
	}
	
	public void addPlayer(User player){
		playersMap.put("Player"+(playersMap.size()+1), player);
	}
	public void addVisitor(User visitor){
		playersMap.put("Visitor"+(playersMap.size()+1), visitor);
	}

	public HashMap<String, User> getPlayersMap() {
		return playersMap;
	}
	public HashMap<String, User> getVisitorsMap() {
		return visitorsMap;
	}
	private void changePanel(IProxyGraphic panel) {
		proxyPanel.setPanel(panel);
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().add(proxyPanel.setUpPanel());
		frame.getContentPane().validate();
	}


	private void setPass(String pass) {
		Mediator.pass = pass;
	}
	private void setNick(String nick) {
		me.setNick(nick);
	}
	private User generateUser(List<String> infos){
		return new User(infos.get(0), infos.get(1), infos.get(2));
	}
	
}
