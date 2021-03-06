package codeconverter;

public class DeclaredBlock implements CodeModule{

	private CodeLine blockDeclaration;
	private Block relatedBlock;

	public DeclaredBlock(CodeLine blockDeclaration, Block relatedBlock) {
		super();
		this.blockDeclaration=blockDeclaration;
		this.relatedBlock=relatedBlock;
	}

	@Override
	public int getSize() {
		return 2;
	}
	@Override
	public CodeModule getSubModule(int index) {
		if(index==0){
			return blockDeclaration;
		}
		return relatedBlock;
	}

	public CodeLine getBlockDeclaration() {
		return blockDeclaration;
	}

	public Block getRelatedBlock() {
		return relatedBlock;
	}


	@Override
	public String print() {
		return "DBLOCK "+blockDeclaration.getCode()+"\n"+relatedBlock.print()+"["+getFirstLine()+","+getLastLine()+"]";
	}

	@Override
	public String toString() {
		return "BLOCK ["+blockDeclaration.getCode()+"]";
	}

	@Override
	public String getCode() {
		return blockDeclaration.getCode()+" "+relatedBlock.getCode();
	}

	@Override
	public String getExtendedCode() {
		return blockDeclaration.getCode()+" "+relatedBlock.getExtendedCode();
	}

	@Override
	public int getFirstLine() {
		return blockDeclaration.getFirstLine();
	}

	@Override
	public int getLastLine() {
		int x=relatedBlock.getLastLine();
		if(x>=0){
			return x;
		}
		return blockDeclaration.getLastLine();
	}
}
