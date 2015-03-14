package generic;

import io.IReader;
import io.IWriter;
import io.Reader;
import io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Incapsulator;
import utils.Table;

public class Mediator {
	private static final String NOBODY = "nobody";
	private static final String NULL = "null";
	private static Mediator med= new Mediator();
	private static HashMap<String, User> usersMap;
	private static GamingUser welcomeUser;
	private static HashMap<String, GamingUser> onlineMap;
	private static HashMap<Integer, Table> tablesMap;
	private static IReader reader;
	private static IWriter writer;
	private static String path;
	private User user;
	private static List<User> usersList;
	private static Incapsulator converter;
	private static String[] games={"Briscola", "Memory"};

	private Mediator() {
	}
	
	static{
		
		converter=new Incapsulator();
		path="ccomdata/dataUsers.txt";
		reader= new Reader(path);
		writer= new Writer(path);
		usersList=reader.getUserList();
		usersMap= new HashMap<String, User>();
		onlineMap= new HashMap<String, GamingUser>();
		tablesMap= new HashMap<Integer, Table>();
		welcomeUser= new GamingUser(NOBODY, NULL, NULL, NULL);
		generateUserMap();
		
	}
	
	public User generateUser(String name, String surname,String nickname,String email,String pass) {
		
		user= new User(name, surname, nickname, email, pass);

		return user;
	}
	
	public static Mediator getMed() {
		return med;
	}
	public String[] getGames() {
		return games;
	}
	public List<User> getUsersList(){
		return usersList;
	}
	public HashMap<String, User> getUsersMap() {
		return usersMap;
	}

	public void saveUsers(){
		writer.writeUsers(usersList);
	}
	
	public void setWelcomeUser(String targetNick){
		welcomeUser.setNick(targetNick);
		welcomeUser.setIp(onlineMap.get(targetNick).getIp());
		welcomeUser.setPlatform(onlineMap.get(targetNick).getPlatform());
		welcomeUser.setGame(onlineMap.get(targetNick).getGame());
	}
	
	public String getWelcomeUser() {
		
		return converter.convert(welcomeUser);
	}
	
	public String getXmlUser(String nick) {
		return converter.convert(onlineMap.get(nick));
	}
	
	public void addOnline(String nick, String ip, String game, String platform){
		List<String> status= new ArrayList<String>();
		status.add(ip);
		status.add(platform);
		status.add(game);
		onlineMap.put(nick, generateGamingUser(nick, ip, platform, game));
	}
	public void changeGame(String nick, String game){
		onlineMap.get(nick).setGame(game);
	}
	public void removeOnline(String nick){
		onlineMap.remove(nick);
		if (welcomeUser.getNick().equals(nick)) {
			welcomeUser.setNick(NOBODY);
			welcomeUser.setIp(NULL);
			welcomeUser.setPlatform(NULL);
			welcomeUser.setGame(NULL);
		}
	}
	public String getXmlTableMap(){
		return converter.convertTableMap(tablesMap);
	}
	public boolean isOnline(String nick){
		return onlineMap.containsKey(nick);
	}
	public HashMap<String, GamingUser> getOnline(){
		return onlineMap;
	}
	public String getXmlOnlinePlayers(){
		return converter.convert(onlineMap);
	}
	public String codeMessage(String messageToCode){
		return converter.convert(messageToCode);
	}
	private GamingUser generateGamingUser(String nick, String ip, String platform, String game){
		return new GamingUser(nick, ip, platform, game);
	}
	private static void generateUserMap(){
		for (int i = 0; i < usersList.size(); i++) {
			usersMap.put(usersList.get(i).getNickname(), usersList.get(i));
		}
	}
	
}
