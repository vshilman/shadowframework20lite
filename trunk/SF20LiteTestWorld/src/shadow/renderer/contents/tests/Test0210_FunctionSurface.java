package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFExpressionSurfaceFunction;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFExpressionBuilder;
import shadow.pipeline.expression.data.SFExpressionParser;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.viewer.SFViewer;

public class Test0210_FunctionSurface extends SFAbstractTest{

	private static final String FILENAME = "test0028";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0210_FunctionSurface());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		SFExpressionBuilder builder=new SFExpressionBuilder();
		SFParameteri[] parameters={};
		SFExpressionParser.getParser().parseString("(1-u)*(1-v)*(0,1,0)*(0-1)+u*u*(1,0,0)+v*v*(0,1,0)+u*v*(0,0,1)", parameters, builder);

		SFSurfaceFunction function=new SFExpressionSurfaceFunction(builder.getBuiltExpression());
		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		
		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 3, 3,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, function);
		geometry.setFunction(SFPrimitiveBlock.POSITION, function);
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			//Note: material Data and material program are different, because the same material data can be used with different programs
			float[][] colours={{0.5f,0,0}};
			SFStructureArray array=CommonMaterial.generateMaterial(colours); 
		
			//Extract the index-th material from file libraries/library
			SFStructureReference materialReference=new SFStructureReference(array, 0);

			node.getModel().getTransformComponent().setProgram("BasicPNTransform");
			node.getModel().getMaterialComponent().setProgram("BasicMat");
			
			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().getMaterialComponent().addData(materialReference);

		//4) Show the Surface Function on an SFViewer	
			
		SFViewer.generateFrame(node,SFViewer.getWireframeController(),SFViewer.getRotationController(),SFViewer.getZoomController(),SFViewer.getWireframeController());
	}
	
	@Override
	public void buildTestData() {
		//Not doing anything
	}
}
