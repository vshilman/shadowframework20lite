package connection.tcp.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.Media;

import mediator.Mediator;
import utils.User;

public class ServiceServer implements Runnable {

	
	private static final String MESSAGELIST= "messageList";
	private static final String ONLINELIST= "listaOnline";
	private static final String TABLEMAP= "tableMap";
	private static final String MESSAGE= "message";
	private static final String USER="user";
	private static final String UNKNOWN= "unknown";
	private ServerSocket service;
	private HashMap<String, User> serviceMap= new HashMap<String, User>();
	private BufferedReader reader;
	private String request;
	private String request2;
	private String method;
		
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
					request=reader.readLine();
					method=Mediator.getMed().getDecoder().whichMethodUse(request);
					if (method.equals(MESSAGE)) {
						request2=reader.readLine();
						String whichIs=Mediator.getMed().getDecoder().whichMethodUse(request2);
						if (request2!=null) {
							if (whichIs.equals(USER)) {
								Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request),Mediator.getMed().getDecoder().decodeUser(request2));
							}else if (whichIs.equals(TABLEMAP)) {
								Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request),Mediator.getMed().getDecoder().decodeTableMap(request2));
							}else if (whichIs.equals(ONLINELIST)){
								Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request),Mediator.getMed().getDecoder().decodeUsersMap(request2));
							}
							
						}else {
							Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request));
						}
					}else if (method.equals(MESSAGELIST)) {
						Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessageList(request));
					}else if (method.equals(ONLINELIST)) {
						Mediator.getMed().getComputator().checkRequest("online",Mediator.getMed().getDecoder().decodeUsersMap(request));
					}else if (method.equals(TABLEMAP)) {
						Mediator.getMed().getComputator().checkRequest("table",Mediator.getMed().getDecoder().decodeTableMap(request));
					}else if (method.equals(UNKNOWN)) {
						Mediator.getMed().getComputator().checkRequest(UNKNOWN);
					}
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
