package client;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFIDataCenter;
import shadow.system.data.SFObjectsLibrary;

public class SFViewerObjectsLibrary implements SFIDataCenter{

	private SFObjectsLibrary library;
	
	public SFViewerObjectsLibrary(String root,String library){
		SFDataset dataset=SFDataUtils.loadDataset(root, library);
		this.library=(SFObjectsLibrary)(dataset);
	}
	
	public SFViewerObjectsLibrary(){
		this.library=new SFObjectsLibrary();
	}
	
	@Override
	public void makeDatasetAvailable(String name, SFDataCenterListener listener) {
		SFDataset dataset=library.retrieveDataset(name);
		if(dataset==null){
			throw new NullPointerException("SFViewerObjectsLibrary: Cannot Find A Dataset named "+name);
		}
		listener.onDatasetAvailable(name, dataset);
	}
	
	public SFObjectsLibrary getLibrary() {
		return library;
	}
	
	public void addLibrary(SFViewerObjectsLibrary library) {
		this.library.addLibrary(library.getLibrary());
	}
	
}
