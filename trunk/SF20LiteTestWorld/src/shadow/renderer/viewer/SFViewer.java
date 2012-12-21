package shadow.renderer.viewer;

import java.io.File;
import java.io.IOException;

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

public class SFViewer implements SFDrawable{

	private static final float MIN_UNIT_VOLUME_SIZE=200.0f;
	private static final float MAX_UNIT_VOLUME_SIZE=4000.0f;
	private static final float DELTA_VOLUME_SIZE=100.0f;
	private static float unitVolumeSize=600.0f;
	
	private static SFNode node;
	private static SFRenderer renderer;
	private static boolean rotateModelX;
	private static boolean rotateModelY;
	private static float rotateModelFactorX;
	private static float rotateModelFactorY;
	private static boolean wireframe;
	
	private SFDrawableFrame frame;
	
	private static SFProgramModuleStructures[] programAssets=new SFProgramModuleStructures[10];

	public void generateSteps() {
		programAssets[0]=new SFProgramModuleStructures("BasicLSPN2");
		programAssets[0].getData().add(getSceneLight("BasicLSPN2"));
		programAssets[1]=new SFProgramModuleStructures("BasicLSPN");
		programAssets[1].getData().add(getSceneLight("BasicLSPN"));
		programAssets[2]=new SFProgramModuleStructures("BasicColor");
		programAssets[3]=new SFProgramModuleStructures("DrawNormals");
		programAssets[4]=new SFProgramModuleStructures("DrawDus");
		programAssets[5]=new SFProgramModuleStructures("DrawDvs");
		programAssets[6]=new SFProgramModuleStructures("DrawTexture");
		programAssets[7]=new SFProgramModuleStructures("DrawTexture2");
		programAssets[8]=new SFProgramModuleStructures("DrawTextCoord");
		programAssets[9]=new SFProgramModuleStructures("VectorsLight");
	}
	

	public SFViewer(){
		renderer=new SFRenderer();
		//reference=getSceneLight();
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0), new SFVertex3f(0,0,1), 
				new SFVertex3f(1,0,0), new SFVertex3f(0,1,0), 1, 1, 20);
		
		camera.extractTransform();
		renderer.setCamera(camera);
		
		generateSteps();
		renderer.setLight(programAssets[0]);
	}
	
	
	public static SFViewer generateFrame(SFNode node,SFFrameController... controllers){
		SFViewer viewer=new SFViewer();
		viewer.setNode(node);
		viewer.frame=new SFDrawableFrame("Scene Viewer", 600, 600, viewer, controllers);
		viewer.frame.setVisible(true);
		return viewer;
	}
	
	
	
	public SFDrawableFrame getFrame() {
		return frame;
	}


	public SFCamera getCamera(){
		return renderer.getCamera();
	}
	
	public static SFRenderer getRenderer() {
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

//		//KEEP THIS COMMENTED LINES FOR TIME-ANALYSIS!
//		List<Long> times=new ArrayList<Long>();
//		times.add(System.nanoTime());
		
		float[] matrix=renderer.getCamera().extractTransform();
		
		//if(!renderer.getCamera().isPerspective()){	
			float[] viewport=new float[4];
			gl.glGetFloatv(GL2.GL_VIEWPORT, viewport, 0);
			float width=viewport[2];
			float height=viewport[3];
			for (int i = 0; i < 4; i++) {
				matrix[i]*=unitVolumeSize/width;
				matrix[i+4]*=unitVolumeSize/height;
			}
		//}
		
		gl.glClearColor(0.4f,0.4f,0.4f,1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_ALPHA_TEST);
		gl.glAlphaFunc(GL2.GL_GREATER, 0.1f);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		if(wireframe)
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		else
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		
		try {
			
			SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
		
			if(rotateModelX || rotateModelY)
				rotateModel();
			
			renderer.render(getNode());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		//KEEP THIS COMMENTED LINES FOR TIME-ANALYSIS!
//		times.add(System.nanoTime());//1
//		for (int i = 1; i < times.size(); i++) {
//			long t1=times.get(i-1);
//			long t2=times.get(i);
//			System.out.println("\t\t T["+i+"]="+((t2-t1)*0.001*0.001)+"ms");
//		}
	}

	private void rotateModel() {
		SFMatrix3f rot=new SFMatrix3f();
		getNode().getTransform().getOrientation(rot);
		rot=rot.Mult(SFMatrix3f.getRotationY(rotateModelFactorY));
		rot=rot.Mult(SFMatrix3f.getRotationX(rotateModelFactorX));
		getNode().getTransform().setOrientation(rot);
	}
	
	
	public void setWireframe(boolean wireframe) {
		SFViewer.wireframe = wireframe;
	}

	public void setRotateModel(boolean rotateModel,float rotateModelFactor){
		SFViewer.rotateModelX=rotateModel;
		SFViewer.rotateModelFactorY=rotateModelFactor;
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	private static SFStructureReference getSceneLight(String lightProgramName){
		SFStructureArray lightArray=generateLightData(lightProgramName, 0);
		SFVertex3f[] lightData={new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)};
		SFStructureReference lightReference=generateStructureDataReference( lightArray, lightData);
		return lightReference;
	}
	
	public static SFStructureArray generateLightData(String program,int lightIndex){
		//note it supposes to have only one structure..
		SFPipelineStructure materialStructure=((SFProgramModule)(SFPipeline.getProgramModule(program))).getComponents()[0]
                    .getStructures().get(lightIndex).getStructure();
		SFStructureArray materialData=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructure); 
		return materialData;
	}
	
	public static SFStructureReference generateStructureDataReference(SFStructureArray lightData,SFVertex3f[] data){
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
	

	private static String[] stepNames={
		"Lambert Light (More Light)",
		"Lambert Light",
		"Basic Colour",
		"Draw Normal",
		"Draw Du",
		"Draw Dv",
		"Draw Texture",
		"Draw Texture2",
		"Draw TexCoord",
		"VectorsLight"
	};
	
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
			renderer.setLight(programAssets[index]);
		}
	};
	

	public static SFFrameController rotationController=new SFFrameController() {
		
		String[] rotations={
			"Stop Y",
			"Slow  Y",
			"Normal Y",
			"Fast Y",
			"Stop X",
			"Slow  X",
			"Normal X",
			"Fast X",
			"Reset"
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
			rotateModelX=true;
			switch (index) {
				case 0: rotateModelFactorY=0; 
				rotateModelY=false; break;
				case 1: rotateModelFactorY=0.005f; break;
				case 2: rotateModelFactorY=0.015f; break;
				case 3: rotateModelFactorY=0.03f; break;
				case 4: rotateModelFactorX=0; 
				rotateModelX=false; break;
				case 5: rotateModelFactorX=0.005f; break;
				case 6: rotateModelFactorX=0.015f; break;
				case 7: rotateModelFactorX=0.03f; break;
				case 8: node.getTransform().setOrientation(new SFMatrix3f());break;
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

	public SFNode getNode() {
		return node;
	}

	public void setNode(SFNode node) {
		SFViewer.node = node;
	}


	public void updateCamera() {
		float leftL=getFrame().getGLCanvas().getWidth()*0.00002f;
		float upL=getFrame().getGLCanvas().getHeight()*0.00002f;
		getCamera().setupDimensions(leftL, upL);
	}


	public void setFrame(SFDrawableFrame frame) {
		this.frame = frame;
	}
	
	
}
