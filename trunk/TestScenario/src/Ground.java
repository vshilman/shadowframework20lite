
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.SFLine;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.functions.SFRectangle2DFunction;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.structures.SFQuadsGrid;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;


public class Ground {
	SFNode geometryNode;

	public Ground() {

		this.geometryNode = generateModel();

	}

	public SFNode generateModel() {

		/*
		 * SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();
		 * SFBicurvedLoftedSurface loft = new SFBicurvedLoftedSurface(); SFLine
		 * line1 = new SFLine(3); SFLine line2 = new SFLine(2);
		 * 
		 * loft.setA(line1); loft.setB(line2); SFQuadsSurfaceGeometry
		 * quadsSurfaceGeometry = new SFQuadsSurfaceGeometry( primitive, loft,
		 * null, 2, 2);
		 * 
		 * quadsSurfaceGeometry.compile();
		 */
		SFPrimitive primitive = SFPipeline.getPrimitive("QuadPN");
		
		
		
		SFObjectModel node = new SFObjectModel();

		float[][] colours = { { 0.0f, 1.0f, 0.0f } };
		SFStructureArray array = CommonMaterial.generateMaterial(colours);

		SFStructureReference materialReference = new SFStructureReference(
				array, 0);

		SFRectangle2DFunction rectFunct = new SFRectangle2DFunction(-2f, -2f,
				40f, 40f);
		SFQuadsSurfaceGeometry quadsSurfaceGeometry = new SFQuadsSurfaceGeometry();
		//quadsSurfaceGeometry.setMainGeometryFunction(rectFunct);
	    quadsSurfaceGeometry.setPrimitive(primitive);
	    SFQuadsGrid quadsGrid=new SFQuadsGrid();
		quadsGrid.setNu(3);
		quadsGrid.setNv(2);
		quadsSurfaceGeometry.setQuadsGrid(quadsGrid);
		quadsSurfaceGeometry.setMainGeometryFunction(rectFunct);
	    quadsSurfaceGeometry.init();
		node.getModel().setRootGeometry(quadsSurfaceGeometry);

		node.getTransform().setPosition(new SFVertex3f(0f, 0f, 0f));
		SFMatrix3f rot = new SFMatrix3f();

		node.getTransform().getOrientation(rot);
		float rotateModelFactor = (float)Math.toRadians(90);
		rot = rot.Mult(SFMatrix3f.getRotationX(rotateModelFactor));
		node.getTransform().setOrientation(rot);
		node.getModel().getMaterialComponent().setProgram("BasicMat");
		return node;
	}

}
