package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Incapsulator{

	private static String HEADERLIST="<listaOnline>";
	private static String HEADERUSER="<user>";
	private static String FOOTERLIST="</listaOnline>";
	private static String FOOTERUSER="</user>";
	private static String HEADERNICK="<nick>";
	private static String HEADERPLATFORM="<platform>";
	private static String HEADERIP="<ip>";
	private static String HEADERGAME="<game>";
	private static String FOOTERNICK="</nick>";
	private static String FOOTERPLATFORM="</platform>";
	private static String FOOTERIP="</ip>";
	private static String FOOTERGAME="</game>";
	private static String FILEHEADER="<?xml version=\"1.0\"?>";
	private static String FILEMESSAGEHEADER="<messageList>";
	private static String MESSAGEHEADER="<message>";
	private static String FILEMESSAGEFOOTER="</messageList>";
	private static String MESSAGEFOOTER="</message>";
	private Set<String> mainTag;
	private List<String> message= new ArrayList<String>();
	private String messageConverted;
	
	
	
	
	
	public String convertUserMap(HashMap<String, List<String>> mapToConvert) {
		
		mainTag=mapToConvert.keySet();
		messageConverted=FILEHEADER+HEADERLIST;
		for (Iterator<String> iterator = mainTag.iterator(); iterator.hasNext();) {
			String nick = (String) iterator.next();
			messageConverted=messageConverted+HEADERUSER+HEADERNICK+nick+FOOTERNICK+HEADERIP+mapToConvert.get(nick).get(0)+FOOTERIP+HEADERPLATFORM+mapToConvert.get(nick).get(1)+FOOTERPLATFORM+HEADERGAME+mapToConvert.get(nick).get(2)+FOOTERGAME+FOOTERUSER;
		}
		messageConverted=messageConverted+FOOTERLIST;
		
		return messageConverted;
	}
	
	
	public String convertMessageList(List<String> messages){
		messageConverted=FILEHEADER+FILEMESSAGEHEADER;
		for (int i = 0; i < messages.size(); i++) {
			String actualMessage = messages.get(i);
			messageConverted=messageConverted+MESSAGEHEADER+actualMessage+MESSAGEFOOTER;
		}
		messageConverted=messageConverted+FILEMESSAGEFOOTER;
		
		return messageConverted;
	}
	
	public String convertWelcomer(List<String> welcomeUser){
		messageConverted=FILEHEADER+HEADERLIST;
		messageConverted=messageConverted+HEADERUSER+HEADERNICK+welcomeUser.get(0)+FOOTERNICK+HEADERIP+welcomeUser.get(1)+FOOTERIP+HEADERPLATFORM+welcomeUser.get(2)+FOOTERPLATFORM+HEADERGAME+welcomeUser.get(3)+FOOTERGAME+FOOTERUSER;
		messageConverted=messageConverted+FOOTERLIST;
		
		return messageConverted;
	}
	
	public String convertMessage(String message){
		messageConverted=FILEHEADER+MESSAGEHEADER+message+MESSAGEFOOTER;
		return messageConverted;
	}
	
	public String convert(List<String> listToConvert, String headerList, String elementsHeader, List<String> headers ){
		return null;
	}
}
