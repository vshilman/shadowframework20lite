package viewer;

import interfaces.Observer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.math.SFMatrix3f;
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
import util.Triangle;
import common.CommonMaterial;

public class SFViewer implements SFDrawable, KeyListener {

	private static final float MIN_UNIT_VOLUME_SIZE = 200.0f;
	private static final float MAX_UNIT_VOLUME_SIZE = 4000.0f;
	private static final float DELTA_VOLUME_SIZE = 100.0f;
	private static float unitVolumeSize = 900.0f;
	public static int modello;
	private static SFNode node;
	private static SFRenderer renderer;
	private static boolean rotateModelX;
	private static boolean rotateModelY;
	private static float rotateModelFactorX;
	private static float rotateModelFactorY;
	private static boolean wireframe;
	public static int algorithm = 0;
	private SFDrawableFrame frame;
	private static SFProgramModuleStructures[] programAssets = new SFProgramModuleStructures[8];
	private static SFVertex3f[] lightData;
	private static SFStructureArray diffArray;
	private static SFStructureArray specArray;
	private static SFStructureArray ambArray;
	private static ArrayList <Observer> observers;
	private static ArrayList<Triangle> triangleMesh;
	
	static float[][] diffColor={{0.7f,0.7f,0.7f},{0.1f,0.6f,0.1f},{0.51f,0.04f,0.04f},{0.78f,0.56f,0.11f},{0.01f,0.15f,0.95f},{0.2f,0.4f,0.5f},{0.4f,0.4f,0.4f},{0.31f,0.31f,0.31f},{0.99f,0.5f,0.8f}};;
	static float[][] specColor={{0.1f,0.1f,0.1f},{0.25f,0.75f,0.25f},{0.72f,0.62f,0.62f},{0.99f,0.94f,0.8f},{0.8f,0.8f,0.8f},{0.8f,0.8f,0.8f},{0.5f,0.5f,0.5f},{0.8f,0.5f,0.7f}};;
	static float[][] ambColor={{1,1,1},{0,0.2f,0},{0.17f,0.01f,0.01f},{0.32f,0.22f,0.02f},{0.0f,0.0f,0.5f},{0.25f,0.25f,0.25f},{0.8f,0.8f,0.8f},{0.35f,0.05f,0.05f}};;
	static SFVertex3f[] light={new SFVertex3f(1,1,1), new SFVertex3f(1,1,1), new SFVertex3f(1,1,1),  new SFVertex3f(1,1,-1)};

	 
	 
	 public void generateSteps() {

		programAssets[0] = new SFProgramModuleStructures("Ambient");
		programAssets[0].getData().add(getSceneLight("Ambient"));

		programAssets[1] = new SFProgramModuleStructures("AmbientAO");
		programAssets[1].getData().add(getSceneLight("AmbientAO"));

		programAssets[2] = new SFProgramModuleStructures("Lambert");
		programAssets[2].getData().add(getSceneLight("Lambert"));

		programAssets[3] = new SFProgramModuleStructures("LambertAO");
		programAssets[3].getData().add(getSceneLight("LambertAO"));

		programAssets[4] = new SFProgramModuleStructures("SpecularLight");
		programAssets[4].getData().add(getSceneLight("SpecularLight"));

		programAssets[5] = new SFProgramModuleStructures("SpecularLightAO");
		programAssets[5].getData().add(getSceneLight("SpecularLightAO"));

		programAssets[6] = new SFProgramModuleStructures("BlinnPhong");
		programAssets[6].getData().add(getSceneLight("BlinnPhong"));

		programAssets[7] = new SFProgramModuleStructures("BlinnPhongAO");
		programAssets[7].getData().add(getSceneLight("BlinnPhongAO"));

	}

	public SFViewer(SFProgramModuleStructures light) {
		renderer = new SFRenderer();
		SFCamera camera = new SFCamera(new SFVertex3f(0, 0, 0), new SFVertex3f(0, 0, 1), new SFVertex3f(1, 0, 0), new SFVertex3f(0, 1, 0), 1, 1, 20);
		camera.extractTransform();
		renderer.setCamera(camera);
		renderer.setLight(light);
	}

