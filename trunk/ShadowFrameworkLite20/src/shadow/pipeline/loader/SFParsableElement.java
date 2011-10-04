package shadow.pipeline.loader;

import java.util.Collection;

public interface SFParsableElement {

	public void setName(String name);
	
	/**
	 * the end command is called 
	 */
	public abstract void finalize();
	
	/**
	 * @return the list of commands that 
	 */
	public abstract Collection<String> getAllCommands();
}
