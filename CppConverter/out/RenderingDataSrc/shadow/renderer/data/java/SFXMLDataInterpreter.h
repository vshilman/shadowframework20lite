#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

#include "java/io/IOException.h"
#include "java/util/LinkedList.h"

#include "javax/xml/parsers.ParserConfigurationException.h"
#include "javax/xml/parsers.SAXParser.h"
#include "javax/xml/parsers.SAXParserFactory.h"

#include "org/xml/sax.Attributes.h"
#include "org/xml/sax.SAXException.h"
#include "org/xml/sax.helpers.DefaultHandler.h"

class SFXMLDataInterpreter  extends DefaultHandler{

	SFDataInterpreter sfDataInterpreter;

	LinkedList<String> plannedElement=new LinkedList<String>();
	LinkedList<String> plannedElementName=new LinkedList<String>();
	booldoStartElement=true;
	String content=null;

	SFXMLDataInterpreter(SFDataInterpreter sfDataInterpreter) {
		super();
		this->sfDataInterpreter=sfDataInterpreter;
	}

	void generateInterpretation(String filename){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(filename, this);
		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}
	}

	void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		startElement(qName, attributes);
	}

	void endElement(String uri, String localName, String qName)
			throws SAXException {
		endElement(qName);

	}

	void characters(char ch[], int start, int length) throws SAXException {
		elementContent(new String(ch, start, length));
	}

	void startElement(String elementName,Attributes attributes){
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

	void endElement(String elementName){
		//System.out.println("Ending element "+elementName+" "+doStartElement);
		if(doStartElement){
			if(content!=null){
				sfDataInterpreter.insertElement(elementName, content);
				content=null;
			}
			plannedElement.removeLast();
			plannedElementName.removeLast();
		}
			sfDataInterpreter.popElement(elementName);
		}
		doStartElement=false;
		//Se arriva una endElement, ferma startElement
	}

	void elementContent(String content){
		if(content.trim().length()>0)
			this->content=content;
	}
}
;
}
#endif
