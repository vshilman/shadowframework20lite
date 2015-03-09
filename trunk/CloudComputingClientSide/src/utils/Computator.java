package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mediator.Mediator;

public class Computator {
	private static final String WHO_IS_MANAGER = "who_is_manager";
	private static final String ADD_NEW_PLAYER = "add_new_player";
	private static final String MANAGER = "manager";
	private static final String UPDATE_DEALER="update_dealer";
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
	private HashMap<String, Boolean> tables;
	private HashMap<String, Integer> freePlaceMap;

	private Random r;
	private List<Integer> carteLibere=new ArrayList<Integer>();
	private int ID=0;


	
	public Computator() {
		objectsToSend=new ArrayList<Object>();
		me=new User("", "", JAVA,"");
		tables= new HashMap<String,Boolean>();
		serviceMap=new HashMap<String, User>();
//		playersMap=new HashMap<String, User>();
		visitorsMap=new HashMap<String, User>();
		freePlaceMap= new HashMap<String, Integer>();
	}
	
	public void validateLogin(String ans, String nick, String game){
		if (ans.equals(SUCCESS)) {
			me.setNick(nick);
			me.setGame(game);
			Mediator.getCMed().getConnection().getWelcome();
			Mediator.getCMed().openServiceServer();
		}else{
			Mediator.getGMed().generateDialog("  "+ans);
		}
	}
	
	public void validateLogout(){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  You must be connected!");
		}else{
			if (me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				HashMap<String, User> map= new HashMap<String, User>();
				if (me.getNick().equals(serviceMap.get(WELCOMER).getNick())) {
					Mediator.getCMed().getConnection().logout(me.getNick());
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}else {
					Mediator.getCMed().getConnection().getOnlineUsers();
					map.putAll(Mediator.getCMed().getOnlinePlayers());
					if (map.size()==2) {
						objectsToSend.add(serviceMap.get(WELCOMER));
						Mediator.getCMed().sendRequestOnService(UPDATE_DEALER, objectsToSend);
						objectsToSend.clear();
					}else {
						map.remove(serviceMap.get(DEALER).getNick());
						map.remove(serviceMap.get(WELCOMER).getNick());
						Set<String> keySet=map.keySet();
						String nick=keySet.iterator().next();
						objectsToSend.add(UPDATE_DEALER);
						objectsToSend.add(map.get(nick));
						Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(),objectsToSend);
						objectsToSend.clear();
						
					}
					Mediator.getCMed().getConnection().logout(me.getNick());
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}
				
				
			}else{
				
				Mediator.getCMed().getConnection().logout(me.getNick());
				objectsToSend.add(REMOVE_ME);
				objectsToSend.add(me.getNick());
				Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
				String ans=(String)Mediator.getCMed().getAns();
				if (ans.equals(OK)) {
					Mediator.getGMed().setLoginPanel();
					me.setNick("");
					Mediator.getCMed().closeServiceServer();
				}
				objectsToSend.clear();
				
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
			objectsToSend.add(WHO_IS_DEALER);
			Mediator.getCMed().sendRequestOnService(user.getIp(), objectsToSend);
			objectsToSend.clear();
			User tempDealer=(User)Mediator.getCMed().getAns();
			
			if (serviceMap.get(WELCOMER).getNick().equals(tempDealer.getNick())) {
				objectsToSend.add(GET_TABLES_MAP);
				Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
				objectsToSend.clear();
				tables.putAll((HashMap<String, Boolean>)Mediator.getCMed().getAns());
				
				objectsToSend.add(UPDATE_DEALER);
				objectsToSend.add(me);
				Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
				serviceMap.put(DEALER, me);
				objectsToSend.clear();
				
				
				
			}else {
							serviceMap.put(DEALER, tempDealer);
							objectsToSend.add(GET_TABLES_MAP);
							Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
							objectsToSend.clear();
							tables.putAll((HashMap<String, Boolean>)Mediator.getCMed().getAns());
						}
						Mediator.getGMed().setChoosePanel();
			
						
						
					}else if (answer.get(2).equals(HTML)) {
						Mediator.getCMed().getConnection().setMyselfAsWelcomer();
						serviceMap.put(WELCOMER, me);
						//TODO: CONTACT WELCOMER AND ASK FOR DEALER
						//come sopra
						Mediator.getGMed().setChoosePanel();
					}
		}
		
