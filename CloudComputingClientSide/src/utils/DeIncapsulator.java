package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.xml.sax.SAXException;

public class DeIncapsulator {

	private HashMap<String, List<String>> mapObtained= new HashMap<String, List<String>>();
	private List<String> messageList= new ArrayList<String>();
	
	
	public HashMap<String, List<String>> decodeUsersMap(InputStream in) throws ParserConfigurationException, SAXException, IOException {
		
		mapObtained.clear();
		
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("user");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String nick=eElement.getElementsByTagName("nick").item(0).getTextContent();
				List<String> attributes= new ArrayList<String>();
				attributes.add(eElement.getElementsByTagName("ip").item(0).getTextContent());
				attributes.add(eElement.getElementsByTagName("platform").item(0).getTextContent());
				attributes.add(eElement.getElementsByTagName("game").item(0).getTextContent());
				
				mapObtained.put(nick, attributes);
			}
		}
		return mapObtained;
	}
	
	public List<String> decodeMessageList(InputStream in)throws ParserConfigurationException, SAXException, IOException{
		messageList.clear();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("messageList");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String message=eElement.getElementsByTagName("message").item(0).getTextContent();
				messageList.add(message);
			}
		}
		return messageList;
	}
	
	public User decodeWelcomer(InputStream in) throws ParserConfigurationException, SAXException, IOException{
		
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("user");
		Node nNode = nList.item(0);
		List<String> welcomer= new ArrayList<String>();

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			welcomer.add(eElement.getElementsByTagName("nick").item(0).getTextContent());
			welcomer.add(eElement.getElementsByTagName("ip").item(0).getTextContent());
			welcomer.add(eElement.getElementsByTagName("platform").item(0).getTextContent());
			welcomer.add(eElement.getElementsByTagName("game").item(0).getTextContent());
			
		}
		
		return Mediator.getMed().getComputator().generateUser(welcomer);
	}
	public String decodeMessage(InputStream in) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);

		doc.getDocumentElement().normalize();

		String messageDecoded=doc.getElementsByTagName("message").item(0).getTextContent();
		
		return messageDecoded;
		
	}
	
}