package shadow.system.data;

/**
 * @author Alessandro Martinelli
 */
public interface SFCharsetObjectList {
	
	public int getSize();

	public String getCharSetObjectString(int index);
	
	public void addCharSetObjects(String value);
}
