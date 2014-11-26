package dataAssetTests;


import junit.framework.TestCase;

import org.junit.Test;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;
import tests.utils.DataAsset003;
import tests.utils.DictionaryUtils;

public class DataAssetTest002 extends TestCase{

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		SFDataCenter.setDictionary(new DictionaryUtils());
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library = new SFObjectsLibrary();
		
		dataBuilder.loadBuilderData("testsData/DataAsset.sfb", library);
		
		try {
			DataAsset003[] dataAssets={ 
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset05").getResource()),
					(DataAsset003)(SFDataCenter.makeDatasetAvailable("TestDataAsset06").getResource()),
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
				
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
		
	}

}
