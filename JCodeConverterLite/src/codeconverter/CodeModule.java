package codeconverter;


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
}
