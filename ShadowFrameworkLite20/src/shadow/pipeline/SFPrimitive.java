package shadow.pipeline;

import java.util.ArrayList;
import java.util.List;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

/**
 * A complete description of a Geometric Primitive.
 * 
 * 		- It's tessellator.
 * 		- The Map of Primitive and Slots. 
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitive {

	public enum PrimitiveBlock{
		POSITION(SFPipelineRegister.getFromName("P")),
		NORMAL(SFPipelineRegister.getFromName("N")),
		DU(SFPipelineRegister.getFromName("du")),
		DV(SFPipelineRegister.getFromName("dv")),
		TXO(SFPipelineRegister.getFromName("Tx0"));
		private SFPipelineRegister register;

		private PrimitiveBlock(SFPipelineRegister register) {
			this.register = register;
		}
		
		public SFPipelineRegister getRegister(){
			return register;
		}
		
		public short getType(){
			return register.getType();
		}
		
		public static PrimitiveBlock getBlock(SFPipelineRegister register){
			PrimitiveBlock[] blocks=PrimitiveBlock.values();
			for (int i = 0; i < blocks.length; i++) {
				if(blocks[i].getRegister()==register){
					return blocks[i];
				}
			}
			return POSITION;
		}

		public int getIndex() {
			return ordinal();
		}

		public static PrimitiveBlock getBlock(int index){
			return values()[index];
		}
	}
	
	private SFProgramComponent tessellator; 
	private int[] indicesPositions;
	private int[] indicesSizes;
	private int indicesCount;
	private int positionBlockIndex; 
	private boolean isQuad;
	
	private PrimitiveBlock[] blocks=new PrimitiveBlock[0];
	private SFProgramComponent[] components=new SFProgramComponent[0];
	private SFPrimitiveGrid grids[];
	
	public SFPrimitive() {
		super();
	}
	
	//TODO Remove this!!
	public boolean containRegister(SFPipelineRegister register){
		for (int i = 0; i < blocks.length; i++) {
			if(register==blocks[i].getRegister())
				return true;
		}
		return false;
	}
	
	
	public void setPrimitiveElements(PrimitiveBlock[] blocks,SFProgramComponent[] components){
		this.blocks=blocks;
		this.components=components;
		generateGridInstances();
	}
	
	
	public void addPrimitiveElement(PrimitiveBlock block,SFProgramComponent component){
		//The implementation of this method should appear non optimal;
		//nevertheless you should be aware that this is the least called method of SFPrimitive 
		//(No more than 4-5 times for each primitive)
		//and the choice in here will accelerate any other (much more used) call in this class
		//Furthermore, viewers will also call setPrimitiveElements instead of this.
		PrimitiveBlock[] tmpBlocks=new PrimitiveBlock[this.blocks.length+1];
		for (int i = 0; i < blocks.length; i++) {
			tmpBlocks[i]=blocks[i];
		}
		tmpBlocks[blocks.length]=block;
		blocks=tmpBlocks;
		//C++:delete blocks;
		SFProgramComponent[] tmpComponents=new SFProgramComponent[this.components.length+1];
		for (int i = 0; i < components.length; i++) {
			tmpComponents[i]=components[i];
		}
		tmpComponents[components.length]=component;
		components=tmpComponents;
		//C++:delete components;
		generateGridInstances();
	}
	
	public void generateGridInstances(){
		/*C++: 
		 * if(gridInstances!=null) 
		 * 		delete gridInstances;
		 * */
		List<SFPrimitiveGrid> grids=new ArrayList<SFPrimitiveGrid>();
		for (int i = 0; i < components.length; i++) {
			List<SFPipelineGridInstance> grid=getComponents()[i].getGrid();
			for (SFPipelineGridInstance sfPipelineGridInstance : grid) {
				SFPrimitiveGrid grid_=new SFPrimitiveGrid(blocks[i], sfPipelineGridInstance);
				grids.add(grid_);
				if(blocks[i]==PrimitiveBlock.POSITION && sfPipelineGridInstance.getParameters().get(0).getType()==SFParameteri.GLOBAL_GENERIC){
					positionBlockIndex=grids.size()-1;
					isQuad=grid_.getGrid().getCorners().length==4;
				}
			}
		}
		//C++: delete grids;
		this.grids=grids.toArray(new SFPrimitiveGrid[grids.size()]);
		generateIndicesSizes();
	}
	
	/**
	 * @return -1 if there is no Txo Block
	 */
	public int findTxoBlockPosition(){
		for (int i = 0; i < grids.length; i++) {
			if(grids[i].getBlock()==PrimitiveBlock.TXO){
				return i;
			}
		}
		return -1;
	}

	public int getPositionBlockIndex() {
		return positionBlockIndex;
	}

	public PrimitiveBlock[] getBlocks(){
		return blocks;
	}
	
	public SFProgramComponent[] getComponents(){
		return components;
	}
	
	public SFPrimitiveGrid[] getGridInstances() {
		return grids;
	}

	public SFProgramComponent getTessellator() {
		return tessellator;
	}

	public void setAdaptingTessellator(SFProgramComponent adaptingTessellator) {
		this.tessellator=adaptingTessellator;
	}
	
	public boolean isQuad(){
		return isQuad;
	}
	
	public PrimitiveBlock getBlock(SFPipelineRegister register) {
		String registerName=register.getName();
		char[] rName=registerName.toCharArray(); 
		
		PrimitiveBlock block=PrimitiveBlock.POSITION;
		if(rName[0]=='N'){
			block=PrimitiveBlock.NORMAL;
		}else if(rName.length>1 && rName[0]=='T' && rName[1]=='x'){
			block=PrimitiveBlock.TXO;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='u'){
			block=PrimitiveBlock.DU;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='v'){
			block=PrimitiveBlock.DV;
		}
		return block;
	}
	
	public int[] getIndicesPositions() {
		return indicesPositions;
	}
	
	public int getIndicesCount() {
		return indicesCount;
	}
	
	public int[] getIndicesSizes() {
		return indicesSizes;
	}

	private void generateIndicesSizes() {
		indicesPositions=new int[this.grids.length];
		indicesSizes=new int[this.grids.length];
		indicesCount=0;
		for (int i = 0; i < this.grids.length; i++) {
			indicesPositions[i]=indicesCount;
			indicesSizes[i]=this.grids[i].getGrid().getGridSize();
			indicesCount+=indicesSizes[i];
		}
	}
}
