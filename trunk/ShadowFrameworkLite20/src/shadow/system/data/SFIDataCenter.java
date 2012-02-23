package shadow.system.data;


/**
 * An abstract element whose responsibility is loading
 * and releasing datasets. 
 * 
 * If more SFDataCenterListeners require the same object,
 * the Object itself should be released only when more elements are acquired.
 * 
 * @author Alessandro Martinelli
 */
//NOTE: This class may also be called 'SFMemory'
public interface SFIDataCenter {
	/**
	 * Ask this data center to load a data
	 * @param name  the name of the Dataset which should be loaded
	 * @param listener a listener, called after this DataCenter have tried to load the DataObject. 
	 */
	public void makeDatasetAvailable(String name,SFDataCenterListener listener);

	
	public void releaseDataset(String name,SFDataCenterListener listener);
}
