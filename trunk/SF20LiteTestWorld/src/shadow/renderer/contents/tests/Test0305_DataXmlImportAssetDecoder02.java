package shadow.renderer.contents.tests;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.image.SFBitmapTexture;
import shadow.image.SFTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFAssetDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;

public class Test0305_DataXmlImportAssetDecoder02 {

	private static final String root = "testsData";
	//private static String filename="test0003";
	
	public static void main(String argv[]) {
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//... and a complete ShadowFramework Pipeline must be prepared.
		CommonPipeline.prepare();
		
		SFAssetDecoder decoder=new SFAssetDecoder();
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("testsDataInput/test0003Asset.xml");
		
		SFDataAsset<?> asset=(SFDataAsset<?>)decoder.getDecodedAsset();
		
		SFBitmapTexture resource=(SFBitmapTexture)asset.getResource();
		
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedGeometryFunction = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");

		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		SFSurfaceFunction geometryResource=retrievedGeometryFunction.getResource();

		SFPrimitive primitive=SFPipeline.getPrimitive("Triangle2PNTxO");
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry(primitive.getConstructionPrimitive(), 8, 8,false,false);
		SFParametrizedGeometry geometry=new SFParametrizedGeometry(primitive,gridGeometry);
		geometry.setFunction(SFPrimitiveBlock.NORMAL, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.POSITION, geometryResource);
		geometry.setFunction(SFPrimitiveBlock.TXO, new SFSimpleTexCoordGeometryuv(2,2));
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			node.getModel().getMaterialComponent().getTextures().add(0, new SFTexture(resource,0));

			node.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
			node.getModel().getMaterialComponent().setProgram("TexturedMat");


		SFViewer.generateFrame(node,SFViewer.getZoomController(),SFViewer.getRotationController());
	}
	
}
