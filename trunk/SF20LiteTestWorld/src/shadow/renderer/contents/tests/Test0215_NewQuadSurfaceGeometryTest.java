package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.structures.SFQuadsGrid;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.viewer.SFViewer;

public class Test0215_NewQuadSurfaceGeometryTest extends SFAbstractTest{

	private static final String FILENAME="test0215";
	
	public static void main(String[] args) {
		execute(new Test0215_NewQuadSurfaceGeometryTest());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	

	@Override
	public void viewTestData() {
		
		SFBicurvedLoftedSurfaceData retrievedData = loadDataset();
		SFSurfaceFunction resource=retrievedData.getResource();

		float[][] colours={{1.0f,0,0},{1.0f,0.6f,0},{0.0f,0,1.0f},{0.0f,1.0f,0},{0.0f,1.0f,1.0f},{1.0f,1.0f,0.0f},{1.0f,0.4f,0.4f},{0.4f,0.4f,1.0f}};
		SFStructureArray array=CommonMaterial.generateMaterial(colours);
		
		SFObjectModel[] nodes = {
				generateModel(resource, SFPipeline.getPrimitive("Triangle3PN"), array, -1,0,0,0),
				generateModel(resource, SFPipeline.getPrimitive("Triangle2PN"), array, 0,0,0.02f,1),
				generateModel(resource, SFPipeline.getPrimitive("QuadPN"), array, 1,0,0.04f,2),
				generateModel(resource, SFPipeline.getPrimitive("Quad2PN"), array, 0.0f,0,0.56f,3),
				generateModel(resource, SFPipeline.getPrimitive("TriangleP2N1"), array, 0,0,1,4),
				generateModel(resource, SFPipeline.getPrimitive("TriangleP3N2"), array, 0,0,-1,5),
				generateModel(resource, SFPipeline.getPrimitive("TrianglePN"), array, -1,0,-1.02f,6),
				generateModel(resource, SFPipeline.getPrimitive("QuadP2N1"), array, 1,0,1.02f,7)
		};
		
		SFReferenceNode referenceNode=new SFReferenceNode();
		referenceNode.getTransform().setOrientation(SFMatrix3f.getAmpl(1.0f, 1.0f, 0.7f));
		for (int i = 0; i < nodes.length; i++) {
			referenceNode.addNode(nodes[i]);
		}
			
		//4) Show the Surface Function on an SFViewer	
			
		SFViewer.generateFrame(referenceNode,SFViewer.getWireframeController(),SFViewer.getRotationController(),SFViewer.getLightStepController());
	}

	public SFObjectModel generateModel(SFSurfaceFunction resource, SFPrimitive primitive,
			SFStructureArray array, float x, float y, float z,int color) {
		SFQuadsSurfaceGeometry quadSurfaceGeometry=new SFQuadsSurfaceGeometry();
		SFQuadsGrid quadsGrid=new SFQuadsGrid();
		quadsGrid.setNu(3);
		quadsGrid.setNv(2);
		quadSurfaceGeometry.setQuadsGrid(quadsGrid);
		quadSurfaceGeometry.setPrimitive(primitive);
		quadSurfaceGeometry.setMainGeometryFunction(resource);
		quadSurfaceGeometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(quadSurfaceGeometry);
			node.getTransform().setPosition(new SFVertex3f(x,y,z));
			node.getModel().getTransformComponent().setProgram("BasicPNTransform");
			node.getModel().getMaterialComponent().setProgram("BasicMat");
			node.getModel().getMaterialComponent().addData(new SFStructureReference(array, color));
		return node;
	}

	@Override
	public void buildTestData() {
		SFBicurvedLoftedSurfaceData function = new SFBicurvedLoftedSurfaceData();

		SFUniformBezier33fData spline1Data=new SFUniformBezier33fData();
		spline1Data.addBezier33f(-0.7f, -0.5f, 0, -0.5f, 0, 0, 0, 0.6f, 0);
		spline1Data.addBezier33f(0, 0.6f, 0, 0.5f, 0, 0, 0.5f, -0.5f, 0);

		SFUniformBezier33fData spline2Data=new SFUniformBezier33fData();
		spline2Data.addBezier33f(0.0f, -0.5f, 0, -0.5f, -0.2f, 0, 0, 0.5f, 0);
		spline2Data.addBezier33f( 0, 0.5f, 0, 0.5f, -0.2f, 0, 0.4f, -0.5f, 0);

		function.getFirstCurve().setDataset(spline1Data);
		function.getSecondCurve().setDataset(spline2Data);
		
		store(function);
	}
	
}
