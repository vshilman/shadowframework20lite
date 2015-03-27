package shadow.system.data;

/**
 * A List of elements which can be Described with a String Format
 * 
 * @author Alessandro Martinelli
 */
public interface SFCharsetObjectList{

	/**Set the value for the elements using the String format
	 * @param value
	 */
	public void setStringValues(String[] values);
	
	/**Get the values of the elements described with the String Format
	 * @param value
	 */
	public String[] toStringValues();
	
//	/**
//	 * Number of Elements
//	 * @return 
//	 */
//	public int getSize();
//
//	/**
//	 * Converts the index-th Object into a String
//	 * @param index
//	 * @return
//	 */
//	public String getCharSetObjectString(int index);
//	
//	/**
//	 * Add an Object to the list using the String format
//	 * @param value
//	 */
//	public void addCharSetObjects(String value);
}
