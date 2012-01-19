package tests.blocks;

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
		return "DBLOCK "+blockDeclaration.codeLine+"\n"+relatedBlock.print();
	}
	
	@Override
	public String toString() {
		return "DBLOCK "+blockDeclaration.codeLine+"\n"+relatedBlock;
	}
}
