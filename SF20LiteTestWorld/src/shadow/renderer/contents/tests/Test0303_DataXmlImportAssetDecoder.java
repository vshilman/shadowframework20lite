package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFAssetDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test0303_DataXmlImportAssetDecoder {

	public static void main(String argv[]) {
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//... and a complete ShadowFramework Pipeline must be prepared.
		CommonPipeline.prepare();
		
		SFAssetDecoder decoder=new SFAssetDecoder();
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("testsData/test0001.xml");
		
		SFDataAsset<?> asset=decoder.getDecodedAsset();
		
		SFSurfaceFunction resource=(SFSurfaceFunction)asset.getResource();

		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PN");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, resource);
		geometry.setFunction(SFPrimitiveBlock.POSITION, resource);
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
			node.getModel().getMaterialComponent().addData(materialReference);

		//4) Show the Surface Function on an SFViewer	
			
		SFViewer.generateFrame(node);
	}
	
}
