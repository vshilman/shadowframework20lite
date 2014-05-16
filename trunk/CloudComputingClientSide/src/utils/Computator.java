package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mediator.Mediator;

public class Computator {
	private static final String UPDATE_TABLE_MAP = "UPDATE_TABLE_MAP";
	private static final String NEW_TABLE = "NEW_TABLE";
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
	private String pass;
	private List<Object> objectsToSend;
	private HashMap<String, User> serviceMap;
	private HashMap<String, User> playersMap;
	private HashMap<String, User> visitorsMap;
	private HashMap<String, String> tables;

	

	
	public Computator() {
		objectsToSend=new ArrayList<Object>();
		pass="";
		me=new User("", "", JAVA);
		tables= new HashMap<String,String>();
		serviceMap=new HashMap<String, User>();
		playersMap=new HashMap<String, User>();
		visitorsMap=new HashMap<String, User>();
	}
	
	public void validateLogin(String ans, String nick, String pass){
		if (ans.equals(SUCCESS)) {
			setNick(nick);
			setPass(pass);
			Mediator.getCMed().getConnection().getWelcome();
			Mediator.getCMed().getServerManager().openServiceServer();
		}else{
			Mediator.getGMed().generateDialog("  "+ans);
		}
	}
	public void validateLogout(){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  You must be connected!");
		}else{
			if (me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				//TODO: DESIGNARE NUOVO DEALER
				Mediator.getCMed().getConnection().login(me.getNick(), pass);
				Mediator.getGMed().setLoginPanel();
				setNick("");
				setPass("");
				Mediator.getCMed().getServerManager().closeServiceServer();
				
			}else{
				objectsToSend.add(REMOVE_ME);
				objectsToSend.add(me.getNick());
				Mediator.getCMed().sendOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
				String ans=(String)Mediator.getCMed().getAns();
				if (ans.equals(OK)) {
					Mediator.getCMed().getConnection().login(me.getNick(), pass);
					Mediator.getGMed().setLoginPanel();
					setNick("");
					setPass("");
					Mediator.getCMed().getServerManager().closeServiceServer();
				}
				objectsToSend.clear();
				
			}
		}
	}
	
	public void checkAns(List<String> answer) {
		
		if (answer.get(0).equals(NOBODY)) {
			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
			serviceMap.put(WELCOMER, me);
			serviceMap.put(DEALER, me);
			Mediator.getGMed().setRoomsPanel();
		}else if(answer.get(2).equals(JAVA)) {
			serviceMap.put(WELCOMER, me);
			serviceMap.put(WELCOMER, generateUser(answer));
			objectsToSend.add(WHO_IS_DEALER);
			Mediator.getCMed().sendOnService(serviceMap.get(WELCOMER).getIp(), objectsToSend);
			objectsToSend.clear();
			serviceMap.put(DEALER, (User)Mediator.getCMed().getAns());
			objectsToSend.add(GET_TABLES_MAP);
			Mediator.getCMed().sendOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
			objectsToSend.clear();

			
			tables.putAll((HashMap<String, String>)Mediator.getCMed().getAns());
			Mediator.getGMed().setRoomsPanel();
		}else if (answer.get(2).equals(HTML)) {
			Mediator.getCMed().getConnection().setMyselfAsWelcomer();
			serviceMap.put(WELCOMER, me);
			//TODO: CONTACT WELCOMER AND ASK FOR DEALER
			//come sopra
			Mediator.getGMed().setRoomsPanel();
		}
		
		
	}
	
	public void addNewTable(String tableName){
		if (me.getNick().isEmpty()) {
			Mediator.getGMed().generateDialog("  Please, login first!");
			
		}else{
			String tableNameComposed=tableName+"$$||$$"+me.getNick();
			serviceMap.put(tableNameComposed, me);
			objectsToSend.add(NEW_TABLE);
			objectsToSend.add(tableNameComposed);
			if (!me.getNick().equals(serviceMap.get(DEALER).getNick())) {
				Mediator.getCMed().sendOnService(serviceMap.get(DEALER).getIp(), objectsToSend);
				tables.put(tableNameComposed,me.getNick());
				Mediator.getGMed().refreshPanel();
				//TODO:EVENTUALMENTE DA FINIRE
			}else {
				tables.put(tableNameComposed, me.getNick());
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

	public HashMap<String, String> getTables(){
		return tables;
	}

	public String getNick(){
		return me.getNick();
	}
	private void setPass(String pass) {
		this.pass = pass;
	}
	private void setNick(String nick) {
		me.setNick(nick);
	}
	private User generateUser(List<String> infos){
		return new User(infos.get(0), infos.get(1), infos.get(2));
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

	public HashMap<String, User> getPlayersMap() {
		return playersMap;
	}
	public HashMap<String, User> getVisitorsMap() {
		return visitorsMap;
	}
}
