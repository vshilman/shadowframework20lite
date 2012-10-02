package shadow.geometry;

import shadow.operational.grid.SFLinearGrid;
import shadow.operational.grid.macroGrid.SFMacroGrid;
import shadow.pipeline.SFMesh;

public interface SFCurvesMeshSchema {

	/**
	 * Generate a Grid which can be used to control this Curves Mesh Schema
	 * 
	 * @param edges the number of main-edges of this 
	 * @param edgesSizes the number of subdivisions for each edge
	 * @param gridSize the grid size for each edge
	 * @return
	 */
	public SFMacroGrid generateMacroGrid(SFMesh mesh,int edges,SFLinearGrid<Integer>[][] edgeGrids);
}