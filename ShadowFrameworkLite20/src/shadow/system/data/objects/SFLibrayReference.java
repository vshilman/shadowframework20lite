package shadow.system.data.objects;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;

public class SFLibrayReference extends SFCompositeDataArray implements SFDataCenterListener{

	private SFString datasetName;
	private SFDataset dataset=null;
	
	@Override
	public void generateData() {
		datasetName=new SFString("");
		addDataObject(datasetName);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFLibrayReference();
	}
	
	public void setReference(String name){
		datasetName.setLabel(name);
	}
	
	public String getReference(){
		return datasetName.getLabel();
	}
	
	
	
	public void setDataset(SFDataset dataset) {
		this.dataset = dataset;
	}

	public SFDataset retrieveDataset(){
		if(dataset==null){
			SFDataCenter.getDataCenter().makeDatasetAvailable(datasetName.getLabel(), this);
		}
		return dataset;
	}
	
	public void releaseReference(){
		
	}
	
	@Override
	public void onDatasetAvailable(String name, SFDataset dataset) {
		this.dataset=dataset;
	}
	
	@Override
	public void onDatasetUnAvailable(String name) {
		//TODO: should feedback something
	}
}
