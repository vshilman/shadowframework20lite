package client;

import java.awt.AWTException;
import java.net.URISyntaxException;

import model.AvatarsHandler;
import model.MyAvatarHandler;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;
import viewer.SFViewer;
import commons.CommonPipeline;
import controller.ClientComm;
import controller.InputHandler;
import viewer.CameraHandler;

public class StartClient {

	public static void main(String[] args) throws AWTException,
			URISyntaxException {

		if (args.length == 0) {

			System.exit(0);

		}

		CameraHandler cameracontr;
		ClientComm clientComm;
		SFViewer.prepare();
		CommonPipeline.prepare();

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFViewerObjectsLibrary library = new SFViewerObjectsLibrary("/",
				"test0610.sf");

		SFDataCenter.setDataCenterImplementation(library);
		SFNode house1 = SFDataUtils.getResource("House01");
		SFNode house2 = SFDataUtils.getResource("House02");
		SFNode house3 = SFDataUtils.getResource("House04");
		SFNode house4 = SFDataUtils.getResource("House05");
		house1.getTransform().setPosition(new SFVertex3f(0, 0, 10));
		house2.getTransform().setPosition(new SFVertex3f(-8, 0, 1));
		house2.getTransform().setOrientation(
				SFMatrix3f.getRotationY(-0.5f * (float) Math.PI));
		house3.getTransform().setPosition(new SFVertex3f(-5, 0, 4));
		house3.getTransform().setOrientation(
				SFMatrix3f.getRotationY(-0.3f * (float) Math.PI));
		house4.getTransform().setPosition(new SFVertex3f(-8, 0, 16));
		house4.getTransform().setOrientation(
				SFMatrix3f.getRotationY(-0.15f * (float) Math.PI));

		SFReferenceNode rootSceneNode = new SFReferenceNode();
		rootSceneNode.getTransform().setPosition(new SFVertex3f(0, 0, 0));
		rootSceneNode.getTransform().setOrientation(
				SFMatrix3f.getAmpl(0.1f, 0.1f, 0.1f));

		rootSceneNode.addNode(house1);
		rootSceneNode.addNode(house2);
		rootSceneNode.addNode(house3);
		rootSceneNode.addNode(house4);

		// rootSceneNode.getTransform().setPosition(new
		// SFVertex3f(0,-0.25f,-2));

		// SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		library = new SFViewerObjectsLibrary("/", "test0014.sf");
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

		clientComm = new ClientComm(args[1]);
		cameracontr = new CameraHandler();

		SFViewer viewer = SFViewer.generateFrame(rootSceneNode,
				cameracontr.getCamera());

		InputHandler.addObserver(MyAvatarHandler.getInstance());

		// InputHandler.addObserver(clientComm);
		// InputHandler.addObserver(cameracontr);

	}

}
