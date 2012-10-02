package shadow.geometry.geometries.structures;

import shadow.geometry.SFCurvesMeshSchema;
import shadow.operational.grid.SFLinearGrid;
import shadow.operational.grid.macroGrid.SFMacroGrid;
import shadow.pipeline.SFMesh;

public class SFCenterPieceCurvesMeshSchema implements SFCurvesMeshSchema{

	public SFMacroGrid generateMacroGrid(SFMesh mesh, int edges, SFLinearGrid<Integer>[][] edgeGrids) {
		return new SFCenterPieceCurvesMeshMacroGrid(mesh.getArray(),edges,edgeGrids);
	}
}
