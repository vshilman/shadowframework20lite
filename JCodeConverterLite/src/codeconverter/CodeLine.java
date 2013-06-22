package codeconverter;

/**
 * A Code Line, which is a CodeModule.
 *
 * As part of the CodeModule's composite Pattern, this stay for a Concrete Leaf
 *
 * @author Alessandro Martinelli
 */
public class CodeLine implements CodeModule{

	private String codeLine;
	private boolean isBlockDeclaration;


	public CodeLine(String codeLine, boolean isBlockDeclaration) {
		super();
		this.codeLine = codeLine;
		this.isBlockDeclaration = isBlockDeclaration;
	}
	public int getSize() {
		return 0;
	}
	@Override
	public CodeModule getSubModule(int index) {
		return null;
	}
	@Override
	public String print() {
		return (isBlockDeclaration()?"blockDeclaration:":"sentence:")+getCode();
	}

	@Override
	public String toString() {
		return print();
	}

	public void setCodeLine(String codeLine) {
		this.codeLine = codeLine;
	}
	/**
	 * @return the String content of this Line of Code
	 */
	public String getCode() {
		return codeLine;
	}

	public void setBlockDeclaration(boolean isBlockDeclaration) {
		this.isBlockDeclaration = isBlockDeclaration;
	}
	/**
	 * Tells whether this line is a Block Declaration line
	 * @return
	 */
	public boolean isBlockDeclaration() {
		return isBlockDeclaration;
	}
}
