package shadow.geometry.geometries;

import java.util.List;

import shadow.geometry.SFGeometryEngine;
import shadow.math.SFValuenf;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFLinearGrid;
import shadow.operational.meshes.SFCurvesSurface;
import shadow.operational.meshes.SFMeshEdge;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;

public class SFCurvesGeometry extends SFMeshGeometry{

	private SFLinearGrid<Integer>[][] allGrids;

	protected SFCurvesSurface surfaceMesh = new SFCurvesSurface();
	protected int[] gridSizes;
	protected int[] vxPositions;
	
	public SFCurvesGeometry(SFPrimitive primitive,SFCurvesSurface mesh){
		super(primitive);
		this.surfaceMesh=mesh;
	}
	
	
	
	public SFCurvesSurface getSurfaceMesh() {
		return surfaceMesh;
	}



	public SFLinearGrid<Integer>[][] getAllGrids() {
		return allGrids;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void compile() {
		super.compile();

		verifyArray();
		gridSizes=new int[getPrimitive().getGridsCount()];
		SFGeometryEngine.getGridsSize(primitive, gridSizes);//Do I need maxGridSize?
		
		allocateSurfaceVertices();
		
		List<SFMeshEdge> edges=surfaceMesh.getEdges();
		allGrids=(SFLinearGrid<Integer>[][])(new SFLinearGrid<?>[edges.size()][]);
		
		for (int edgeIndex = 0; edgeIndex < edges.size(); edgeIndex++) {
			SFMeshEdge edge=edges.get(edgeIndex);//get the edge
			int mainTessellation=edge.getMainTessellation();//get edge tessellation value
			
			allGrids[edgeIndex]=(SFLinearGrid<Integer>[])(new SFLinearGrid<?>[getPrimitive().getGridsCount()]);//Define the Linear Grids for the edge
			
			int mainTesselPosition=getArray().generateElements(mainTessellation);
			
			//SFValuenf[][] values=edge.extractValues(ns);
			for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
				//SFArray<SFValuenf> valueArray=getArray().getPrimitiveData(gridIndex);
				int totalSize=mainTessellation*(gridSizes[gridIndex]-1)+1;
				int position=getArray().getPrimitiveData(gridIndex).generateElements(totalSize-2);
				SFLinearGrid<Integer> lineGrid=new SFLinearGrid<Integer>(totalSize);
				lineGrid.setValue(0, edge.getEdgeConnectionIndex1()+vxPositions[gridIndex]);
				lineGrid.setValue(totalSize-1, edge.getEdgeConnectionIndex2()+vxPositions[gridIndex]);
				for (int j = 1; j < totalSize-1; j++) {
					lineGrid.setValue(j, position+(j-1));
				}
				allGrids[edgeIndex][gridIndex]=lineGrid;
				
				SFLinearGrid<Integer>[] grids=SFGridEngine.breakLinearGrid(lineGrid, gridSizes[gridIndex]); 
				
				SFPrimitiveIndices indices=getArray().generateSample();
				
				for (int i = 0; i < grids.length; i++) {
					getArray().getElement(i+mainTesselPosition, indices);
					SFLinearGrid<Integer> tmpGrid=grids[i];
					int gridPosition=getPrimitive().getIndicesPositions()[gridIndex];
					indices.getPrimitiveIndices()[gridPosition+0]=tmpGrid.getValue(0);
					indices.getPrimitiveIndices()[gridPosition+1]=tmpGrid.getValue(tmpGrid.getN()-1);
					for (int j = 1; j < tmpGrid.getN()-1; j++) {
						indices.getPrimitiveIndices()[gridPosition+1+j]=tmpGrid.getValue(j);
					}
					getArray().setElement(i+mainTesselPosition, indices);
				}
			}
		}

		
		update();
	}
	

	public void allocateSurfaceVertices() {
		int verticesNumber=surfaceMesh.getVertices().size();
		vxPositions=new int[getPrimitive().getGridsCount()];
		for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
			vxPositions[gridIndex]=getArray().getPrimitiveData(gridIndex).generateElements(verticesNumber);
		}
	}

	@Override
	public void update() {
		super.update();

		List<SFMeshEdge> edges=surfaceMesh.getEdges();
		for (int edgeIndex = 0; edgeIndex < edges.size(); edgeIndex++) {
			SFMeshEdge edge=edges.get(edgeIndex);
			SFValuenf[][] values=edge.extractValues(gridSizes);
			for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
				SFArray<SFValuenf> valueArray=getArray().getPrimitiveData(gridIndex);
				SFLinearGrid<Integer> lineGrid=allGrids[edgeIndex][gridIndex];
				for (int i = 0; i < lineGrid.getN(); i++) {
					int index=lineGrid.getValue(i);
					valueArray.setElement(index, values[gridIndex][i]);
				}
			}
		}
		
	}
	
}
