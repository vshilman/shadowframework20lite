package connection.tcp.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mediator.Mediator;
import utils.User;

public class ServiceServer implements Runnable {

	private ServerSocket service;
	private HashMap<String, User> serviceMap= new HashMap<String, User>();
	private XMLDecoder decoder;
	private XMLEncoder encoder;
	private List<Object> request;
	
		
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
					
					decoder=new XMLDecoder(connection.getInputStream());
					request=(ArrayList<Object>)decoder.readObject();
					Mediator.getMed().getComputator().checkRequest(request);
				}	
				
			}
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		
	}
	public void sendAnswer(Object answer){
		encoder.writeObject(answer);
		encoder.flush();
	}


}
