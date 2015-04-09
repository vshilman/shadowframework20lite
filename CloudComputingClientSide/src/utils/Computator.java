package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.jws.Oneway;
import javax.print.attribute.standard.Media;

import mediator.Mediator;

public class Computator {
	private static final String GET_PLAYERS_MAP = "get_players_map";
	private static final String FULL = "full";
	private static final String WHO_IS_MANAGER = "who_is_manager";
	private static final String ADD_NEW_PLAYER = "add_new_player";
	private static final String MANAGER = "manager";
	private static final String UPDATE_DEALER="update_dealer";
	private static final String UPDATE_WELCOMER="update_welcomer";
	private static final String ENTER_TABLE = "enter_table";
	private static final String SEPARATOR = "$$||$$";
	private static final String SPECTATE = "spectate";
	private static final String PLAY = "play";
	private static final String UPDATE_TABLE_MAP = "UPDATE_TABLE_MAP";
	private static final String NEW_TABLE = "NEW_TABLE";
	/*fino a qui è il nuovo codice da sistemare*/
	private static final String OK = "OK";
	private static final String REMOVE_ME = "REMOVE_ME";
	private static final String JAVA = "java";
	private static final String WELCOMER = "welcomer";
	private static final String HTML = "html";
	private static final String GET_TABLES_MAP = "GET_TABLES_MAP";
	private static final String DEALER = "dealer";
	private static final String WHO_IS_DEALER = "WHO_IS_DEALER";
	private static final String SUCCESS = "Success!";
	private static final String NOBODY = "nobody";
	private User me;
	private List<Object> objectsToSend;
	private HashMap<String, User> serviceMap;
	private HashMap<String, User> playersMap;
	private HashMap<String, User> visitorsMap;
	private HashMap<String, User> onlineMap;
	private HashMap<Integer, Table> tableMap;
	private HashMap<String, Integer> freePlaceMap;
	private Table actualTable;

	private Random r;
	private List<Integer> carteLibere=new ArrayList<Integer>();
	private int ID=0;


	
	public Computator() {
		objectsToSend=new ArrayList<Object>();
		me=new User("", "", JAVA,"");
		tableMap= new HashMap<Integer,Table>();
		serviceMap=new HashMap<String, User>();
		onlineMap= new HashMap<String, User>();
//		playersMap=new HashMap<String, User>();
		visitorsMap=new HashMap<String, User>();
		freePlaceMap= new HashMap<String, Integer>();
	}
	
	public void validateLogin(String ans, String nick, String game){
		if (ans.equals(SUCCESS)) {
			me.setNick(nick);
			me.setGame(game);
			Mediator.getCMed().openServiceServer();
			Mediator.getCMed().getConnection().getOnlineUsers();
			Mediator.getCMed().getConnection().getWelcome();
		}else{
			Mediator.getGMed().generateDialog("  "+ans);
		}
	}
	
