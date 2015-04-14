package connection.tcp.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import mediator.Mediator;
import utils.User;

public class GamingServer implements Runnable{

	private ServerSocket gaming;
	private HashMap<String, User> playersMap= new HashMap<String, User>();
	private BufferedReader reader;
	private DataOutputStream writer;

	public GamingServer() {
		super();
		List<String> players=Mediator.getPMed().getOrderedPlayers();
		for (int i = 0; i < players.size(); i++) {
			playersMap.put(players.get(i), Mediator.getCMed().getOnlinePlayers().get(players.get(i)));
		}
		
		
	}
	
	
	@Override
	public void run() {
		try {
			gaming=new ServerSocket(3333);
			gaming.setSoTimeout(2000);
		while (!Thread.currentThread().isInterrupted()) {
			Socket connection=gaming.accept();
			if (connection.isConnected()) {
				
				reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String request=reader.readLine();
				Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request));
				System.out.println("GAMING SERVER HAS RECEIVED: "+request);
				
			}	
			
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void sendAnswer(String answer){
		try {
			writer.writeBytes(answer+'\n');
			writer.writeBytes(""+'\n');
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
