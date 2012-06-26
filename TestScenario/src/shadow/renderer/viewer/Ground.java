package shadow.renderer.viewer;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.SFLine;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.functions.SFRectangle2DFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;

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
		SFPrimitive primitive = new SFPrimitive();
		primitive.addPrimitiveElement(PrimitiveBlock.NORMAL,
				(SFProgramComponent) (SFPipeline.getModule("Triangle2")));
		primitive.addPrimitiveElement(PrimitiveBlock.POSITION,
				(SFProgramComponent) (SFPipeline.getModule("Triangle2")));
		primitive.setAdaptingTessellator((SFProgramComponent) (SFPipeline
				.getModule("BasicTess")));

		SFObjectModel node = new SFObjectModel();

		float[][] colours = { { 0.0f, 1.0f, 0.0f } };
		SFStructureArray array = CommonMaterial.generateMaterial(colours);

		SFStructureReference materialReference = new SFStructureReference(
				array, 0);

		SFRectangle2DFunction rectFunct = new SFRectangle2DFunction(-2f, -2f,
				40f, 40f);
		SFQuadsSurfaceGeometry quadsSurfaceGeometry = new SFQuadsSurfaceGeometry(
				primitive, rectFunct, null, 2, 2);
		quadsSurfaceGeometry.init();
		node.getModel().setRootGeometry(quadsSurfaceGeometry);
		node.getModel().addMaterial(
				new SFProgramStructureReference("BasicMat", materialReference));

		node.getTransform().setPosition(new SFVertex3f(0f, 0f, 0f));
		SFMatrix3f rot = new SFMatrix3f();

		node.getTransform().getOrientation(rot);
		float rotateModelFactor = (float)Math.toRadians(90);
		rot = rot.Mult(SFMatrix3f.getRotationX(rotateModelFactor));
		node.getTransform().setOrientation(rot);
		return node;
	}

}
