package shadow.tests.data.utils;

import shadow.system.data.SFDictionary;

public class TestDictionary extends SFDictionary{

	public TestDictionary() {
		super();
		addSFDataset(new TestDataAsset());
		addSFDataset(new TestDataAsset2());
		addSFDataset(new TestDataAsset3());
	}
}
