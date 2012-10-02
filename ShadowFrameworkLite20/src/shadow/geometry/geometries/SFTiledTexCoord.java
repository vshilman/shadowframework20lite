package shadow.geometry.geometries;

import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFQuadrilateralGrid;
import shadow.operational.grid.SFTriangularGrid;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitiveArray;
import shadow.renderer.SFTilesSet;
import shadow.system.SFArray;
import shadow.system.SFException;

public class SFTiledTexCoord extends SFMeshGeometry{

	private int[] matrix;
	private SFTilesSet space;
	private int tilesX;
	private int tilesY;
	
	public SFTiledTexCoord(int[] matrix, SFTilesSet space,int tilesX,int tilesY) {
		super();
		this.matrix = matrix;
		this.space = space;
		this.tilesX=tilesX;
		this.tilesY=tilesY;
	}
	
	public SFTiledTexCoord(int[] matrix,int tilesX,int tilesY) {
		super();
		this.matrix = matrix;
		this.tilesX=tilesX;
		this.tilesY=tilesY;
	}
	
	@SuppressWarnings("unchecked")
	public void compile() {
		super.compile();
		
		if(getArray()==null)			
			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(getPrimitive()));
		SFPrimitiveArray array=getArray();
		
		int gridN=array.getPrimitive().getGrid(0).getN();
		//vs.length=Nv * (gridN-1)+1;
		int gridX= (tilesX);
		int gridY= (tilesY);
		if(matrix.length!=gridX*gridY){
			throw new SFException("SFTiledTexCoord with "+matrix.length+" total tiles is being used on a "+gridX+"x"+gridY+" Grid!");
		}
		
		int primitiveIndex=0;
		if(getPrimitive().isQuad()){
			primitiveIndex=array.generateElements(gridX*gridY);
			setFirstElement(primitiveIndex);
			setLastElement(primitiveIndex+gridX*gridY);
		}else{
			primitiveIndex=array.generateElements(gridX*gridY*2);
			setFirstElement(primitiveIndex);
			setLastElement(primitiveIndex+gridX*gridY*2);
		}
		
		int modelPosition=0;
		int deltaPosition=gridN*gridN;
		float stepX=space.getStepX()*1.0f/((gridN-1));
		float stepY=space.getStepY()*1.0f/((gridN-1));
		for (int x = 0; x < space.getSizeX(); x++) {
			for (int y = 0; y < space.getSizeY(); y++) {

				int tileIndex=x*space.getSizeY()+y;
				//SFRectangularGrid<SFValuenf> values=grids[tileIndex];
				SFArray<SFValuenf> arrayValues=array.getPrimitiveData(0);
				int position=arrayValues.generateElements(gridN*gridN);
				float xSpace=space.getStepX()*x;
				float ySpace=space.getStepY()*y;
				
				for (int indexX = 0; indexX < gridN; indexX++) {
					for (int indexY = 0; indexY < gridN; indexY++) {
						arrayValues.setElement(position+indexX*gridN+indexY, new SFVertex2f(xSpace+stepX*indexX, ySpace+stepY*indexY));
					}
				}

				if(tileIndex==0){
					modelPosition=position;
				}
			}
		}
		
		for (int x = 0; x < gridX; x++) {
			for (int y = 0; y < gridY; y++) {
				
				int quadIndex=x*gridY+y;
				int tileIndex=matrix[quadIndex];
				SFQuadrilateralGrid<Integer> indices=new SFQuadrilateralGrid<Integer>(gridN);
				
				SFPipelineGrid pipelineGrid=array.getPrimitive().getGrid(0);

				SFGridEngine.generateIndices(indices/*values, , arrayValues, modelPosition+deltaPosition*tileIndex*/);
				int deltaIndex=modelPosition+deltaPosition*tileIndex;
				for (int i = 0; i < gridN; i++) {
					for (int j = 0; j < gridN; j++) {
						indices.setValue(i, j, indices.getValue(i, j)+deltaIndex);
					}
				}

				SFQuadrilateralGrid<Integer>[] quads=(SFQuadrilateralGrid<Integer>[])(new SFQuadrilateralGrid<?>[1]);
				quads[0]=indices;
				if(pipelineGrid.isTriangular()){
					SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
					SFGridEngine.loadPrimitiveIndices(array, primitiveIndex, 0, triangles);
					primitiveIndex+=triangles.length;
					
				}else{
					SFGridEngine.loadPrimitiveIndices(array, primitiveIndex, 0, quads);
					primitiveIndex+=1;
				}
			}
		}
		SFIndexRange[] ranges={new SFIndexRange(modelPosition, deltaPosition*space.getSizeX()*space.getSizeY())};
		
		getMesh().setPrimitiveDataRanges(ranges);
	
	}

	public int[] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[] matrix) {
		this.matrix = matrix;
	}

	public SFTilesSet getSpace() {
		return space;
	}

	public void setSpace(SFTilesSet space) {
		this.space = space;
	}

	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
	
}

