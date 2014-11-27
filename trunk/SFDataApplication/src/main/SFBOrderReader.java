package main;

import android.os.Environment;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;

public class SFBOrderReader {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFOrders";
	
	public SFDataAssetBuilder[] createSFBOrder(String fileName){
		
		DictionaryBuilder dictionary = new DictionaryBuilder();
		
		SFDataCenter.setDictionary(dictionary);	
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library=new SFObjectsLibrary();	
		
		dataBuilder.loadBuilderData(DIRECTORY+"/"+fileName, library);
		
			SFDataAssetBuilder[] dataAssets={ 
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
			};
			
			
			return dataAssets;
	
	}
	
	
	
}

	
	
	

