package shadow.system;

/**
 * Main class for any element in the Framework, which can be used somewhere.
 * 
 * User can call {@link SFElement.use} once, than they can call {@link SFElement.free}.
 * Each user can call free a number of times not greater than the number of times they have called use.
 * Unexpected behaviors may occur when this rule is not respected.
 * 
 * @author Alessandro Martinelli
 */
public class SFElement {

	private int count;

	/**
	 * Call this when you want to use this element
	 */
	public void use(){
		count++;
	}
	
	/**
	 * Call this when you don't need this element any more
	 */
	public void free(){
		count--;
	}
	
	/**
	 * @return true, if this element is no more used by any user
	 */
	public boolean isFree(){
		return count==0;
	}
}
