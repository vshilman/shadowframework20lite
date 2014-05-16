package connection;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mediator.Mediator;


public class Connector{

	
	private String NICKHEAD= "nickname=";
	private String PASSHEAD= "&password=";
	private String PLATFORM= "&platform=java";
	
	private HttpURLConnection connection;
	private URL url;
	private String ans= new String();
	private List<String> answer=new ArrayList<String>();
	private String ip;
	private HashMap<String, List<String>>map= new HashMap<String, List<String>>();

	public Connector(String ip) {
		this.ip=ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public void login(String user, String pass) {
			
			try {
				url= new URL("http://"+ip+":8080/ccom/Login");
				connection=(HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("User-Agent", "Java");
				connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(NICKHEAD+user+PASSHEAD+pass+PLATFORM);
				wr.flush();
				wr.close();
	//			System.out.println(NICKHEAD+user+PASSHEAD+pass+PLATFORM);
				BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
				ans="";
				while (in.ready()) {
					ans+=in.readLine();
				}
				in.close();
				
				Mediator.getMed().validateLogin(ans, user, pass);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
	}
	
	public void setMyselfAsWelcomer(){
		try {
			url= new URL("http://"+ip+":8080/ccom/HomeJava");
			connection=(HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", "Java");
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(NICKHEAD+Mediator.getMed().getNick());
			wr.flush();
			wr.close();
			BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ans="";
			while (in.ready()) {
				ans+=in.readLine();
			}
		
			in.close();
			
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
			XMLDecoder decoder= new XMLDecoder(connection.getInputStream());
			answer=(ArrayList<String>)decoder.readObject();
			for (int i = 0; i < answer.size(); i++) {
				System.out.println(answer.get(i));
			}
			Mediator.getMed().checkAns(answer);
			decoder.close();
			
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
