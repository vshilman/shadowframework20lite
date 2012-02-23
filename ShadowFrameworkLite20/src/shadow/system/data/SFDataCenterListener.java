package shadow.system.data;

/**
 * A CallBack from {@link SFIDataCenter}
 * 
 * @author Alessandro Martinelli
 */
public interface SFDataCenterListener {
	/**
	 * Called when a Dataset is ready 
	 * @param dataset the ready dataset
	 */
	public void onDatasetAvailable(String name, SFDataset dataset);
	
	/**
	 * Called when a Dataset cannot be loaded
	 * @param name the dataset name
	 */
	public void onDatasetUnAvailable(String name);
}
