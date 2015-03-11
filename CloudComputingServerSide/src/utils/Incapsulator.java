package utils;

import generic.GamingUser;

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
	private String messageConverted;
	
	
	
	
	
	public String convert(HashMap<String, GamingUser> onlineUsersMap) {
		
		mainTag=onlineUsersMap.keySet();
		messageConverted=FILEHEADER+HEADERLIST;
		for (Iterator<String> iterator = mainTag.iterator(); iterator.hasNext();) {
			String nick = (String) iterator.next();
			messageConverted=messageConverted+HEADERUSER+HEADERNICK+nick+FOOTERNICK+HEADERIP+onlineUsersMap.get(nick).getIp()+FOOTERIP+HEADERPLATFORM+onlineUsersMap.get(nick).getPlatform()+FOOTERPLATFORM+HEADERGAME+onlineUsersMap.get(nick).getGame()+FOOTERGAME+FOOTERUSER;
		}
		messageConverted=messageConverted+FOOTERLIST;
		
		return messageConverted;
	}
	
	
	public String convert(List<String> messages){
		messageConverted=FILEHEADER+FILEMESSAGEHEADER;
		for (int i = 0; i < messages.size(); i++) {
			String actualMessage = messages.get(i);
			messageConverted=messageConverted+MESSAGEHEADER+actualMessage+MESSAGEFOOTER;
		}
		messageConverted=messageConverted+FILEMESSAGEFOOTER;
		
		return messageConverted;
	}
	
	public String convert(GamingUser gamingUser){
		messageConverted=FILEHEADER+HEADERLIST;
		messageConverted=messageConverted+HEADERUSER+HEADERNICK+gamingUser.getNick()+FOOTERNICK+HEADERIP+gamingUser.getIp()+FOOTERIP+HEADERPLATFORM+gamingUser.getPlatform()+FOOTERPLATFORM+HEADERGAME+gamingUser.getGame()+FOOTERGAME+FOOTERUSER;
		messageConverted=messageConverted+FOOTERLIST;
		
		return messageConverted;
	}
	
	public String convert(String message){
		messageConverted=FILEHEADER+MESSAGEHEADER+message+MESSAGEFOOTER;
		return messageConverted;
	}
	
	public String convert(List<String> listToConvert, String headerList, String elementsHeader, List<String> headers ){
		return null;
	}
}
