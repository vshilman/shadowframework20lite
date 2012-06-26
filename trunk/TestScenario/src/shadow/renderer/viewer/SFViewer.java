package shadow.renderer.viewer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.geometry.SFGeometry;
import shadow.material.SFDataLightStep;
import shadow.material.SFLightStep;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFCamera;
import shadow.renderer.SFLodFilter;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFRenderer;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.renderer.SFStructureReference;
import shadow.system.SFArrayElementException;
import shadow.system.SFDrawable;
public class SFViewer implements SFDrawable, ModelNotif{

	private static final float MIN_UNIT_VOLUME_SIZE=100.0f;
	private static final float MAX_UNIT_VOLUME_SIZE=1000.0f;
	private static final float DELTA_VOLUME_SIZE=100.0f;
	private static float unitVolumeSize=600.0f;

	private SFNode node;
	private static SFRenderer renderer;
	private static SFRenderingAlgorithm algorithm;
	private static boolean rotateModel;
	private static float rotateModelFactor;
	private static boolean wireframe;
    private MyCharModel mychar;
    private CharHandler charHandler;
	private static SFProgramStructureReference reference; 
	NodeGenerator nodeGen;
	
	
	public SFViewer(){
		setupRenderer();
	
		mychar = MyCharModel.getInstance(this);
		charHandler = CharHandler.getInstance(this);
		
		ArrayList<SFLightStep> steps=new ArrayList<SFLightStep>();
		steps.add(voidStep);
		SFLodFilter voidFilter=new SFLodFilter() {
			@Override
			public boolean acceptNode(SFNode node) {
				return true;
			}
			@Override
			public int acceptGeometry(SFGeometry node) {
				return 0;
			}
		}; 
				
		ArrayList<SFLodFilter> filters=new ArrayList<SFLodFilter>();
		filters.add(voidFilter);
		algorithm=new SFRenderingAlgorithm(steps,filters);
		renderer.setAlgorithm(algorithm);
		
		
		
	}

