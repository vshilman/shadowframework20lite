package shadow.pipeline;

import java.util.ArrayList;
import java.util.List;

import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

/**
 * A complete description of a Geometric Primitive.
 * 
 * 		- The Map of Primitive and Slots. 
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitive extends SFProgramModule{

	private int[] indicesPositions;
	private int[] indicesSizes;
	private int indicesCount;
	private SFGridModel gridModel;
	
	private SFPrimitiveBlock blocks[]=new SFPrimitiveBlock[0];
	private SFPipelineGrid grids[];
	private int gridBlocksIndex[];
	private short types[];
	//private SFPrimitiveGrid grids[];
	
	public SFPrimitive() {
		super();
	}
	
	public SFPrimitive(String name,SFGridModel gridModel) {
		super(name);
		this.gridModel=gridModel;
	}
	
	public void setPrimitiveElements(SFPrimitiveBlock[] blocks,SFProgramComponent[] components){
		this.blocks=blocks;
		this.components=components;
		generateGridInstances();
	}

	
	public void generateGridInstances(){
		/*C++: 
		 * if(gridInstances!=null) 
		 * 		delete gridInstances;
		 * */
		List<SFPipelineGrid> grids=new ArrayList<SFPipelineGrid>();
		List<Integer> gridBlocksIndex=new ArrayList<Integer>();
		List<Short> types=new ArrayList<Short>();
		for (int i = 0; i < blocks.length; i++) {
			List<SFPipelineGrid> grid=getComponents()[i].getGrid();
			for (SFPipelineGrid sfPipelineGridInstance : grid) {
				grids.add(sfPipelineGridInstance);
				gridBlocksIndex.add(i);
				short type=sfPipelineGridInstance.getParameterType(0);
				if(type==SFParameteri.GLOBAL_GENERIC)
					type=blocks[i].getType();
				types.add(type);
			}
		}
		//C++: delete grids;
		this.grids=grids.toArray(new SFPipelineGrid[grids.size()]);
		this.gridBlocksIndex=new int[gridBlocksIndex.size()];
		for (int i = 0; i < this.gridBlocksIndex.length; i++) {
			this.gridBlocksIndex[i]=gridBlocksIndex.get(i);
		}
		this.types=new short[types.size()];
		for (int i = 0; i < this.types.length; i++) {
			this.types[i]=types.get(i);
		}
		generateIndicesSizes();
	}
	
	/**
	 * @return -1 if there is no Txo Block
	 */
	public int findBlockPosition(SFPrimitiveBlock block){
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==block){
				return i;
			}
		}
		return -1;
	}
	
	public SFProgramComponent[] getComponents(){
		return components;
	}
	
	public SFPipelineGrid[] getGrids() {
		return grids;
	}
	
	public SFGridModel getGridModel() {
		return gridModel;
	}

	public int getGridsCount(){
		return grids.length;
	}
	
	public SFPrimitiveBlock getBlock(int gridIndex){
		return blocks[gridBlocksIndex[gridIndex]];
	}

	public SFPipelineGrid getGrid(int gridIndex){
		return grids[gridIndex];
	}

	public short getType(int gridIndex){
		return types[gridIndex];
	}
	
	public int getGridBlocksIndex(int gridIndex){
		return gridBlocksIndex[gridIndex];
	}
	
	public SFPrimitiveBlock[] getBlocks() {
		return blocks;
	}

	public SFPrimitiveBlock getBlock(SFPipelineRegister register) {
		String registerName=register.getName();
		char[] rName=registerName.toCharArray(); 
		
		SFPrimitiveBlock block=SFPrimitiveBlock.POSITION;
		if(rName[0]=='N'){
			block=SFPrimitiveBlock.NORMAL;
		}else if(rName.length>1 && rName[0]=='T' && rName[1]=='x'){
			block=SFPrimitiveBlock.TXO;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='u'){
			block=SFPrimitiveBlock.DU;
		}else if(rName.length>1 && rName[0]=='d' && rName[1]=='v'){
			block=SFPrimitiveBlock.DV;
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
	
	public boolean isQuad(){
		return gridModel==SFGridModel.Quad;
	}

	private void generateIndicesSizes() {
		indicesPositions=new int[this.grids.length];
		indicesSizes=new int[this.grids.length];
		indicesCount=0;
		for (int i = 0; i < this.grids.length; i++) {
			indicesPositions[i]=indicesCount;
			indicesSizes[i]=this.grids[i].getGridSize();
			indicesCount+=indicesSizes[i];
		}
	}
	
	
	public SFPrimitive getConstructionPrimitive(){
		SFPrimitive primitive=new SFPrimitive("",this.gridModel);
		
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==SFPrimitiveBlock.POSITION){
				SFProgramComponent[] components={this.components[i]};
				SFPrimitiveBlock[] blocks={SFPrimitiveBlock.UV};
				primitive.setPrimitiveElements(blocks, components);
			}
		}
		
		return primitive;
	}
}
