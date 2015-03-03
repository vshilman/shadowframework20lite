package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Incapsulator {

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
	private Set<String> nicks;
	private List<String> message= new ArrayList<String>();
	private String message1;
	
	public String convert(HashMap<String, List<String>> mapToConvert) {
		
		nicks=mapToConvert.keySet();
		message.add(FILEHEADER);
		message.add(HEADERLIST);
		message1=FILEHEADER+HEADERLIST;
		for (Iterator<String> iterator = nicks.iterator(); iterator.hasNext();) {
			String nick = (String) iterator.next();
			message.add(HEADERUSER);
			message.add(HEADERNICK+nick+FOOTERNICK);
			message.add(HEADERIP+mapToConvert.get(nick).get(0)+FOOTERIP);
			message.add(HEADERPLATFORM+mapToConvert.get(nick).get(1)+FOOTERPLATFORM);
			message.add(HEADERGAME+mapToConvert.get(nick).get(2)+FOOTERGAME);
			message.add(FOOTERUSER);
			message1=message1+HEADERUSER+HEADERNICK+nick+FOOTERNICK+HEADERIP+mapToConvert.get(nick).get(0)+FOOTERIP+HEADERPLATFORM+mapToConvert.get(nick).get(1)+FOOTERPLATFORM+HEADERGAME+mapToConvert.get(nick).get(2)+FOOTERGAME+FOOTERUSER;
		}
		message1=message1+FOOTERLIST;
		message.add(FOOTERLIST);
		
		return message1;
	}
	
}
