package shadow.geometry.geometries;

import shadow.geometry.SFCurvesMeshSchema;
import shadow.operational.grid.SFLinearGrid;
import shadow.operational.grid.macroGrid.SFMacroGrid;
import shadow.operational.meshes.SFCurvesSurface;
import shadow.operational.meshes.SFMeshPatch;
import shadow.pipeline.SFPipeline;

public class SFCurvesMeshGeometry extends SFMeshGeometry{

	private SFCurvesGeometry curvesGeometry;
	private SFCurvesMeshSchema schema;
	private SFMacroGrid[] allGrids;
	
	public SFCurvesMeshGeometry() {
		super();
	}

	public void setCurvesGeometry(SFCurvesGeometry curvesGeometry) {
		this.curvesGeometry = curvesGeometry;
		if(getArray()==null)
			setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive));
		curvesGeometry.setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArrayView(getArray(), curvesGeometry.getPrimitive()));
	}
	
	public void setSchema(SFCurvesMeshSchema schema) {
		this.schema = schema;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void compile() {
		super.compile();
		
		SFCurvesSurface surface=curvesGeometry.getSurfaceMesh();
		
		SFLinearGrid<Integer>[][] edgesGrids=curvesGeometry.getAllGrids();
		
		//setFirstElement(getArray().getElementsCount());

		allGrids=new SFMacroGrid[surface.getPatches().size()];
		for (int i = 0; i < surface.getPatches().size(); i++) {
			
			SFMeshPatch patch=surface.getPatches().get(i);

			int edges=patch.getEffectiveIndexSize();
			
			SFLinearGrid<Integer>[][] edgeLine=(SFLinearGrid<Integer>[][])new SFLinearGrid<?>[edges][];
			for (int j = 0; j < edges; j++) {
				int edgeIndex=patch.get(j);
				edgeLine[j]=edgesGrids[edgeIndex];
			}
			
			allGrids[i]=schema.generateMacroGrid(getMesh(),edges, edgeLine);
		}

		//setLastElement(getArray().getElementsCount());
		
		//getMesh().evaluateRanges();
		
		//applyFillData();
		
	}
	
	
	@Override
	public void update() {
		super.update();
		
		curvesGeometry.update();
		
		applyFillData();
	}

	public void applyFillData() {
		for (int i = 0; i < allGrids.length; i++) {
			allGrids[i].updateInternalCircles();
		}
		
		//SFGridEngine.correctValues(getMesh());
	}

	
}
