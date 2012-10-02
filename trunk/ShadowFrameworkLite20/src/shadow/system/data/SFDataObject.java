package shadow.system.data;

/**
 * Keeps informations for some SF element. Can be written on an SFOutputStream
 * or read from an SFInputStream
 * 
 * @author Alessandro Martinelli
 */
public interface SFDataObject{
	
	/**Read all the data from a stream, given the cardinality 
	 * associated with this data. The Data read from readFromStream
	 * should come from a previous call of writeOnStream, and
	 * N should be the same it was when writeOnStream was called. */
	public void readFromStream(SFInputStream stream);
	/** write the content of this dataset
	 */
	public void writeOnStream(SFOutputStream stream);
	
	public SFDataObject clone();
	
}
