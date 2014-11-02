package dataAssetTests;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;
import shadow.system.data.tools.SFDataUtility;
import tests.utils.DataAsset001;
import tests.utils.DictionaryUtils;

public class DataAssetTest003 extends TestCase{

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	
	@Test
	public void testA() {
	
		SFDataCenter.setDictionary(new DictionaryUtils());
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library=new SFObjectsLibrary();
		
		dataBuilder.loadBuilderData(DIRECTORY+"/"+"Ordine.sfb", library);
		
		try {
			DataAsset001[] dataAssets={ 
				(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),	
			
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
			}
						
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		String root=DIRECTORY;
		String filename="Salvato.sfb";
		
		SFDataUtility.saveDataset(root, filename, library);

		SFObjectsLibrary library2=new SFObjectsLibrary();
		SFDataUtility.loadDataset(root+"/"+filename,library2);

		
		try {
			
			DataAsset001[] dataAssets={ 
						(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
						(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
						(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
						(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),	
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

}