//		if (answer.get(0).equals(NOBODY)) {
//			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
////			System.out.println("setto welcomer: me stesso");
//			serviceMap.put(WELCOMER, me);
//			serviceMap.put(DEALER, me);
////			Mediator.getCMed().getConnection().getOnlineUsers();
//			Mediator.getGMed().setChoosePanel();
////			Mediator.getGMed().setRoomsPanel();
//		}else if(answer.get(2).equals(JAVA)) {
//			serviceMap.put(WELCOMER, generateUser(answer));
//
//			objectsToSend.add(WHO_IS_DEALER);
//			Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
//			objectsToSend.clear();
//			User tempDealer=(User)Mediator.getCMed().getAns();
//			if (serviceMap.get(WELCOMER).getNick().equals(tempDealer.getNick())) {
//				objectsToSend.add(GET_TABLES_MAP);
//				Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
//				objectsToSend.clear();
//				tables.putAll((HashMap<String, Boolean>)Mediator.getCMed().getAns());
//				
//				objectsToSend.add(UPDATE_DEALER);
//				objectsToSend.add(me);
//				Mediator.getCMed().sendRequestOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
//				serviceMap.put(DEALER, me);
//				objectsToSend.clear();
//				
//
//
//			}else {
//				serviceMap.put(DEALER, tempDealer);
//				objectsToSend.add(GET_TABLES_MAP);
//				Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
//				objectsToSend.clear();
//				tables.putAll((HashMap<String, Boolean>)Mediator.getCMed().getAns());
//			}
//			Mediator.getGMed().setChoosePanel();
//
//			
//			
//		}else if (answer.get(2).equals(HTML)) {
//			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
//			serviceMap.put(WELCOMER, me);
//			//TODO: CONTACT WELCOMER AND ASK FOR DEALER
//			//come sopra
//			Mediator.getGMed().setChoosePanel();
//		}
		
	}
	
	public void checkRequest(List<Object> request){
		String action=(String)request.get(0);
		if (action.equals(WHO_IS_DEALER)) {
			Mediator.getCMed().sendAnswerOnService(serviceMap.get(DEALER));
		}else if (action.equals(GET_TABLES_MAP)) {
			Mediator.getCMed().sendAnswerOnService(tables);
		}else if (action.equals(NEW_TABLE)) {
			String addingTable=(String)request.get(1);
			freePlaceMap.put(addingTable, 1);
			tables.put(addingTable, true);
			Mediator.getGMed().refreshPanel();
			Mediator.getCMed().sendAnswerOnService(tables);
		}else if (action.equals(REMOVE_ME)) {
			while (Mediator.getCMed().getOnlinePlayers().containsKey((String)(request.get(1)))) {
				Mediator.getCMed().getConnection().getOnlineUsers();
			}
			Mediator.getCMed().sendAnswerOnService(OK);
		}else if (action.equals(UPDATE_TABLE_MAP)) {
			tables.putAll((HashMap<String, Boolean>)request.get(1));
			Mediator.getCMed().sendAnswerOnService(OK);
			Mediator.getGMed().refreshPanel();
		}
	}
	
	public void addNewTable(String tableName){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  Please, login first!");
			
		}else{
			compilaCarte();
			generateID();
			r= new Random(ID);
			playersMap= new HashMap<String, User>();
			String tableNameComposed=tableName+SEPARATOR+me.getNick()+SEPARATOR+ID;
			
//			for (int i = 0; i < 40; i++) {
//
////				System.out.println("------->"+i);
//				System.out.println(nextCard());
//
//			}
			//TODO: finire
		
			serviceMap.put(tableNameComposed, me);
			freePlaceMap.put(tableNameComposed, 0);
			objectsToSend.add(NEW_TABLE);
			objectsToSend.add(tableNameComposed);
			if (!me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				Mediator.getCMed().sendRequestOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
				tables.putAll((HashMap<String, Boolean>)Mediator.getCMed().getAns());
				Mediator.getGMed().refreshPanel();
				//TODO:EVENTUALMENTE DA FINIRE
			}else {
				tables.put(tableNameComposed, isFree(tableNameComposed));
				objectsToSend.add(UPDATE_TABLE_MAP);
				objectsToSend.add(tables);
				Mediator.getCMed().updateUsers(objectsToSend);
				objectsToSend.clear();
				Mediator.getGMed().refreshPanel();
				//TODO ATTENZIONE
			}
			objectsToSend.clear();
			
			
		}
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
		//TODO: scegliere il dealer giusto
		return tables;
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
	private int nextCard(){
		
//		double ID=0;
//		ID=Math.random();
		int index=r.nextInt(carteLibere.size());
		int number=carteLibere.get(index);
		carteLibere.remove(index);
		
		
		return number;
	}
	private void compilaCarte(){
		for (int i = 1; i < 41; i++) {
			carteLibere.add(i);
		}
	}
}
