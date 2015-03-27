package shadow.system.data;


/**
 * An Element which can be Described with a String Format
 * 
 * @author Alessandro Martinelli
 */
public interface SFCharsetObject {

	/**Set the value for the element using the String format
	 * @param value
	 */
	public void setStringValue(String value);
	
	/**Get the value of the element Described with the String Format
	 * @param value
	 */
	public String toStringValue();
}
