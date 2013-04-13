package viewer;

import controller.*;
import commons.*;
import interfaces.*;
import viewer.*;
import model.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.media.opengl.GL2;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFCamera;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFRenderer;
import shadow.renderer.SFStructureReference;
import shadow.system.SFArrayElementException;
import shadow.system.SFDrawable;

public class SFViewer implements SFDrawable, ModelAvatars, ModelMyAvatar {

	private static SFNode node;
	private static SFNode myNode;
	private static SFRenderer renderer;

	private SFDrawableFrame frame;

	private static SFProgramModuleStructures[] programAssets = new SFProgramModuleStructures[10];

	private AvatarsHandler charHandler;
	private MyAvatarHandler mychar;

	public void generateSteps() {
		programAssets[0] = new SFProgramModuleStructures("BasicLSPN2");
		programAssets[0].getData().add(getSceneLight("BasicLSPN2"));
		programAssets[1] = new SFProgramModuleStructures("BasicLSPN");
		programAssets[1].getData().add(getSceneLight("BasicLSPN"));
		programAssets[2] = new SFProgramModuleStructures("BasicColor");
		programAssets[3] = new SFProgramModuleStructures("DrawNormals");
		programAssets[4] = new SFProgramModuleStructures("DrawDus");
		programAssets[5] = new SFProgramModuleStructures("DrawDvs");
		programAssets[6] = new SFProgramModuleStructures("DrawTexture");
		programAssets[7] = new SFProgramModuleStructures("DrawTexture2");
		programAssets[8] = new SFProgramModuleStructures("DrawTextCoord");
		programAssets[9] = new SFProgramModuleStructures("VectorsLight");
	}

	public SFViewer(SFCamera camera) {

		renderer = new SFRenderer();
		charHandler = AvatarsHandler.getInstance(this);
		mychar = MyAvatarHandler.getInstance(this);

			camera.extractTransform();
	
		renderer.setCamera(camera);
		generateSteps();

		renderer.setLight(programAssets[0]);

	}

	public static SFViewer generateFrame(SFNode node, SFCamera camera) {

		SFViewer viewer = new SFViewer(camera);
		viewer.setNode(node);
		SFDrawableFrame frame;
		frame = new SFDrawableFrame("Scene Viewer", 600, 600, viewer);
		frame.setVisible(true);
		return viewer;

	}

	public SFDrawableFrame getFrame() {
		return frame;
	}

	public SFCamera getCamera() {
		return renderer.getCamera();
	}

	public SFRenderer getRenderer() {
		return renderer;
	}

	public static void prepare() {

		SFGL20Pipeline.setup();

		try {
			SFProgramComponentLoader.loadComponents(
					SFViewer.class.getResourceAsStream("/structure"),
					new SFPipelineBuilder());
			SFProgramComponentLoader.loadComponents(
					SFViewer.class.getResourceAsStream("/primitive"),
					new SFPipelineBuilder());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void draw() {

		GL2 gl = SFGL2.getGL();
		float[] matrix = renderer.getCamera().extractTransform();
		gl.glClearColor(0f, 0f, 0.5f, 0);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_ALPHA_TEST);
		gl.glAlphaFunc(GL2.GL_GREATER, 0.1f);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
		
		SFNode copynode = getNode().copyNode();
		renderer.render(copynode);

	}

	@Override
	public void init() {

	}

	private static SFStructureReference getSceneLight(String lightProgramName) {
		SFStructureArray lightArray = generateLightData(lightProgramName, 0);
		SFVertex3f[] lightData = { new SFVertex3f(1, 1, 1),
				new SFVertex3f(1, 1, -1) };
		SFStructureReference lightReference = generateStructureDataReference(
				lightArray, lightData);
		return lightReference;
	}

	public static SFStructureArray generateLightData(String program,
			int lightIndex) {
		// note it supposes to have only one structure..
		SFPipelineStructure materialStructure = ((SFProgramModule) (SFPipeline
				.getProgramModule(program))).getComponents()[0].getStructures()
				.get(lightIndex).getStructure();
		SFStructureArray materialData = SFPipeline.getSfPipelineMemory()
				.generateStructureData(materialStructure);
		return materialData;
	}

	private static SFStructureReference generateStructureDataReference(
			SFStructureArray lightData, SFVertex3f[] data) {
		SFStructureReference materialReference = new SFStructureReference(
				lightData, lightData.generateElement());
		SFStructureData mat = new SFStructureData(
				materialReference.getStructure());
		for (int i = 0; i < data.length; i++) {
			((SFVertex3f) mat.getValue(i)).set(data[i]);
		}
		try {
			materialReference.setStructureData(mat);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		return materialReference;
	}

	public void notifyChgState(int id) {

		for (Iterator<SFNode> itrNode = node.iterator(); itrNode.hasNext();) {

			SFNode nodeCurrent = itrNode.next();

			if (charHandler.getNodeAt(id).equals(nodeCurrent)) {

				nodeCurrent.getTransform().setPosition(
						charHandler.getPositionAt(id));
				nodeCurrent.getTransform().setOrientation(
						charHandler.getDirectionAt(id));

			}

		}

	}

	public void notifyNewChar(int id) {

		node.addNode(charHandler.getNodeAt(id));

	}
	
	

	public void notifyRemoveChar(int id) {

		for (Iterator<SFNode> itrNode = node.iterator(); itrNode.hasNext();) {

			SFNode nodeCurrent = itrNode.next();

			if (charHandler.getNodeAt(id).equals(nodeCurrent)) {

				itrNode.remove();

			}

		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public SFNode getNode() {
		return node;
	}

	public void setNode(SFNode node) {

		this.node = node;

	}

	
		

	@Override
	public void notifyConnection() {
		myNode = mychar.getNode().copyNode();
		node.addNode(mychar.getNode());
		
	}

	@Override
	public void notifyChgState(float code) {
		
	//myNode.getTransform().setPosition(mychar.getMyPosition());
		for (Iterator<SFNode> itrNode = node.iterator(); itrNode.hasNext();) {

			SFNode nodeCurrent = itrNode.next();

			if (mychar.getNode().equals(nodeCurrent)) {

				SFVertex3f vertex = new SFVertex3f();
				nodeCurrent.getTransform().getPosition(vertex);
				System.out.println(vertex);
			}

		}

	}
	}

	


