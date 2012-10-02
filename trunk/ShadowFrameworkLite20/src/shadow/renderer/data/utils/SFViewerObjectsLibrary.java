package shadow.renderer.data.utils;

import java.io.InputStream;

import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFIDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;

public class SFViewerObjectsLibrary implements SFIDataCenter{

	private SFObjectsLibrary library;
	
	public SFViewerObjectsLibrary(String root,String library){
		SFDataset dataset=SFDataUtility.loadDataset(root, library);
		this.library=(SFObjectsLibrary)(dataset);
	}
	
	public SFViewerObjectsLibrary(String library){
		SFDataset dataset=SFDataUtility.loadDataset( library);
		this.library=(SFObjectsLibrary)(dataset);
	}
	
	public SFViewerObjectsLibrary(InputStream stream){
		try {
			SFDataset dataset=SFDataCenter.getDataCenter().readDataset(
					new SFInputStreamJava(stream, new DefaultExceptionKeeper()));
			this.library=(SFObjectsLibrary)(dataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SFViewerObjectsLibrary(){
		this.library=new SFObjectsLibrary();
	}
	
	@Override
	@SuppressWarnings("all")
	public void makeDatasetAvailable(String name, SFDataCenterListener listener) {
		SFDataset dataset=library.retrieveDataset(name);
		if(dataset==null){
			throw new SFException("SFViewerObjectsLibrary: Cannot Find A Dataset named "+name);
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