	public void validateLogout(){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  You must be connected!");
		}else{
			HashMap<String, User> map= new HashMap<String, User>();
			
			if (me.getNick().equals(serviceMap.get(WELCOMER).getNick())) {
			
				if (me.getNick().equals(serviceMap.get(DEALER).getNick())) {
					Mediator.getCMed().getConnection().logout(me.getNick());
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}else {
					Mediator.getCMed().getConnection().getOnlineUsers();
					map.putAll(Mediator.getCMed().getOnlinePlayers());
					if (map.size()==2) {
						Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), Mediator.getMed().getCoder().convert(UPDATE_WELCOMER), Mediator.getMed().getCoder().convert(serviceMap.get(DEALER)));
					}else {
						map.remove(serviceMap.get(DEALER).getNick());
						map.remove(serviceMap.get(WELCOMER).getNick());
						Set<String> keySet=map.keySet();
						String nick=keySet.iterator().next();
						Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), Mediator.getMed().getCoder().convert(UPDATE_WELCOMER), Mediator.getMed().getCoder().convert(map.get(nick)));
							
					}
					Mediator.getCMed().getConnection().logout(me.getNick());
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}
			
			}else if (me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				Mediator.getCMed().getConnection().getOnlineUsers();
				map.putAll(Mediator.getCMed().getOnlinePlayers());
				if (map.size()==2) {
					Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), Mediator.getMed().getCoder().convert(UPDATE_DEALER), Mediator.getMed().getCoder().convert(serviceMap.get(WELCOMER)));
				}else {
					map.remove(serviceMap.get(DEALER).getNick());
					map.remove(serviceMap.get(WELCOMER).getNick());
					Set<String> keySet=map.keySet();
					String nick=keySet.iterator().next();
					Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), Mediator.getMed().getCoder().convert(UPDATE_DEALER), Mediator.getMed().getCoder().convert(map.get(nick)));
						
				}
				Mediator.getCMed().getConnection().logout(me.getNick());
				Mediator.getGMed().setLoginPanel();
				me.setNick("");
				Mediator.getCMed().closeServiceServer();

			}else{
				
				Mediator.getCMed().getConnection().logout(me.getNick());
				Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), Mediator.getMed().getCoder().convert(REMOVE_ME));
				String ans=Mediator.getCMed().getAns();
				if (ans.equals(OK)) {
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}else {
					Mediator.getGMed().generateDialog(ans);
				}
				
			}
		}
	}
	
	
	public void checkAns(List<String> answer) {
		
		
	}
	public void checkAns(HashMap<String, List<String>> answer) {
		
		
	}
	public void checkAns(User user) {
		
		if (user.getNick().equals(NOBODY)) {
			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
			serviceMap.put(WELCOMER, me);
			serviceMap.put(DEALER, me);
			Mediator.getGMed().setRoomsPanel(me.getGame());
		}else if (user.getPlatform().equals(JAVA)) {

			serviceMap.put(WELCOMER, user);
			Mediator.getCMed().sendRequestOnService(user.getIp(), Mediator.getMed().getCoder().convert(WHO_IS_DEALER));
			User dealer=Mediator.getMed().getDecoder().decodeUser(Mediator.getCMed().getAns());
			
			if (user.getNick().equals(dealer.getNick())) {
				
				objectsToSend.add(UPDATE_DEALER);
				objectsToSend.add(me);
				Mediator.getCMed().sendRequestOnService(user.getIp(), Mediator.getMed().getCoder().convert(UPDATE_DEALER), Mediator.getMed().getCoder().convert(me));
				serviceMap.put(DEALER, me);
				objectsToSend.clear();
				
				
				
			}else {
				serviceMap.put(DEALER, dealer);
			}
		
			Mediator.getCMed().sendRequestOnService(user.getIp(), Mediator.getMed().getCoder().convert(GET_TABLES_MAP));

			tableMap.putAll(Mediator.getMed().getDecoder().decodeTableMap(Mediator.getCMed().getAns()));
			
			Mediator.getGMed().setRoomsPanel(me.getGame());			
						
						
		}else if (user.getPlatform().equals(HTML)) {
			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
			tableMap.putAll(Mediator.getCMed().getConnection().getTablesMap());
			serviceMap.put(WELCOMER, me);
			serviceMap.put(DEALER, me);
			
			Mediator.getGMed().setRoomsPanel(me.getGame());
			}
	}
	
	
	public void checkRequest(String message){
		if (message.equals(WHO_IS_DEALER)) {
			Mediator.getCMed().sendAnswerOnService(Mediator.getMed().getCoder().convert(serviceMap.get(DEALER)));
		}else if (message.equals(GET_TABLES_MAP)) {
			Mediator.getCMed().sendAnswerOnService(Mediator.getMed().getCoder().convertTableMap(tableMap));
		}else if (message.equals("unknown")) {
			//DO NOTHING
		}
	}
	
	public void checkRequest(String message, User user){
		if (message.equals(UPDATE_DEALER)) {
			serviceMap.put(DEALER, user);
			Mediator.getCMed().sendAnswerOnService(Mediator.getMed().getCoder().convert(OK));
		}else if (message.equals(UPDATE_WELCOMER)) {
			serviceMap.put(WELCOMER, user);
			Mediator.getCMed().sendAnswerOnService(Mediator.getMed().getCoder().convert(OK));
		}else if (message.equals(REMOVE_ME)) {
			onlineMap.remove(user.getNick());
			Mediator.getCMed().sendAnswerOnService(Mediator.getMed().getCoder().convert(OK));
			Mediator.getCMed().setOnlineMap(onlineMap);
			Mediator.getCMed().updateOnlineMap(onlineMap);
		}
	}
	
	public void checkRequest(String message, int id, String ip){
		if (message.equals(ENTER_TABLE)) {
			if (tableMap.get(id).getPlayersSupported()>tableMap.get(id).getPlayersList().size()) {
				tableMap.get(id).getPlayersList().add(ip);
				Mediator.getCMed().sendRequestOnService(onlineMap.get(ip).getIp(), Mediator.getMed().getCoder().convert(serviceMap.get(""+id)));
			}else {
				Mediator.getCMed().sendRequestOnService(onlineMap.get(ip).getIp(), Mediator.getMed().getCoder().convert(FULL));
			};
			Set<String> keys=onlineMap.keySet();
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				Mediator.getCMed().sendRequestOnService(onlineMap.get(string).getIp(), Mediator.getMed().getCoder().convert(UPDATE_TABLE_MAP), Mediator.getMed().getCoder().convertTableMap(tableMap));
			}
		}
	}
	
	
	public void checkRequest(List<String> messageList){
		//TODO: DO SOMETHING
		
		
	}
	
	public void checkRequest(String type, HashMap map){
		if (type.equals("online")) {
			this.onlineMap.putAll(map);
		}else if (type.equals("table")||type.equals(UPDATE_TABLE_MAP)) {
			this.tableMap.putAll(map);
			if (me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				Set<String> keys=onlineMap.keySet();
				for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					Mediator.getCMed().sendRequestOnService(onlineMap.get(string).getIp(), Mediator.getMed().getCoder().convert(UPDATE_TABLE_MAP), Mediator.getMed().getCoder().convertTableMap(tableMap));
				}
			}
		}
		
	}
	
	
	public void addNewTable(String tableName, boolean spectable){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  Please, login first!");
			
		}else if (me.getGame().equals("briscola")){
			generateID();
			r= new Random(ID);
			playersMap= new HashMap<String, User>();
			String tableNameComposed=tableName;
			List<String> playersNames= new ArrayList<String>();
			playersNames.add(me.getNick());
			int numberOfPlayers=0;
			if (me.getGame().equals("briscola")) {
				numberOfPlayers=4;
			}else {
				numberOfPlayers=2;
			}
			Table newTable= generateTable(tableNameComposed, ID, me.getGame(), numberOfPlayers, playersNames, spectable, me.getNick());
			serviceMap.put(""+newTable.getId(), me);

			tableMap.put(newTable.getId(), newTable);
			Mediator.getGMed().setGamePanel(me.getGame());
			actualTable=newTable;
			if (!me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(),Mediator.getMed().getCoder().convert(UPDATE_TABLE_MAP), Mediator.getMed().getCoder().convertTableMap(tableMap));
				tableMap.putAll(Mediator.getMed().getDecoder().decodeTableMap(Mediator.getCMed().getAns()));
				Mediator.getGMed().refreshPanel();
				//TODO:EVENTUALMENTE DA FINIRE
			}else {
				checkRequest(UPDATE_TABLE_MAP, tableMap);
				Mediator.getGMed().refreshPanel();
				//TODO ATTENZIONE
			}
			
			
		}else if (me.getGame().equals("Memory")){
		//TODO: simile alla briscolazza
		}
	}
	
	public Table generateTable(String name, int id, String game, int playersSupported, List<String> playersList, boolean spectable, String manager){
		return new Table(name, id, game, playersSupported, playersList, spectable, manager);
	}
	
	public void enterTable(int tableID, String action){
		actualTable=tableMap.get(tableID);
		if (action.equals(PLAY)) {
			
			Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), Mediator.getMed().getCoder().convert(ENTER_TABLE),Mediator.getMed().getCoder().convert(tableID));
			
			String ans=Mediator.getCMed().getAns();
			String kind=Mediator.getMed().getDecoder().whichMethodUse(ans);
			if (kind.equals("user")) {
				User manager=Mediator.getMed().getDecoder().decodeUser(ans);
				playersMap.clear();
				serviceMap.put(MANAGER, manager);
				Mediator.getCMed().sendRequestOnService(manager.getIp(), Mediator.getMed().getCoder().convert(GET_PLAYERS_MAP));
				playersMap.putAll(Mediator.getMed().getDecoder().decodeUsersMap(Mediator.getCMed().getAns()));
				Mediator.getGMed().setGamePanel(actualTable.getGame());

			}else {
				Mediator.getGMed().generateDialog(" "+Mediator.getMed().getDecoder().decodeMessage(ans));
			}
			
			
			
		}else if (action.equals(SPECTATE)) {
			//TODO:dire cosa avviene alla pressione di Spectate!
		}
		
		
		
	}
	public void exitTable(int tableID, String action){
		//TODO: Da sistemare!
		
	}
	public void startGame(){
		//TODO: Da sistemare!
	}
	public HashMap<Integer, Table> getTables(String gameType){
		HashMap<Integer, Table> requestedTables=new HashMap<Integer, Table>();
		Set<Integer> keySet= tableMap.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Integer id = (Integer) iterator.next();
			if (tableMap.get(id).getGame().equals(gameType)) {
				requestedTables.put(id,tableMap.get(id));
			}
		}
		return requestedTables;
	}

	public String getNick(){
		return me.getNick();
	}
	
	public User generateUser(List<String> infos){
		return new User(infos.get(0), infos.get(1), infos.get(2), "");
	}
	public void setUserGame(String game){
		me.setGame(game);
	}
	public void addPlayer(User player){
		playersMap.put("Player"+(playersMap.size()+1), player);
	}
	public void addVisitor(User visitor){
		playersMap.put("Visitor"+(playersMap.size()+1), visitor);
	}
	public HashMap<String, User> getServiceMap(){
		return serviceMap;
	}
	public boolean amILogged(){
		if (!me.getNick().isEmpty()) {
			return true;
		}
		return false;
	}

//	public HashMap<String, User> getPlayersMap() {
//		return playersMap;
//	}
	//TODO: USARE LA PLAYERSMAP PER OGNI TAVOLO: IMPLEMENTARE UNA MAPPA DI STRINGHE DI MAPPA PER QUESTO MOTIVO!
	public HashMap<String, User> getVisitorsMap() {
		return visitorsMap;
	}
	private void generateID(){
		ID=(int)(Math.random()*100000);
	}
	
}
