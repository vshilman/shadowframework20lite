package tests.blocks;

public class CodeLine implements CodeModule{
	public String codeLine;
	public boolean isBlockDeclaration;
	
	public CodeLine(String codeLine, boolean isBlockDeclaration) {
		super();
		this.codeLine=codeLine;
		this.isBlockDeclaration=isBlockDeclaration;
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
		return (isBlockDeclaration?"blockDeclaration:":"sentence:")+codeLine;
	}
	
	@Override
	public String toString() {
		return print();
	}
}
