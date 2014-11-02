
package shadow.system;

/**
 * Sometying which can be updated
 * 
 * @pattern interface
 * 
 * @author Alessandro Martinelli
 */
public interface SFUpdatable {
	/**
	 * Update this updatable.
	 * Refer to Specific modules to understand how they will be updated
	 */
	public void update();
}
