package utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mediator.Mediator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DeIncapsulator {

	private HashMap<String, User> usersMapObtained= new HashMap<String, User>();
	private HashMap<Integer, Table> tableMapObtained= new HashMap<Integer, Table>();
	private List<String> messageList= new ArrayList<String>();
	
	
	public HashMap<String, User> decodeUsersMap(String usersMapEncoded) {
		
		usersMapObtained.clear();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			
			doc = dBuilder.parse(new InputSource(new StringReader(usersMapEncoded)));
		
	
			doc.getDocumentElement().normalize();
	
			NodeList nList = doc.getElementsByTagName("user");
			for (int temp = 0; temp < nList.getLength(); temp++) {
	
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String nick=eElement.getElementsByTagName("nick").item(0).getTextContent();
					List<String> attributes= new ArrayList<String>();
					attributes.add(nick);
					attributes.add(eElement.getElementsByTagName("ip").item(0).getTextContent());
					attributes.add(eElement.getElementsByTagName("platform").item(0).getTextContent());
					attributes.add(eElement.getElementsByTagName("game").item(0).getTextContent());
					User user= Mediator.getMed().getComputator().generateUser(attributes);
					usersMapObtained.put(nick, user);
				}
			}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersMapObtained;
	}
	
	public List<String> decodeMessageList(String messageListEncoded){
		messageList.clear();
		try{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(messageListEncoded)));

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("messageList");
		NodeList childs=nList.item(0).getChildNodes();

		Node nNode = childs.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			String nodeName=eElement.getFirstChild().getNodeName();
			if (nodeName.equals("message")) {
				messageList.add(Mediator.getMed().getCoder().convert(eElement.getElementsByTagName("message").item(0).getTextContent()));
			}else if (nodeName.equals("user")) {
				String nick=eElement.getElementsByTagName("nick").item(0).getTextContent();
				String ip=eElement.getElementsByTagName("ip").item(0).getTextContent();
				String platform=eElement.getElementsByTagName("platform").item(0).getTextContent();
				String game=eElement.getElementsByTagName("game").item(0).getTextContent();
				messageList.add(Mediator.getMed().getCoder().convert(new User(nick, ip, platform, game)));
			}else if (nodeName.equals("id")) {
				messageList.add(Mediator.getMed().getCoder().convert(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())));
			}
		}
		nNode = childs.item(1);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			String nodeName=eElement.getLastChild().getNodeName();
			if (nodeName.equals("message")) {
				messageList.add(Mediator.getMed().getCoder().convert(eElement.getElementsByTagName("message").item(0).getTextContent()));
			}else if (nodeName.equals("user")) {
				String nick=eElement.getElementsByTagName("nick").item(0).getTextContent();
				String ip=eElement.getElementsByTagName("ip").item(0).getTextContent();
				String platform=eElement.getElementsByTagName("platform").item(0).getTextContent();
				String game=eElement.getElementsByTagName("game").item(0).getTextContent();
				messageList.add(Mediator.getMed().getCoder().convert(new User(nick, ip, platform, game)));
			}else if (nodeName.equals("id")) {
				messageList.add(Mediator.getMed().getCoder().convert(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent())));
			}
				
