package shadow.system;

/**
 * Any Exception launched because the Shadow Framework 
 * has encountered a problem.
 * @author Alessandro Martinelli
 */
public class SFException extends RuntimeException{
	
	private static final long serialVersionUID = 0;
	
	public SFException(String arg0) {
		super(arg0);
	}
}
