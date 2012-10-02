package shadow.geometry.geometries.structures;

import shadow.math.SFValuenf;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFLinearGrid;
import shadow.operational.grid.SFQuadrilateralGrid;
import shadow.operational.grid.SFRectangularGrid;
import shadow.operational.grid.SFTriangularGrid;
import shadow.operational.grid.macroGrid.SFMacroGrid;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPrimitiveArray;

public class SFQuadsCurvesMeshMacroGrid implements SFMacroGrid{
	
	private SFPrimitiveArray array;
	
	private SFRectangularGrid<Integer>[] indices;
	private SFRectangularGrid<SFValuenf>[] values;

	@SuppressWarnings("unchecked")
	public SFQuadsCurvesMeshMacroGrid(SFPrimitiveArray array,int edges, SFLinearGrid<Integer>[][] edgeGrids) {

		super();
		
		System.err.println("edges "+edges);
		int firstGridSize=array.getPrimitive().getGrid(0).getN();
		int widthElements = (edgeGrids[0][0].getN()-1)/(firstGridSize-1);
		int heghtElements = (edgeGrids[1][0].getN()-1)/(firstGridSize-1);
		
		int primitivesPosition=array.generateElements(heghtElements*widthElements);
		
		int gridsCount=array.getPrimitive().getGridsCount();
		
		indices=(SFRectangularGrid<Integer>[])(new SFRectangularGrid<?>[gridsCount]);
		values=(SFRectangularGrid<SFValuenf>[])(new SFRectangularGrid<?>[gridsCount]);
		
		for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
			
			SFPipelineGrid pipelineGrid=array.getPrimitive().getGrid(gridIndex);
			
			int width=edgeGrids[0][gridIndex].getN();
			int height=edgeGrids[1][gridIndex].getN();
			indices[gridIndex]=new SFRectangularGrid<Integer>(width,height);
			values[gridIndex]=new SFRectangularGrid<SFValuenf>(width,height);
		
			for (int j = 0; j < width; j++) {
				indices[gridIndex].setValue(0, j, edgeGrids[0][gridIndex].getValue(j));
				indices[gridIndex].setValue(height-1, width-j-1, edgeGrids[2][gridIndex].getValue(j));
			}
			for (int j = 0; j < height; j++) {
				indices[gridIndex].setValue(j, width-1, edgeGrids[1][gridIndex].getValue(j));
				indices[gridIndex].setValue(height-j-1, 0, edgeGrids[3][gridIndex].getValue(j));
			}
		
			int position=array.getPrimitiveData(gridIndex).generateElements((width-2)*(height-2));
			for (int i = 1; i < width-1; i++) {
				for (int j = 1; j < height-1; j++) {
					indices[gridIndex].setValue(j, i, position);
					position++;
				}
			}
			
			SFQuadrilateralGrid<Integer>[] quads=SFGridEngine.breakRectangularGrid(indices[gridIndex], pipelineGrid.getN());
			if(pipelineGrid.isTriangular()){
				SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
				SFGridEngine.loadPrimitiveIndices(array, primitivesPosition, gridIndex, triangles);
			}else{
				SFGridEngine.loadPrimitiveIndices(array, primitivesPosition, gridIndex, quads);	
			}
			
			SFGridEngine.printQuadsGrid(indices[gridIndex]);
			
			SFValuenf value=array.getPrimitiveData(gridIndex).generateSample();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					values[gridIndex].setValue(j, i, value.cloneValue());
				}
			}
		}
	}
	
	@Override
	public void updateInternalCircles() {
		
		//read external edge... //
		
		
	}
	
}
