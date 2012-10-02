package shadow.renderer.data.java;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SFXMLDataInterpreter  extends DefaultHandler{
	
	private SFDataInterpreter sfDataInterpreter;
	
	private LinkedList<String> plannedElement=new LinkedList<String>();
	private LinkedList<String> plannedElementName=new LinkedList<String>();
	private boolean doStartElement=true;
	private String content=null;
	
	public SFXMLDataInterpreter(SFDataInterpreter sfDataInterpreter) {
		super();
		this.sfDataInterpreter=sfDataInterpreter;
	}

	public void generateInterpretation(String filename){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(filename, this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		startElement(qName, attributes);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		endElement(qName);

	}

	public void characters(char ch[], int start, int length) throws SAXException {
		elementContent(new String(ch, start, length));
	}

	public void startElement(String elementName,Attributes attributes){
		//System.out.println("Starting element "+elementName);
		if(doStartElement){
			if(plannedElement.size()>0){
				sfDataInterpreter.pushElement(plannedElement.getLast(), plannedElementName.getLast());
			}
		}
		plannedElement.add(elementName);
		plannedElementName.add(attributes.getValue(0));
		doStartElement=true;
	}
	
	public void endElement(String elementName){
		//System.out.println("Ending element "+elementName+" "+doStartElement);
		if(doStartElement){
			if(content!=null){
				sfDataInterpreter.insertElement(elementName, content);
				content=null;
			}
			plannedElement.removeLast();
			plannedElementName.removeLast();
		}else{
			sfDataInterpreter.popElement(elementName);
		}
		doStartElement=false;
		//Se arriva una endElement, ferma startElement
	}
	
	public void elementContent(String content){
		if(content.trim().length()>0)
			this.content=content;
	}
}
