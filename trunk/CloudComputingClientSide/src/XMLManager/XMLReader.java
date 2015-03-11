package XMLManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import utils.Table;

public class XMLReader {
	private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	private InputStream in;
	private XMLEvent event;
	private List<String> nodes;
	private String separatore = "tavolo";

	public XMLReader(InputStream in, String separatore,List<String> nodes) {
		this.in=in;
		this.separatore=separatore;
		this.nodes=nodes;
	}
	
	public List<Table> readConfig(String configFile) {
		List<Table> items = new ArrayList<Table>();
		try {
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			Table node = null;

			while (eventReader.hasNext()) {
				event = eventReader.nextEvent();

				if (event.isStartElement()) {
					
					StartElement startElement = event.asStartElement();
					
					if (startElement.getName().getLocalPart() == (separatore)) {
						node = new Table();
					}

						
						for (int i = 0; i < nodes.size(); i++) {
							if (event.isStartElement()) {						
								if (event.asStartElement().getName().getLocalPart()
									.equals(nodes.get(i))) {
								event = eventReader.nextEvent();
								node.setAttribute(nodes.get(i), event.asCharacters().getData());
								continue;
							}
							
						}
						
					}
				}
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (separatore)) {
						items.add(node);
					}
				}

			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return items;
	}

}