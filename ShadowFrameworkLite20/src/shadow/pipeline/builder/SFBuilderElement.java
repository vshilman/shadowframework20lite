package shadow.pipeline.builder;

import java.util.List;

public interface SFBuilderElement {

	public void setName(String name);
	
	/**
	 * the end command is called 
	 */
	public abstract void finalize();
	
	/**
	 * @return the list of commands that 
	 */
	public abstract List<String> getAllCommands();
}