//				String message=eElement.getElementsByTagName("arrayElement").item(0).getTextContent();
//				System.out.println(message);
//				messageList.add(message);
		}
	} catch (SAXException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(messageList.get(0));
		System.out.println(messageList.get(1));
		return messageList;
	}
	
	public HashMap<Integer, Table> decodeTableMap(String tablesMapEncoded){
		
		tableMapObtained.clear();
		try{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(tablesMapEncoded)));

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("table");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String name=eElement.getElementsByTagName("name").item(0).getTextContent();
				int id=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
				NodeList nList2=eElement.getElementsByTagName("nPlayers");
				String game=eElement.getElementsByTagName("game").item(0).getTextContent();
				int nPlayers=Integer.parseInt(nList2.item(0).getTextContent());
				List<String> players= new ArrayList<String>();
				for (int i = 0; i < nPlayers; i++) {
					if (eElement.getElementsByTagName("p"+(i+1)).getLength()!=0) {
						players.add(eElement.getElementsByTagName("p"+(i+1)).item(0).getTextContent());
					}
					
				}
				boolean spectation=Boolean.parseBoolean(eElement.getElementsByTagName("spectable").item(0).getTextContent());
				String manager=eElement.getElementsByTagName("manager").item(0).getTextContent();
				Table table= Mediator.getMed().getComputator().generateTable(name, id, game, nPlayers, players, spectation, manager);
				
				tableMapObtained.put(id, table);
			}
		}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tableMapObtained;
	}
	
	public User decodeUser(String userEncoded){
				List<String> userAttributes= new ArrayList<String>();

		try{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(userEncoded)));

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("user");
		Node nNode = nList.item(0);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			userAttributes.add(eElement.getElementsByTagName("nick").item(0).getTextContent());
			userAttributes.add(eElement.getElementsByTagName("ip").item(0).getTextContent());
			userAttributes.add(eElement.getElementsByTagName("platform").item(0).getTextContent());
			userAttributes.add(eElement.getElementsByTagName("game").item(0).getTextContent());
			
		}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Mediator.getMed().getComputator().generateUser(userAttributes);
	}
	public String decodeMessage(String messageEncoded){
		
		String messageDecoded= new String();
		try{
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(messageEncoded)));

		doc.getDocumentElement().normalize();

		messageDecoded=doc.getElementsByTagName("message").item(0).getTextContent();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageDecoded;
		
	}
	public Integer decodeId(String idEncoded) {
		int messageDecoded=-1;
		try{
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(idEncoded)));

			doc.getDocumentElement().normalize();

			messageDecoded=Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return messageDecoded;
	}
	
	public Table decodeTable(String tableEncoded){
		String name="";
		int id=0;
		String game="";
		int nPlayers=0;
		List<String> players= new ArrayList<String>();
		boolean spectation=false;
		String manager="";
		
		try{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(tableEncoded)));

		doc.getDocumentElement().normalize();
		
		
		NodeList nList = doc.getElementsByTagName("table");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			name=eElement.getElementsByTagName("name").item(0).getTextContent();
			id=Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent());
			NodeList nList2=eElement.getElementsByTagName("nPlayers");
			game=eElement.getElementsByTagName("game").item(0).getTextContent();
			nPlayers=Integer.parseInt(nList2.item(0).getTextContent());
			players= new ArrayList<String>();
			for (int i = 0; i < nPlayers; i++) {
				if (eElement.getElementsByTagName("p"+(i+1)).getLength()!=0) {
					players.add(eElement.getElementsByTagName("p"+(i+1)).item(0).getTextContent());
				}
				
			}
			spectation=Boolean.parseBoolean(eElement.getElementsByTagName("spectable").item(0).getTextContent());
			manager=eElement.getElementsByTagName("manager").item(0).getTextContent();

		}

		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mediator.getMed().getComputator().generateTable(name, id, game, nPlayers, players, spectation, manager);

	}
	public String whichMethodUse(String xml){
		try{
			System.out.println("------------------------->"+xml);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			
			doc.getDocumentElement().normalize();
			if (doc.getElementsByTagName("messageList").getLength()!=0) {
				return "messageList";
			}else if (doc.getElementsByTagName("listaOnline").getLength()!=0) {
				return "listaOnline";
			}else if (doc.getElementsByTagName("tableMap").getLength()!=0) {
				return "tableMap";
			}else if (doc.getElementsByTagName("message").getLength()!=0) {
				return "message";
			}else if (doc.getElementsByTagName("id").getLength()!=0) {
				return "id";
			}else if (doc.getElementsByTagName("user").getLength()!=0) {
				return "user";
			}else if (doc.getElementsByTagName("table").getLength()!=0) {
				return "table";
			}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "unknown";
	}
	
}