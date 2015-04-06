package utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Incapsulator{

	private static String HEADERTABLELIST="<tableMap>";
	private static String FOOTERTABLELIST="</tableMap>";
	private static String HEADERTABLE="<table>";
	private static String FOOTERTABLE="</table>";
	private static String HEADERNAME="<name>";
	private static String FOOTERNAME="</name>";
	private static String HEADERID="<ID>";
	private static String FOOTERID="</ID>";
	private static String HEADERNPLAYERS="<nPlayers>";
	private static String FOOTERNPLAYERS="</nPlayers>";
	private static String HEADERSPECTABLE="<spectable>";
	private static String FOOTERSPECTABLE="</spectable>";
	private static String HEADERMANAGER="<manager>";
	private static String FOOTERMANAGER="</manager>";

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
	
	
	
	
	
	public String convertTableMap(HashMap<Integer, Table> tableMap) {
		Set<Integer> tableID=tableMap.keySet();
		messageConverted=FILEHEADER+HEADERTABLELIST;
		for (Iterator<Integer> iterator = tableID.iterator(); iterator.hasNext();) {
			Integer id = (Integer) iterator.next();
			messageConverted=messageConverted+HEADERTABLE+HEADERNAME+tableMap.get(id).getName()+FOOTERNAME+HEADERID+id+FOOTERID+HEADERNPLAYERS+tableMap.get(id).getPlayersSupported()+FOOTERNPLAYERS;
			for (int i = 0; i < tableMap.get(id).getPlayersSupported(); i++) {
				messageConverted=messageConverted+"<p"+(i+1)+">"+tableMap.get(id).getPlayersList().get(i)+"</p"+(i+1)+">";
			}
			messageConverted=messageConverted+HEADERSPECTABLE+tableMap.get(id).isSpectable()+FOOTERSPECTABLE;
			messageConverted=messageConverted+HEADERMANAGER+tableMap.get(id).getManager()+FOOTERMANAGER+FOOTERTABLE;
		}
		messageConverted=messageConverted+FOOTERTABLELIST;
		
		return messageConverted;
	}
	
	public String convert(HashMap<String, User> onlineUsersMap) {
		
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
	
	public String convert(User user){
		messageConverted=FILEHEADER;
		messageConverted=messageConverted+HEADERUSER+HEADERNICK+user.getNick()+FOOTERNICK+HEADERIP+user.getIp()+FOOTERIP+HEADERPLATFORM+user.getPlatform()+FOOTERPLATFORM+HEADERGAME+user.getGame()+FOOTERGAME+FOOTERUSER;
		
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
