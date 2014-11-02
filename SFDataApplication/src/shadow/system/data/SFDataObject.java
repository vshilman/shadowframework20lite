package shadow.system.data;

/**
 * Keeps informations for some SF element. Can be written on an SFOutputStream
 * or read from an SFInputStream
 * 
 * @author Alessandro Martinelli
 */
public interface SFDataObject{
	
	/**Read all the data from a stream. The Data read from readFromStream
	 * should come from a previous call of writeOnStream. */
	public void readFromStream(SFInputStream stream);
	
	/** write the content of this dataset
	 */
	public void writeOnStream(SFOutputStream stream);
	
	/**
	 * Create a new empty copy of this DataObject
	 * @return
	 */
	public SFDataObject copyDataObject();
	
}
