package connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import mediator.Mediator;
import utils.Table;
import utils.User;


public class Connector{

	
	private static final String GET_ONLINE_MAP_ACTION = "&action=getOnlineMap";
	private static final String SET_ME_AS_WELCOMER_ACTION = "&action=setMeAsWelcomer";
	private static final String GET_TABLES_MAP = "&action=getTablesMap";
	private String NICKHEAD= "nickname=";
	private String LOGINACTION="&action=login";
	private String LOGOUTACTION="&action=logout";
	private String PASSHEAD= "&password=";
	private String GAMEHEAD="&game=";
	private String PLATFORM= "&platform=java";
	private Thread updator;
	private User welcomer;
	private HttpURLConnection connection;
	private URL url;
	private String ans= new String();
	private String ip;

	public Connector(String ip) {
		this.ip=ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public void login(String user, String pass, String game) {
			
			try {
				url= new URL("http://"+ip+":8080/ccom/Login");
				connection=(HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("User-Agent", "Java");
				connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(NICKHEAD+user+PASSHEAD+pass+PLATFORM+GAMEHEAD+game+LOGINACTION);
				wr.flush();
				wr.close();
	//			System.out.println(NICKHEAD+user+PASSHEAD+pass+PLATFORM);
				ans="";
				BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
				ans= Mediator.getMed().getDecoder().decodeMessage(reader.readLine());
				
				reader.close();
				
				Mediator.getMed().getComputator().validateLogin(ans, user, game);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
	}
	
	
	public void logout(String nick) {
		
		try {
			url= new URL("http://"+ip+":8080/ccom/Login");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+nick+PLATFORM+LOGOUTACTION);
			wr.flush();
			wr.close();
//			System.out.println(NICKHEAD+user+PASSHEAD+pass+PLATFORM);
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ans=Mediator.getMed().getDecoder().decodeMessage(reader.readLine());
			reader.close();
			
//			Mediator.getMed().getComputator().validateLogout();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void setWelcomer(User newWelcomer){
		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+newWelcomer.getNick()+SET_ME_AS_WELCOMER_ACTION);
			wr.flush();
			wr.close();
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ans=Mediator.getMed().getDecoder().decodeMessage(reader.readLine());
			reader.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setWelcomer(String nick){
		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+nick+SET_ME_AS_WELCOMER_ACTION);
			wr.flush();
			wr.close();
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ans=Mediator.getMed().getDecoder().decodeMessage(reader.readLine());
			reader.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getWelcome(){
		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			welcomer=Mediator.getMed().getDecoder().decodeUser(reader.readLine());
			reader.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mediator.getMed().getComputator().checkAns(welcomer);
		
	}

	public void getOnlineUsers(){
		
		HashMap<String, User> onlineMap= new HashMap<String, User>();
		
		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+Mediator.getMed().getComputator().getNick()+GET_ONLINE_MAP_ACTION);
			wr.flush();
			wr.close();
			
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String ans=reader.readLine();

			onlineMap.putAll(Mediator.getMed().getDecoder().decodeUsersMap(ans));
			
			Mediator.getCMed().setOnlineMap(onlineMap);
			reader.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, Table> getTablesMap(){
		
		HashMap<Integer, Table> tablesMap=new HashMap<Integer, Table>();

		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+Mediator.getMed().getComputator().getNick()+GET_TABLES_MAP);
			wr.flush();
			wr.close();
			
			BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			tablesMap.putAll(Mediator.getMed().getDecoder().decodeTableMap(reader.readLine()));
//			BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			reader.close();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tablesMap;
	}
	public void startUpdator(){
		updator=new Thread(new Runnable() {
			
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				boolean goOn=true;
				while (goOn) {
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
						goOn=false;
					}
					getOnlineUsers();
				}
				
			}
		});
		updator.setName("updator");
		updator.start();
	}
	public void stopUpdator(){
		try {
			updator.interrupt();
			updator.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setGame(String game){
		try {
			url= new URL("http://"+ip+":8080/ccom/JavaSwitch");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+Mediator.getMed().getComputator().getNick()+"&game="+game);
			wr.flush();
			wr.close();
			BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ans="";
			ans=in.readLine();
			in.close();
//			BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public URLConnection getConnection() {
		return connection;
	}

	
	
}
