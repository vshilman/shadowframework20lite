import java.awt.AWTException;
import java.net.URISyntaxException;

import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFObjectsLibrary;

public class TestAlb01 {

	public static void main(String[] args) throws AWTException,
			URISyntaxException {

		if (args.length == 0){
			
			System.exit(0);
			
		}
		CameraController cameracontr;
		ClientComm clientComm;
		SFViewer.prepare();
		CommonPipeline.prepare();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFViewerObjectsLibrary library = new SFViewerObjectsLibrary(
				"libraries", "library0602.sf");
		SFDataCenter.setDataCenterImplementation(library);

		SFReferenceNode rootSceneNode = new SFReferenceNode();

		rootSceneNode.addNode(placeModel("SLAvatarCenterShop01", 0.0f, -0.2f,
				10.0f, 1, 0, 0, 0));
		rootSceneNode.addNode(placeModel("SLAvatarCenterShop02", -10f, -0.7f,
				0f, 1, 0, 0, -1.07f));
		rootSceneNode.addNode(placeModel("SLAvatarCenterShop02", 10f, -0.7f,
				0f, 1, 0, 0, +1.07f));
		rootSceneNode.addNode(placeModel("SLAvatarCenterShop02", 0f, -0.7f,
				-10f, 1, 0, 0, +1.07f));

		//compileAndLoadXmlFile("test0014");
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		library = new SFViewerObjectsLibrary("libraries", "test0014.sf");
		SFDataCenter.setDataCenterImplementation(library);
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				SFObjectModelData pebblesGround = new SFObjectModelData();
				pebblesGround.setGeometry("FullScreenRectangle");
				pebblesGround.getMaterialComponent().addTexture(
						"PerlinTexture2");
				pebblesGround.getTransformComponent().setProgram(
						"BasicPNTx0Transform");
				pebblesGround.getMaterialComponent().setProgram("TexturedMat");
				SFNode ground = pebblesGround.getResource();
				ground.getTransform().setPosition(
						new SFVertex3f((float) (-50 + 10 * x) - 1f, -1f,
								(float) (-50 + 10 * y)));

				float rotateModelFactor = (float) (Math.PI / 2);
				SFMatrix3f rot = new SFMatrix3f();
				rot = rot.Mult(SFMatrix3f.getRotationX(rotateModelFactor));
				ground.getTransform().setOrientation(rot);
				rootSceneNode.addNode(pebblesGround.getResource());
			}
		}

		cameracontr = new CameraController();
		clientComm = new ClientComm(args[0]);
		SFViewer viewer = SFViewer.generateFrame(rootSceneNode,
				cameracontr.getCamera());
		Handler.addObserver(clientComm);
		Handler.addObserver(cameracontr);

	}

	@SuppressWarnings("unchecked")
	public static SFReferenceNode placeModel(String modelName, float x,
			float y, float z) {

		SFDataset nodeDataset = SFDataCenter.getDataCenter()
				.getAlreadyAvailableDataset(modelName);
		SFNode node = ((SFDataAsset<SFNode>) (nodeDataset)).getResource();

		SFReferenceNode referenceNode = new SFReferenceNode();
		referenceNode.getTransform().setPosition(new SFVertex3f(x, y, z));
		referenceNode.addNode(node.copyNode());

		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float rx, float ry, float rz) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFEulerAngles3f angles = new SFEulerAngles3f(ry, rx, rz);
		SFMatrix3f temp = new SFMatrix3f();
		angles.getMatrix(temp);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float scale) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFMatrix3f temp = SFMatrix3f.getAmpl(scale, scale, scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float scale, float rx, float ry, float rz) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFEulerAngles3f angles = new SFEulerAngles3f(ry, rx, rz);
		SFMatrix3f temp = new SFMatrix3f();
		angles.getMatrix(temp);
		temp.mult(scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	public static void compileAndLoadXmlFile(String xmlFileName) {

		SFViewerObjectsLibrary library = new SFViewerObjectsLibrary();

		SFObjectsLibraryDecoder decoder = new SFObjectsLibraryDecoder(
				library.getLibrary());

		SFXMLDataInterpreter interpreter = new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation("libraries" + "/" + xmlFileName
				+ ".xml");
		SFDataCenter.setDataCenterImplementation(library);
	    store(library.getLibrary());
	}
	
	
	public static void store(SFObjectsLibrary library) {

		String filename = "test0014";

		SFDataUtility.saveDataset("libraries", filename + ".sf", library);

		//SFDataUtility.saveDatasetJs(root, filename + ".js.sf", library);

	}

}
