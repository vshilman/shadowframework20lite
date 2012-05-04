package shadow.operational.grid;

import shadow.math.SFValuenf;
import shadow.operational.SFGridMap;
import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipelineGrid;
import shadow.system.SFArray;

public class SFGridOperations {

	/**
	 * Correct values using one grid rewrite rules
	 * Corrected values are written into an array.
	 * 
	 * @param grid The grid.
	 * @param array the array where the elaboration output is placed.
	 * @param data an array of vertices containing all necessary
	 * @param indices 
	 * @param offset An offset to be applied to indices.
	 */
	public static void correctValues(SFPipelineGrid grid,SFArray<SFValuenf> array,SFValuenf[] data, int[] indices,int indicesOffset,int offset) {
		
		SFGridMap map=new SFGridMap(grid, data, indices, indicesOffset);
		SFFunction[] functions=grid.getFunctions();
		
		for (int k = 0; k < functions.length; k++) {
			SFFunction function=functions[k];
			String name=function.getParameter().getName();
			int index=grid.getNameIndex(name);
			SFValuenf value=function.getFunction().evaluate(map);
			//data[indices[index]].set(value);
			array.setElement(offset+indices[index+indicesOffset], value);
		}
	}

	public static int getGridDimension(SFPipelineGrid grid) {
		return grid.getEdges()[0].length-1;
	}

	/**
	 * Generate The indices of 2 triangles which approximate a quad.
	 * 
	 * @param triangularGrid the reference triangular grid used to define the two triangles
	 * @param output 
	 * @param offset
	 * @param size
	 * @param indices
	 */
	public static void generateQuadTrianglesIndices(SFPipelineGrid triangularGrid,int[][] output, 
			int positionInOutputs, int size, int[] indices) {
		
		
		int gridDimension=getGridDimension(triangularGrid);
		
		int [] tmp_S=output[0];
			tmp_S[positionInOutputs+triangularGrid.getCorners()[0]]=(indices[0]);
			tmp_S[positionInOutputs+triangularGrid.getCorners()[1]]=(indices[size-1]);
			tmp_S[positionInOutputs+triangularGrid.getCorners()[2]]=(indices[gridDimension*size]);
			for (int k = 1; k < triangularGrid.getEdges()[0].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[0][k]]=indices[k];
			}
			for (int k = 1; k < triangularGrid.getEdges()[1].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[1][k]]=indices[size-1+k*gridDimension];
			}
			for (int k = 1; k < triangularGrid.getEdges()[2].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[2][k]]=indices[gridDimension*size-k*size];
			}
			int position=gridDimension*3;//three edges
			for (int l = 1; l < gridDimension; l++) {
				for (int k = 1; k < gridDimension-l; k++) {
					tmp_S[positionInOutputs+position]=indices[k+l*(size)];
					position++;
				}
			}	
			
		tmp_S=output[1];
			tmp_S[positionInOutputs+triangularGrid.getCorners()[0]]=(indices[size*size-1]);
			tmp_S[positionInOutputs+triangularGrid.getCorners()[1]]=(indices[gridDimension*size]);
			tmp_S[positionInOutputs+triangularGrid.getCorners()[2]]=(indices[size-1]);
			for (int k = 1; k < triangularGrid.getEdges()[0].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[0][k]]=indices[size*size-1-k];
			}
			for (int k = 1; k < triangularGrid.getEdges()[1].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[1][k]]=indices[size*size-1-(size-1+k*gridDimension)];
			}
			for (int k = 1; k < triangularGrid.getEdges()[2].length-1; k++) {
				tmp_S[positionInOutputs+triangularGrid.getEdges()[2][k]]=indices[size*size-1-(gridDimension*size-k*size)];
			}
			position=gridDimension*3;//three edges
			for (int l = 1; l < gridDimension; l++) {
				for (int k = 1; k < gridDimension-l; k++) {
					tmp_S[positionInOutputs+position]=indices[size*size-1-k-l*(size)];
					position++;
				}
			}
	}

}
