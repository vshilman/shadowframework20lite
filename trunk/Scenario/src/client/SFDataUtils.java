package client;

import shadow.renderer.data.SFDataAsset;
import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;



public class SFDataUtils {

	
	
	public static <T extends SFInitiable> T getResource(String assetName) throws SFException{
		T t;
		try {
			/*the warning on-this line is suppressed; IF there is any casting-check problem, it will be 
			 * caught as an SF exception and rightly messaged to this method caller*/
			t=(T)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(assetName)).getResource();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("The asset " + assetName + " is not an 'already available' asset");
		}
		return t;
	}
	

	public static SFDataset loadDataset(String root,String name){
		
		//read it
		
			SFInputStream inputStream= new SFInputStreamJava(SFDataUtils.class.getResourceAsStream(root+name),new DefaultExceptionKeeper());
			SFDataset dataset=SFDataCenter.getDataCenter().readDataset(inputStream);
			
			return dataset;
	
		
		
		
	}
	


}
