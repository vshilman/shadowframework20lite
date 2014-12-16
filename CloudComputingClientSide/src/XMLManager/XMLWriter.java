package XMLManager;

import java.io.OutputStream;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLWriter {

	private XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	private XMLEvent end = eventFactory.createDTD("\n");
	private XMLEvent tab = eventFactory.createDTD("\t");
	private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	private OutputStream out;

	public XMLWriter(OutputStream out) {
		this.out = out;
	}

	public void writeXML(String startElement, String separatore,
			List<String> nodes, List<String> attributes) throws Exception {

		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(out);

		StartDocument startDocument = eventFactory.createStartDocument();

		eventWriter.add(startDocument);

		StartElement configStartElement = eventFactory.createStartElement("","", startElement);
		eventWriter.add(configStartElement);
		eventWriter.add(end);

		int i = 0;
		while (i != attributes.size()) {
			eventWriter.add(tab);
			StartElement separaElementi = eventFactory.createStartElement("",
					"", separatore);
			eventWriter.add(separaElementi);
			eventWriter.add(end);
			for (int j = 0; j < nodes.size(); j++, i++) {
				eventWriter.add(tab);
				createNode(eventWriter, nodes.get(j), attributes.get(i));
			}
			eventWriter.add(tab);
			eventWriter.add(eventFactory.createEndElement("", "",
					separatore));
			eventWriter.add(end);
		}

		eventWriter.add(eventFactory.createEndElement("", "", startElement));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name, String value)
			throws XMLStreamException {

		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);

		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);

		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}

}
