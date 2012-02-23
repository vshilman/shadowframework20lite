package shadow.renderer.tests.utils;

import shadow.renderer.SFAsset;
import shadow.system.SFInitiator;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFIDataCenter;
import shadow.system.data.SFObjectsLibrary;

public class SFViewerObjectsLibrary implements SFIDataCenter{

	private static final String root="src/libraries";
	
	private SFObjectsLibrary library;
	
	public SFViewerObjectsLibrary(){
		
		SFDataset dataset=SFDataUtility.loadDataset(root, "library");
		this.library=(SFObjectsLibrary)(dataset);
		
		for (SFObjectsLibrary.RecordData record : library) {
			
			SFAsset geometry=((SFAsset)(record.getDataset()));
	
			SFInitiator.addInitiable(geometry);
		}
	}
	
	@Override
	public void makeDatasetAvailable(String name, SFDataCenterListener listener) {
		listener.onDatasetAvailable(name, library.retrieveDataset(name));
	}
	
	@Override
	public void releaseDataset(String name, SFDataCenterListener listener) {
		// TODO Auto-generated method stub
	}
}
