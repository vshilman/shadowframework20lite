package connection.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import mediator.Mediator;
import utils.User;

public class ServiceServer implements Runnable {

	private ServerSocket service;
	private HashMap<String, User> serviceMap= new HashMap<String, User>();
	private BufferedReader reader;
	
		
	public ServiceServer() {
		super();
		serviceMap.putAll(Mediator.getMed().getComputator().getServiceMap());
		

	}
	
	@Override
	public void run(){
			try {
				service=new ServerSocket(3000);
			
			while (true) {
				if (!serviceMap.equals(Mediator.getCMed().getServiceMap())) {
					serviceMap.putAll(Mediator.getCMed().getServiceMap());
				}
				Socket connection=service.accept();
				if (connection.isConnected()) {
					
					reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String request="";
					while (reader.ready()) {
						request+=reader.readLine();
					}
					
					System.out.println(request);
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
