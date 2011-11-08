package shadow.system.data;

import java.util.HashMap;


public class SFMemory {

	private static SFMemory memory = new SFMemory();

	private HashMap<String, SFList> lists = new HashMap<String, SFList>();

	private SFMemory() {

	}

	public static SFMemory getMemory() {
		return memory;
	}

	public void loadList(String name, SFList list) {
		lists.put(name, list);
	}

	public SFDataset retrieveIdentifier(String list, String identifier) {
		return lists.get(list).getElement(identifier);
	}

}
