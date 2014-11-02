package main;

import android.os.Environment;
import android.util.Log;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;

public class SFBOrderReader {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	public SFDataAssetBuilder[] createSFBOrder(String fileName){
		
		DictionaryBuilder dictionary = new DictionaryBuilder();
		
		SFDataCenter.setDictionary(dictionary);	
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library=new SFObjectsLibrary();
		
		Log.d("PROVA", "TUTTO OK FIN QUA");
		
		dataBuilder.loadBuilderData(DIRECTORY+"/"+fileName+".sfb", library);
		Log.d("PROVA", "CIAO");
		
			SFDataAssetBuilder[] dataAssets={ 
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(SFDataAssetBuilder)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
			};
			
			
			return dataAssets;
	
	}
	
	
	
}

	
	
	

