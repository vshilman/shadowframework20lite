package generic;

import io.IReader;
import io.IWriter;
import io.Reader;
import io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Incapsulator;

public class Mediator {
	private static final String NOBODY = "nobody";
	private static Mediator med= new Mediator();
	private static HashMap<String, User> usersMap;
	private static List<String> welcomeUser= new ArrayList<String>();
	private HashMap<String, List<String>> onlineMap= new HashMap<String, List<String>>();
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
		welcomeUser.add(NOBODY);
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
		
		welcomeUser.clear();
		welcomeUser.add(targetNick);
		welcomeUser.add(onlineMap.get(targetNick).get(0));
		welcomeUser.add(onlineMap.get(targetNick).get(1));
		
		
	}
	
	public List<String> getWelcomeUser() {
		
		return welcomeUser;
	}
	
	public void addOnline(String nick, String ip, int port, String game, String platform){
		List<String> status= new ArrayList<String>();
		status.add(ip);
		status.add(platform);
		status.add(game);
		onlineMap.put(nick, status);
	}
	public void changeGame(String nick, String game){
		onlineMap.get(nick).set(2, game);
	}
	public void removeOnline(String nick){
		onlineMap.remove(nick);
		if (welcomeUser.get(0).equals(nick)) {
			welcomeUser.clear();
			welcomeUser.add(NOBODY);
		}
	}
	public boolean isOnline(String nick){
		return onlineMap.containsKey(nick);
	}
	public HashMap<String, List<String>> getOnline(){
		return onlineMap;
	}
	public String getXmlOnlinePlayers(){
		return converter.convert(onlineMap);
	}
	private static void generateUserMap(){
		for (int i = 0; i < usersList.size(); i++) {
			usersMap.put(usersList.get(i).getNickname(), usersList.get(i));
		}
	}
	
}
