package connection.tcp.server;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	private static final String ENTER_TABLE = "enter_table";
	private static final String UNKNOWN= "unknown";
	private ServerSocket service;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String request;
	private List<String> request2;
	private String method;
		
	public ServiceServer() {
		super();
	}
	
	@Override
	public void run(){
			try {
				service=new ServerSocket(3000);
				
//			while (true) {
				System.out.println("while true");
				Socket connection=service.accept();
				System.out.println("accettata");
				if (connection.isConnected()) {
					reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
					writer=new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
					request=reader.readLine();
					method=Mediator.getMed().getDecoder().whichMethodUse(request);
					if (method.equals(MESSAGELIST)) {
						request2=Mediator.getMed().getDecoder().decodeMessageList(request);
						String whichIs=Mediator.getMed().getDecoder().whichMethodUse(request2.get(1));
						if (whichIs.equals(USER)) {
							Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request2.get(0)),Mediator.getMed().getDecoder().decodeUser(request2.get(1)));
						}else if (whichIs.equals(TABLEMAP)) {
							Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request2.get(0)),Mediator.getMed().getDecoder().decodeTableMap(request2.get(1)));
						}else if (whichIs.equals(ONLINELIST)){
							Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request2.get(0)),Mediator.getMed().getDecoder().decodeUsersMap(request2.get(1)));
						}else if (method.equals("id")) {
							Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request2.get(0)),Mediator.getMed().getDecoder().decodeId(request2.get(1)), connection.getInetAddress().getHostAddress());
						}
						
					}else if (method.equals(MESSAGE)){
						Mediator.getMed().getComputator().checkRequest(Mediator.getMed().getDecoder().decodeMessage(request));
					}else if (method.equals(ONLINELIST)) {
						Mediator.getMed().getComputator().checkRequest("online",Mediator.getMed().getDecoder().decodeUsersMap(request));
					}else if (method.equals(TABLEMAP)) {
						Mediator.getMed().getComputator().checkRequest("table",Mediator.getMed().getDecoder().decodeTableMap(request));
					}else if (method.equals(UNKNOWN)) {
						Mediator.getMed().getComputator().checkRequest(UNKNOWN);
					}
				}	
				
//			}
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		
	}
	public void sendAnswer(String answer){
		try {
			writer.write(answer);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
