package shadow.renderer.viewer.tests;

import java.awt.AWTException;
import java.net.URISyntaxException;

import de.roderick.weberknecht.WebSocketException;
import shadow.geometry.SFCurve;
import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.viewer.ClientComm;
import shadow.renderer.viewer.Ground;
import shadow.renderer.viewer.Handler;
import shadow.renderer.viewer.MyCharModel;
import shadow.renderer.viewer.NodeGenerator;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.renderer.viewer.cameraController;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;

public class TestAlb01 {

	/**
	 * @param args
	 * @throws AWTException
	 * @throws WebSocketException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws AWTException,
			WebSocketException, URISyntaxException {

		cameraController cameracontr = null;
		ClientComm clientComm = null;
		SFViewer.prepare();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				SFGenerateLibrary.root, SFGenerateLibrary.filename));

		SFReferenceNode rootSceneNode = new SFReferenceNode();

		int i = 0;
		int j = 0;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {

				rootSceneNode.addNode(NodeGenerator.generateModelAtPosition("Mushroom", new SFVertex3f(4.5f * i, 0f, -4.5*j), 2));

			}

		}

		Ground gr = new Ground();

		rootSceneNode.addNode(gr.generateModel());
		SFViewer viewer = SFViewer.generateFrame(rootSceneNode);
		viewer.getCamera().setUpL(0.31f);
		viewer.getCamera().setLeftL(0.31f);
		viewer.getCamera().setDistance(20f);

		try {

			cameracontr = new cameraController(viewer.getCamera());

		} catch (AWTException e) {

			System.err.println("Can't initialize the camera");
			System.exit(0);

		}
		try {

			clientComm = new ClientComm();

			
		} catch (WebSocketException e) {

			System.err.println("Can't initialize Socket communication");
			System.exit(0);

		} catch (URISyntaxException e) {

			System.exit(0);

		}

		Handler.addObserver(cameracontr);
		Handler.addObserver(clientComm);

		// Camera setup (you can also create a Camera from scratch, by assigning
		// it to the viewer with :
		// SFCamera createdCamera=new SFCamera(...);
		// viewer.getRenderer().setCamera(createdCamera);

		// NOTE: to verify camera content call
		// viewer.getCamera().extractTransform();
	}

	
	
	
}
