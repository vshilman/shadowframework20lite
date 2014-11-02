package shadow.system.data;

public interface SFLibrary {

	/**
	 * Look for the {@link Dataset} registered with the given name
	 * @param name the name of the {@link Dataset}
	 * @return
	 */
	public abstract SFDataAsset<?> retrieveDataset(String name);

}