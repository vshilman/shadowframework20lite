package shadow.system.data;

/**
 * A CallBack from {@link SFIDataCenter}
 * 
 * @author Alessandro Martinelli
 */
public interface SFDataCenterListener<T extends SFDataset> {
	/**
	 * Called when a Dataset is ready 
	 * @param dataset the ready dataset
	 */
	public void onDatasetAvailable(String name, T dataset);
	
}