	public SFLightStep setupRenderer()  {
		renderer=new SFRenderer();
		reference=getSceneLight();
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0), new SFVertex3f(0,0,1), 
				new SFVertex3f(1,0,0), new SFVertex3f(0,1,0), 1, 1, 20);
		camera.setChanged(true);
		
		camera.extractTransform();
		
		renderer.setCamera(camera);
     
		return voidStep;
	}
	
	public static SFViewer generateFrame(SFNode node,SFFrameController... controllers) {
		SFViewer viewer=new SFViewer();
		viewer.node=node;
		SFDrawableFrame frame;
	
		
		frame = new SFDrawableFrame("Scene Viewer", 600, 600, viewer, controllers);
		
	
		
		frame.setVisible(true);
		return viewer;
	}
	
	public SFCamera getCamera(){
		return renderer.getCamera();
	}
	
	public SFRenderer getRenderer() {
		return renderer;
	}

	public static void prepare(){
		
		//setup the Rendering pipeline
		SFGL20Pipeline.setup();
		try {
			//load pipeline program components
			SFProgramComponentLoader.loadComponents(new File("data/structure"),new SFPipelineBuilder());
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),new SFPipelineBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void draw() {
		
		GL2 gl=SFGL2.getGL(); 
		
		float[] matrix=renderer.getCamera().extractTransform();
		
		float[] viewport=new float[4];
		gl.glGetFloatv(GL2.GL_VIEWPORT, viewport, 0);
		float width=viewport[2];
		float height=viewport[3];
		for (int i = 0; i < 4; i++) {
			matrix[i]*=unitVolumeSize/width;
			matrix[i+4]*=unitVolumeSize/height;
		}
		
		gl.glClearColor(0f,0f,0.5f,1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		if(wireframe)
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		else
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		
		try {
			
			SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
		
			if(rotateModel)
				rotateModel();
			
			renderer.render(node);
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
			
	}

	private void rotateModel() {
		SFMatrix3f rot=new SFMatrix3f();
		node.getTransform().getOrientation(rot);
		rot=rot.Mult(SFMatrix3f.getRotationY(rotateModelFactor));
		node.getTransform().setOrientation(rot);
	}
	
	
	
	public void setWireframe(boolean wireframe) {
		SFViewer.wireframe = wireframe;
	}

	public void setRotateModel(boolean rotateModel,float rotateModelFactor){
		SFViewer.rotateModel=rotateModel;
		SFViewer.rotateModelFactor=rotateModelFactor;
	}
	
	@Override
	public void init() {
		
	}

	private static SFProgramStructureReference getSceneLight(){
		String lightProgramName="BasicLSPN";
		SFStructureArray lightArray=generateLightData(lightProgramName, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)};
		SFStructureReference lightReference=generateStructureDataReference( lightArray, lightData);
		return new SFProgramStructureReference(lightProgramName, lightReference);
	}
	
	public static SFStructureArray generateLightData(String programComponet,int lightIndex){
		//note it supposes to have only one structure..
		SFPipelineStructure materialStructure=((SFProgramComponent)(SFPipeline.getModule(programComponet))).getStructures().get(lightIndex).getStructure();
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure); 
		return materialData;
	}
	
	private static SFStructureReference generateStructureDataReference(SFStructureArray lightData,SFVertex3f[] data){
		SFStructureReference materialReference=new SFStructureReference(lightData,lightData.generateElement()); 
		SFStructureData mat=new SFStructureData(materialReference.getStructure());
		for (int i = 0; i < data.length; i++) {
			((SFVertex3f)mat.getValue(i)).set(data[i]);
		}
		try {
			materialReference.setStructureData(mat);
		} catch (SFArrayElementException e) {
			e.printStackTrace();
		}
		return materialReference;
	}
	

	private static SFLightStep voidStep=new SFLightStep() {
		@Override
		public void prepareStep() {
			renderer.addLights(reference);
		}
		@Override
		public String getProgramName() {
			return "BasicLSPN2";
		}
		
		@Override
		public void closeStep() {
			renderer.clearLights();
		}
	};
	
	private static List<SFLightStep> steps=new ArrayList<SFLightStep>();
	private static String[] stepNames={
		"Lambert Light",
		"Basic Colour",
		"Draw Normal",
		"Draw Du",
		"Draw Dv",
		"Draw Texture",
		"Draw TexCoord",
	};
	static {
		steps.add(voidStep);
		steps.add(new SFDataLightStep("BasicColor"));
		steps.add(new SFDataLightStep("DrawNormals"));
		steps.add(new SFDataLightStep("DrawDus"));
		steps.add(new SFDataLightStep("DrawDvs"));
		steps.add(new SFDataLightStep("DrawTexture"));
		steps.add(new SFDataLightStep("DrawTextCoord"));
	}
	
	public static SFFrameController lightStepController=new SFFrameController() {
		
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
			algorithm.getSteps().clear();
			algorithm.getSteps().add(steps.get(index));
		}
	};
	

	public static SFFrameController rotationController=new SFFrameController() {
		
		String[] rotations={
			"Stop",
			"Slow",
			"Normal",
			"Fast"
		};
		
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
			rotateModel=true;
			switch (index) {
				case 0: rotateModelFactor=0; 
				rotateModel=false; break;
				case 1: rotateModelFactor=0.005f; break;
				case 2: rotateModelFactor=0.015f; break;
				case 3: rotateModelFactor=0.03f; break;
			default:
				break;
			}
		}
	};
	
	public static SFFrameController wireframeController=new SFFrameController() {
		
		String[] modes={
			"Line Mode (Wireframe)",
			"Fill Mode"
		};
		
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
			SFViewer.wireframe=(index==0);
			System.err.println("Setting wireframe "+wireframe);
		}
	};
	
	public static SFFrameController zoomController=new SFFrameController() {
		
		String[] modes={
			"Zoom In",
			"Zoom Out"
		};
		
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
			if(index==0){
				if(unitVolumeSize+DELTA_VOLUME_SIZE<MAX_UNIT_VOLUME_SIZE)
					unitVolumeSize+=DELTA_VOLUME_SIZE;
			}else if(index==1){
				if(unitVolumeSize-DELTA_VOLUME_SIZE>MIN_UNIT_VOLUME_SIZE)
					unitVolumeSize-=DELTA_VOLUME_SIZE;
			}
		}
	};

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
	

	ArrayList<NodeGenerator> arrayOfNodes = new ArrayList<NodeGenerator>();
	public void notifyChgPos(int id){
		
		
		
		for (NodeGenerator nodeCurrent : arrayOfNodes ){
			
			if (nodeCurrent.getId() == id){
					nodeCurrent.setPosition(charHandler.getPositionAt(id));
			}
		}
		}

	@Override
	public void myCharNotif() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyNewChar(int id) {
		
		nodeGen = new NodeGenerator(id, charHandler.getPositionAt(id), 0);
	    node.addNode(nodeGen.getGeometryNode() );
	    arrayOfNodes.add(nodeGen);
	   
	}
		
	public void notifyRemoveChar(int id) {

	NodeGenerator nodeGenFound = null;

		for(Iterator<NodeGenerator> itr = arrayOfNodes.iterator(); itr.hasNext();){
			
			NodeGenerator currentNodeGen = itr.next();
			if(currentNodeGen.getId() == id){
				
				
				nodeGenFound = currentNodeGen;
				
			}
				
			}
			
		
		for(Iterator<SFNode> itrNode = node.iterator(); itrNode.hasNext();){
			
			
			SFNode nodeCurrent = itrNode.next();
		

		if(nodeGenFound.getGeometryNode().equals(nodeCurrent)){

			itrNode.remove();
			
		}
			
		}

		
	}
		
	
	
	}
	

