package shadow.tests.data;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;
import shadow.tests.data.utils.TestDataAsset2;
import shadow.tests.data.utils.TestDictionary;

public class Test101_TemplateDataAsset02 {

	public static void main(String[] args) {
		
		SFDataCenter.setDictionary(new TestDictionary());

		SFObjectsLibrary library=new SFObjectsLibrary();
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		dataBuilder.loadBuilderData("testsData/data/input/TestInput02.sfb", library);
		
		try {
			TestDataAsset2[] dataAssets={ 
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset05").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset06").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset07").getResource()),
					(TestDataAsset2)(SFDataCenter.makeDatasetAvailable("TestDataAsset08").getResource()),
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
			}

//			TestDataAsset asset5 = (TestDataAsset)(SFDataCenter.makeDatasetAvailable("TestDataAsset05").getResource());
//
//			System.err.println("dataAsset"+asset5);
			//What is the problem with that data Asset?
			
			//Object object2=asset3.getDataAsset().getResource();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
}
