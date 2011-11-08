package shadow.system.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class SFStreamer {

	private static SFStreamer streamer = new SFStreamer();

	private HashMap<String, SFDataset> map = new HashMap<String, SFDataset>();

	private SFStreamer() {
	}

	public static void loadDataset(List<SFDataset> dataset) {

		for (Iterator<SFDataset> iterator2 = dataset.iterator(); iterator2
				.hasNext();) {
			SFDataset sfDataset = (SFDataset) iterator2.next();
			streamer.map.put(sfDataset.getCode(), sfDataset);
		}
	}

	public SFDataset loadData(SFInputStream stream) {
		String type = stream.readString();
		SFDataset sfd = map.get(type);
		SFDataset tmp = sfd.generateNewDatasetInstance();
		tmp.readFromStream(stream);
		return tmp;
	}

	public static SFStreamer getStreamer() {
		return streamer;
	}

}
