package shadow.operational;

import shadow.math.SFValuenf;
import shadow.operational.grid.SFGridOperations;
import shadow.pipeline.SFPipelineGrid;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public class SFStandardQuadToTriangleExtractor implements SFSurfaceQuadsExtractor{

	private SFPipelineGrid grid;
	private int gridDimension;
	
	protected SFStandardQuadToTriangleExtractor(SFPipelineGrid grid){
		this.grid=grid;
		gridDimension=SFGridOperations.getGridDimension(grid);
	}

	@Override
	public void extractIndices(int[][] output, int positionInOutputs, SFArray<SFValuenf> array, SFValuenf[] data,
			int offset, int Nu, int Nv, int I, int J) throws SFArrayElementException {

		int K=gridDimension;
		
		int size=gridDimension+1;
		int indices[]=new int[size*size];
		int index00=(J*K)+(I*K)*((K*(Nu-1)+1))+offset;
		for (int k = 0; k < size; k++) {
			for (int j = 0; j < size; j++) {
				indices[k*size+j]=index00+j+k*(K*(Nu-1)+1);
			}
		}
		
		for (int k = 0; k < indices.length; k++) {
			array.setElement(indices[k], data[indices[k]-offset]);
		}	
		
		//step 3 setup indices..... this is hard, much hard.
		SFGridOperations.generateQuadTrianglesIndices(grid, output, positionInOutputs, size, indices);
		SFGridOperations.correctValues(grid,array, data, output[0], positionInOutputs, offset);
		SFGridOperations.correctValues(grid,array, data, output[1], positionInOutputs, offset);
	}
	
	@Override
	
	public int getPrimitivesNumber() {
		return 2;//Quad is split into 2 triangles
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