	public SFViewer() {
		renderer = new SFRenderer();
		SFCamera camera = new SFCamera(new SFVertex3f(0, 0.5, 0), new SFVertex3f(0, 0, 1), new SFVertex3f(1, 0, 0), new SFVertex3f(0, 1, 0), 1, 1, 20);
		camera.extractTransform();
		renderer.setCamera(camera);
		generateSteps();
		renderer.setLight(programAssets[1]);
	}


	public static SFViewer generateFrame(SFNode node, SFFrameController... controllers) {
		SFViewer.setColor(diffColor, specColor, ambColor);
		SFViewer.setLight(light);
		SFViewer viewer = new SFViewer();
		node.getModel().getMaterialComponent().addData(new SFStructureReference(diffArray, 0));
		node.getModel().getMaterialComponent().addData(new SFStructureReference(specArray, 1));
		node.getModel().getMaterialComponent().addData(new SFStructureReference(ambArray, 2));
		node.getModel().getMaterialComponent().getData().get(0).setRefIndex(0);
		node.getModel().getMaterialComponent().getData().get(1).setRefIndex(0);
		node.getModel().getMaterialComponent().getData().get(2).setRefIndex(0);
		viewer.setNode(node);
		try {
			changeMesh(0,0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		viewer.frame = new SFDrawableFrame("Scene Viewer", 600, 600, viewer, controllers);
		viewer.frame.addKeyListener(viewer);
		viewer.frame.setVisible(true);
		return viewer;
	}
	
	
	public static SFViewer generateFrame(SFNode node,SFFrameController matCont, SFFrameController lightCont, SFFrameController rotCont,SFFrameController zoomCont) {
		SFViewer.setColor(diffColor, specColor, ambColor);
		SFViewer.setLight(light);
		SFViewer viewer = new SFViewer();
		node.getModel().getMaterialComponent().addData(new SFStructureReference(diffArray, 0));
		node.getModel().getMaterialComponent().addData(new SFStructureReference(specArray, 1));
		node.getModel().getMaterialComponent().addData(new SFStructureReference(ambArray, 2));
		node.getModel().getMaterialComponent().getData().get(0).setRefIndex(0);
		node.getModel().getMaterialComponent().getData().get(1).setRefIndex(0);
		node.getModel().getMaterialComponent().getData().get(2).setRefIndex(0);
		viewer.setNode(node);
		viewer.frame = new SFDrawableFrame("Scene Viewer", 600, 600, viewer, matCont, lightCont, rotCont, zoomCont );
		viewer.frame.addKeyListener(viewer);
		viewer.frame.setVisible(true);
		return viewer;
	}

	
	public SFDrawableFrame getFrame() {
		return frame;
	}

	public SFCamera getCamera() {
		return renderer.getCamera();
	}

	public static SFRenderer getRenderer() {
		return renderer;
	}

	public static void prepare() {
		SFGL20Pipeline.setup();
		try {
			SFProgramComponentLoader.loadComponents(new File("data/structure"),
					new SFPipelineBuilder());
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),
					new SFPipelineBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void draw() {
		frame.requestFocusInWindow();
		GL2 gl = SFGL2.getGL();
		float[] matrix = renderer.getCamera().extractTransform();
		float[] viewport = new float[4];
		gl.glGetFloatv(GL2.GL_VIEWPORT, viewport, 0);
		float width = viewport[2];
		float height = viewport[3];
		for (int i = 0; i < 4; i++) {
			matrix[i] *= unitVolumeSize / width;
			matrix[i + 4] *= unitVolumeSize / height;
		}
		gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_ALPHA_TEST);
		gl.glAlphaFunc(GL2.GL_GREATER, 0.1f);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		if (wireframe)
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		else
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		try {
			SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
			if (rotateModelX || rotateModelY)
				rotateModel();
			renderer.render(getNode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void rotateModel() {
		SFMatrix3f rot = new SFMatrix3f();
		getNode().getTransform().getOrientation(rot);
		rot = rot.Mult(SFMatrix3f.getRotationY(rotateModelFactorY));
		rot = rot.Mult(SFMatrix3f.getRotationX(rotateModelFactorX));
		getNode().getTransform().setOrientation(rot);
	}

	public void setWireframe(boolean wireframe) {
		SFViewer.wireframe = wireframe;
	}

	public void setRotateModel(boolean rotateModel, float rotateModelFactor) {
		SFViewer.rotateModelX = rotateModel;
		SFViewer.rotateModelFactorY = rotateModelFactor;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	private static SFStructureReference getSceneLight(String lightProgramName) {
		SFStructureArray lightArray = generateLightData(lightProgramName, 0);
		SFStructureReference lightReference = generateStructureDataReference(lightArray, lightData);
		return lightReference;
	}

	public static void setLight(SFVertex3f[] lightData) {
		SFViewer.lightData = lightData;
	}

	public static SFStructureArray generateLightData(String program, int lightIndex) {
		SFPipelineStructure materialStructure = ((SFProgramModule) (SFPipeline.getProgramModule(program))).getComponents()[0].getStructures().get(lightIndex).getStructure();
		SFStructureArray materialData = SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure);
		return materialData;
	}

	public static SFStructureReference generateStructureDataReference(SFStructureArray lightData, SFVertex3f[] data) {
		SFStructureReference materialReference = new SFStructureReference(lightData, lightData.generateElement());
		SFStructureData mat = new SFStructureData(materialReference.getStructure());
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

	public static void setZoom(float index) {		
		if (index == 0 ||index == 1) {
			unitVolumeSize  = 1200;
		} else {
			unitVolumeSize = 900;
		}
	}
	
	
	public static void setColor(float[][] diffColor, float[][] specColor, float[][] ambColor) {
		diffArray = CommonMaterial.generateMaterial(diffColor);
		specArray = CommonMaterial.generateMaterial(specColor);
		ambArray = CommonMaterial.generateMaterial(ambColor);
	}

	
	public static SFFrameController lightStepController = new SFFrameController() {
		
		String[] stepNames = {"Ambient", "Ambient_AO", "Lambert", "Lambert_AO", "Specular", "Specular_AO", "BlinnPhong", "BlinnPhong_AO"};

		@Override
		public String getName() {
			return "Light Model";
		}

		@Override
		public String[] getAlternatives() {
			return stepNames;
		}

		@Override
		public void select(int index) {
			renderer.setLight(programAssets[index]);

		}
	};

	public static SFFrameController rotationController = new SFFrameController() {

		String[] rotations = { "Stop Y", "Slow  Y", "Normal Y", "Fast Y", "Stop X", "Slow  X", "Normal X", "Fast X", "Reset" };

		@Override
		public String getName() {
			return "Rotation";
		}

		@Override
		public String[] getAlternatives() {
			return rotations;
		}

		@Override
		public void select(int index) {
			rotateModelX = true;
			switch (index) {
			case 0:
				rotateModelFactorY = 0;
				rotateModelY = false;
				break;
			case 1:
				rotateModelFactorY = 0.005f;
				break;
			case 2:
				rotateModelFactorY = 0.015f;
				break;
			case 3:
				rotateModelFactorY = 0.03f;
				break;
			case 4:
				rotateModelFactorX = 0;
				rotateModelX = false;
				break;
			case 5:
				rotateModelFactorX = 0.005f;
				break;
			case 6:
				rotateModelFactorX = 0.015f;
				break;
			case 7:
				rotateModelFactorX = 0.03f;
				break;
			case 8:
				node.getTransform().setOrientation(new SFMatrix3f());
				break;
			default:
				break;
			}
		}
	};

	public static SFFrameController wireframeController = new SFFrameController() {

		String[] modes = { "Line Mode (Wireframe)", "Fill Mode" };

		@Override
		public String getName() {
			return "Polygons";
		}

		@Override
		public String[] getAlternatives() {
			return modes;
		}

		@Override
		public void select(int index) {
			SFViewer.wireframe = (index == 0);
			System.err.println("Setting wireframe " + wireframe);
		}
	};

	public static SFFrameController zoomController = new SFFrameController() {

		String[] modes = { "Zoom In", "Zoom Out" };

		@Override
		public String getName() {
			return "Zoom";
		}

		@Override
		public String[] getAlternatives() {
			return modes;
		}

		@Override
		public void select(int index) {
			if (index == 0) {
				if (unitVolumeSize + DELTA_VOLUME_SIZE < MAX_UNIT_VOLUME_SIZE)
					unitVolumeSize += DELTA_VOLUME_SIZE;
			} else if (index == 1) {
				if (unitVolumeSize - DELTA_VOLUME_SIZE > MIN_UNIT_VOLUME_SIZE)
					unitVolumeSize -= DELTA_VOLUME_SIZE;
			}
		}
	};
	
	
	public static SFFrameController algController = new SFFrameController(){
		
		String[] names = {"Ray-Tracer", "D2"};
		
		@Override
		public String getName() {
			return "Algorithm";
		}

		@Override
		public String[] getAlternatives() {
			return names;
		}

		@Override
		public void select(int index) {
			if (index == 0){
				algorithm = 0;
				try {
					changeMesh(modello, 0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(index == 1) {
				algorithm = 1;
				try {
					changeMesh(modello, 1);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	};
	
	
	public static SFFrameController loadModelController = new SFFrameController() {
		
		String[] names = {"Mushroom", "Lateral Tube", "Strange Glass"};
		
		@Override
		public String getName() {
			return "Model";
		}

		@Override
		public String[] getAlternatives() {	
			return names;
		}

		@Override
		public void select(int index) {
			try {	
				modello = index;
				changeMesh(index, algorithm);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	};
	
	
	public static SFFrameController materialController = new SFFrameController() {
		
		String[] names = { "White", "Green", "Red", "Gold","Blue", "Grey","Silver","Violet" };
		
		@Override
		public String getName() {
			return "Material";
		}

		@Override
		public String[] getAlternatives() {
			return names;
		}

		@Override
		public void select(int index) {
			
			if(index == 0){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(0);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(0);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(0);
			
			}else if(index == 1){
			
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(1);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(1);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(1);

			}else if(index == 2){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(2);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(2);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(2);
		
			}else if(index == 3){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(3);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(3);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(3);			
				
			}else if(index == 4){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(4);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(4);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(4);
	
			}else if(index == 5){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(5);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(5);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(5);
				
			}else if(index == 6){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(6);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(6);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(6);
				
			}else if(index == 7){
				
				node.getModel().getMaterialComponent().getData().get(0).setRefIndex(7);
				node.getModel().getMaterialComponent().getData().get(1).setRefIndex(7);
				node.getModel().getMaterialComponent().getData().get(2).setRefIndex(7);
			
			}		
		}
		
	};
	
	
	
	public static SFFrameController getAlgController(){
		return algController;
	}
	

	public static SFFrameController getLoadModelController(){
		return loadModelController;
	}
	
	
	public static SFFrameController getMaterialController() {
		return materialController;
	}

	
	public static SFFrameController getLightStepController() {
		return lightStepController;
	}

	public static SFFrameController getRotationController() {
		return rotationController;
	}

	public static SFFrameController getWireframeController() {
		return wireframeController;
	}

	public static SFFrameController getZoomController() {
		return zoomController;
	}

	public SFNode getNode() {
		return node;
	}

	public void setNode(SFNode node) {
		SFViewer.node = node;
	}

	public void updateCamera() {
		float leftL = getFrame().getGLCanvas().getWidth() * 0.00002f;
		float upL = getFrame().getGLCanvas().getHeight() * 0.00002f;
		getCamera().setupDimensions(leftL, upL);
	}

	public static void setMesh(ArrayList<Triangle> triangleMesh){
		SFViewer.triangleMesh = triangleMesh;
	}
	
	public void setFrame(SFDrawableFrame frame) {
		this.frame = frame;
	}

	

	public static void changeMesh(int index, int alg) throws IOException {
		notifyObservers(index, algorithm);
	}
	

	public static void addObs(Observer observer) {	
		observers = new ArrayList<Observer>();
		SFViewer.observers.add(observer);
	}
	
	
	public static void notifyObservers(int index, int alg) {
	     for (Observer ob : SFViewer.observers) { 
	    	 try {
				ob.update(index,alg);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	     }
	}

	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
			int key = arg0.getKeyCode();
			
			switch(key){
				
				case KeyEvent.VK_PLUS: if (unitVolumeSize + DELTA_VOLUME_SIZE < MAX_UNIT_VOLUME_SIZE)
						unitVolumeSize += DELTA_VOLUME_SIZE;
			    break;
			
				case KeyEvent.VK_MINUS:if (unitVolumeSize - DELTA_VOLUME_SIZE > MIN_UNIT_VOLUME_SIZE)
						unitVolumeSize -= DELTA_VOLUME_SIZE;
				 break;
				 
			}
	}

	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
}
