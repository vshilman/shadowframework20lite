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
			Mediator.getGMed().setChoosePanel();
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
			
			Mediator.getGMed().setChoosePanel();
			
						
						
		}else if (user.getPlatform().equals(HTML)) {
			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
			tableMap.putAll(Mediator.getCMed().getConnection().getTablesMap());
			serviceMap.put(WELCOMER, me);
			serviceMap.put(DEALER, me);
			
			Mediator.getGMed().setChoosePanel();
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
	
//	public void checkRequest(String message, Table table){
//		if (message.equals(NEW_TABLE)) {
//			tableMap.put(table.getId(), table);
//			Set<String> keys=onlineMap.keySet();
//			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
//				String string = (String) iterator.next();
//				Mediator.getCMed().sendRequestOnService(onlineMap.get(string).getIp(), Mediator.getMed().getCoder().convert(UPDATE_TABLE_MAP), Mediator.getMed().getCoder().convertTableMap(tableMap));
//			}
//		}
//	}
	
	
//	public void checkRequest(List<String> messageList){
//		//TODO: DO SOMETHING
//		
//		
//	}
	
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
			
		}else if (me.getGame().equals("Briscola")){
			generateID();
			r= new Random(ID);
			playersMap= new HashMap<String, User>();
			String tableNameComposed=ID+tableName;
			List<String> playersNames= new ArrayList<String>();
			playersNames.add(me.getNick());
			int numberOfPlayers=0;
			if (me.getGame().equals("Briscola")) {
				numberOfPlayers=4;
			}else {
				numberOfPlayers=2;
			}
			Table newTable= generateTable(tableNameComposed, ID, me.getGame(), numberOfPlayers, playersNames, spectable, me.getNick());
			serviceMap.put(newTable.getId()+me.getNick(), me);

			tableMap.put(newTable.getId(), newTable);
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
			objectsToSend.clear();
			
			
		}else if (me.getGame().equals("Memory")){
		//TODO: simile alla briscolazza
		}
	}
	
	public Table generateTable(String name, int id, String game, int playersSupported, List<String> playersList, boolean spectable, String manager){
		return new Table(name, id, game, playersSupported, playersList, spectable, manager);
	}
	
	public void enterTable(String tableName, String type){
		
		if (type.equals(PLAY)&&isFree(tableName)) {
			objectsToSend.add(ENTER_TABLE);
			objectsToSend.add(tableName);
			Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
			objectsToSend.clear();
			String ans=(String)Mediator.getCMed().getAns();

			if (ans.equals(OK)) {
				
				objectsToSend.add(WHO_IS_MANAGER);
				objectsToSend.add(tableName);
				Mediator.getCMed().sendRequestOnService(DEALER, objectsToSend);
				serviceMap.put(MANAGER, (User)Mediator.getCMed().getAns());
				objectsToSend.clear();
				
				objectsToSend.add(ADD_NEW_PLAYER);
				objectsToSend.add(me);
				Mediator.getCMed().sendRequestOnService(serviceMap.get(MANAGER).getIp(), objectsToSend);
				objectsToSend.clear();
				
				playersMap.clear();
				playersMap.putAll((HashMap<String,User>) Mediator.getCMed().getAns());
				
				Substring sub= new Substring(tableName, SEPARATOR);
				sub.nextSubString();
				Mediator.getGMed().setGamePanel();

			}else {
				Mediator.getGMed().generateDialog(" "+ans);
			}
			
			
			
		}else if (type.equals(SPECTATE)) {
			//TODO:dire cosa avviene alla pressione di Spectate!
		}
		
		
		
	}

	
	private boolean isFree(String tableName){
		
		if (freePlaceMap.get(tableName)<4) {
			return true;
		}
		
		return false;
	}
	
	public HashMap<String, Boolean> getTables(String gameType){
		HashMap<String, Boolean> requestedTables=new HashMap<String, Boolean>();
		Set<Integer> keySet= tableMap.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Integer id = (Integer) iterator.next();
			if (tableMap.get(id).getGame().equals(gameType)) {
				requestedTables.put(tableMap.get(id).getName()+"$$||$$"+me.getNick(), tableMap.get(id).isEmpty());
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
