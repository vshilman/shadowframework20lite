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

	public SFIDataCenter getDataCenterImplementation() {
		return dataCenterImplementation;
	}

	public void makeDatasetAvailable(String object,SFDataCenterListener<?> memoryPointer){
		dataCenterImplementation.makeDatasetAvailable(object, memoryPointer);
	}
	
	public SFDataset getAlreadyAvailableDataset(String object) throws NullPointerException{
		final SFDataset[] datasets=new SFDataset[1];
		SFDataCenter.getDataCenter().makeDatasetAvailable(object, new SFDataCenterListener<SFDataset>() {
			@Override
			public void onDatasetAvailable(String name, SFDataset dataset) {
				datasets[0]=dataset;
			}
		});
		if(datasets[0]==null)
			throw new NullPointerException("Dataset is not available");
		return datasets[0];
	}
	
	@Override
	public SFDataset readDataset(SFInputStream stream) {
		return dataCenter.datasetFactory.readDataset(stream);
	}
	
	@Override
	public void writeDataset(SFOutputStream stream, SFDataset dataset) {
		dataCenter.datasetFactory.writeDataset(stream, dataset);
	}

	public SFDataset createDataset(String type){
		return dataCenter.datasetFactory.createDataset(type);
	}
}
