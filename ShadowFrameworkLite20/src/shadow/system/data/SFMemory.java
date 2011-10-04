package shadow.system.data;

import java.util.HashMap;


public class SFMemory {

	private static SFMemory memory = new SFMemory();

	private HashMap<String, SFCollection> collections = new HashMap<String, SFCollection>();

	private SFMemory() {

	}

	public static SFMemory getMemory() {
		return memory;
	}

	public void loadCollection(String name, SFCollection collection) {
		collections.put(name, collection);
	}

	public SFDataset retrieveIdentifier(String collection, String identifier) {
		return collections.get(collection).getElement(identifier);
	}

}
