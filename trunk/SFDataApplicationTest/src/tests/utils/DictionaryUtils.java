package tests.utils;

import shadow.system.data.SFDictionary;

public class DictionaryUtils extends SFDictionary{

	public DictionaryUtils() {
		super();
		addSFDataset(new DataAsset001());
		addSFDataset(new DataAsset002());
		addSFDataset(new DataAsset003());
	}
	
}
