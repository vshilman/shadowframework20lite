package shadow.material;


/**
 * A step in a Rendering algorithm.
 * 
 * @author Alessandro Martinelli
 */
public interface SFLightStep {

	/**
	 * Prepare this step. Step preparation should include pipeline settings
	 */
	public void prepareStep();
	/**
	 * Close this step. Some rendering programs may close rendering here.
	 */
	public void closeStep();
	/**
	 * @return the name of the program which should be used as Light Program Component
	 */
	public String getProgramName();
}
