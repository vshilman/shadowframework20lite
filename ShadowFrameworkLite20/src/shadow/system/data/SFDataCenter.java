package shadow.system.data;

/**
 * A Singleton class providing a container for
 * one effective IDataCenter implementation 
 * 
 * @author Alessandro Martinelli
 */
public class SFDataCenter implements SFIDataCenter, SFAbstractDatasetFactory{

	public static SFDataCenter dataCenter=new SFDataCenter(); 
	
	private SFIDataCenter dataCenterImplementation;
	
	private SFAbstractDatasetFactory datasetFactory;

	private SFDataCenter(){
		//singleton
	}
	
	public static void setDataCenterImplementation(SFIDataCenter dataCenterImplementation) {
		dataCenter.dataCenterImplementation = dataCenterImplementation;
	}
	
	public static void setDatasetFactory(SFAbstractDatasetFactory datasetFactory) {
		dataCenter.datasetFactory = datasetFactory;
	}
	
	public static SFDataCenter getDataCenter() {
		return dataCenter;
	}

	public void makeDatasetAvailable(String object,SFDataCenterListener memoryPointer){
		dataCenterImplementation.makeDatasetAvailable(object, memoryPointer);
	}
	
	@Override
	public SFDataset createDataset(String typeName) {
		return datasetFactory.createDataset(typeName);
	}
	
	@Override
	public void releaseDataset(String name, SFDataCenterListener listener) {
		dataCenterImplementation.releaseDataset(name, listener);
	}
}
