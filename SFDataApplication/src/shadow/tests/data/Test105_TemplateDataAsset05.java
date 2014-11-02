package shadow.tests.data;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;
import shadow.system.data.tools.SFDataUtility;
import shadow.tests.data.utils.TestDataAsset3;
import shadow.tests.data.utils.TestDictionary;

public class Test105_TemplateDataAsset05 {

	public static void main(String[] args) {
		
		SFDataCenter.setDictionary(new TestDictionary());

		SFObjectsLibrary library=new SFObjectsLibrary();
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		dataBuilder.loadBuilderData("testsData/data/input/TestInput05.sfb", library);
		
		try {
			
			TestDataAsset3[] dataAssets={ 
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		String root="testsData/data/output";
		String filename="TestInput05.sf";
		
		SFDataUtility.saveDataset(root, filename, library);

		SFObjectsLibrary library2=new SFObjectsLibrary();
		SFDataUtility.loadDataset(root+"/"+filename,library2);

		System.out.println("After Reload");
		
		try {
			
			TestDataAsset3[] dataAssets={ 
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(TestDataAsset3)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
}
