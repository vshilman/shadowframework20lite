package codeconverter;

/**
 * A Module in the Code, may have different level of granularity,
 * and sometimes may have subModules.
 *
 * For example, a class will have function and a function will have lines of code.
 *
 * Code Mudule is based upon a Composite Pattern, and play the role of an Abstract Element
 *
 * @author Alessandro Martinelli
 */
public interface CodeModule {

	public int getSize();
	public CodeModule getSubModule(int index);
	/**
	 * Print this CodeModule and all its submodules into a String
	 * @return
	 */
	public String print();
	/**
	 * Gives a String representation of this module, not as complete as print
	 * @return
	 */
	@Override
	public String toString();

	public String getCode();


}
