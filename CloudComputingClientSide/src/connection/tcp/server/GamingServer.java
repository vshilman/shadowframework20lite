package connection.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import mediator.Mediator;

import utils.User;

public class GamingServer implements Runnable{

	private ServerSocket gaming;
	private HashMap<String, User> playersMap= new HashMap<String, User>();
	private HashMap<String, User> visitorsMap= new HashMap<String, User>();
	private BufferedReader reader;
	
	public GamingServer() {
		super();
		playersMap.putAll(Mediator.getCMed().getPlayersMap());
		visitorsMap.putAll(Mediator.getCMed().getVisitorsMap());
		
		
	}
	
	
	@Override
	public void run() {
		try {
			gaming=new ServerSocket(3333);
		
		while (true) {
			if (!playersMap.equals(Mediator.getCMed().getPlayersMap())) {
				playersMap.putAll(Mediator.getCMed().getPlayersMap());
			}
			if (!visitorsMap.equals(Mediator.getCMed().getVisitorsMap())) {
				visitorsMap.putAll(Mediator.getCMed().getVisitorsMap());
			}
			Socket connection=gaming.accept();
			if (connection.isConnected()) {
				
				reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String request="";
				while (reader.ready()) {
					request+=reader.readLine();
				}
				System.out.println(request);
				
				//controlla la richiesta e la inserisce nell'apposita mappa: visitatori o giocatori.
				//Mediator.getMed().checkAns(request);
			}	
			
			//Mediator.getMed().refresh();
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
