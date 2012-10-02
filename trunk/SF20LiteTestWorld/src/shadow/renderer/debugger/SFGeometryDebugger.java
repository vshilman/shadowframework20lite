package shadow.renderer.debugger;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.math.SFValuenf;
import shadow.pipeline.SFMesh;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.renderer.viewer.SFFrameController;
import shadow.system.SFArray;
import shadow.system.SFDrawable;


//TODO : SFGeometryDebugger should be finished.
public class SFGeometryDebugger implements SFDrawable{

	private static final float MIN_UNIT_VOLUME_SIZE=200.0f;
	private static final float MAX_UNIT_VOLUME_SIZE=4000.0f;
	private static final float DELTA_VOLUME_SIZE=100.0f;
	private static float unitVolumeSize=600.0f;
	
	private SFMesh mesh;
	private static boolean rotateModel;
	private static float rotateModelFactor;
	private static float rotation;
	private static boolean wireframe;
	
	public SFGeometryDebugger(){
		
	}
	
	public static SFGeometryDebugger generateFrame(SFMesh mesh,SFFrameController... controllers){
		SFGeometryDebugger viewer=new SFGeometryDebugger();
		viewer.mesh=mesh;
		SFDrawableFrame frame=new SFDrawableFrame("Scene Viewer", 600, 600, viewer, controllers);
		frame.setVisible(true);
		return viewer;
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
		
		float[] viewport=new float[4];
		gl.glGetFloatv(GL2.GL_VIEWPORT, viewport, 0);
		float width=viewport[2];
		float height=viewport[3];
		
		gl.glClearColor(0.4f,0.4f,0.4f,1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		if(wireframe)
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
		else
			gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		
		try {
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glScalef(unitVolumeSize/width,unitVolumeSize/height,0);
			
			gl.glMatrixMode(GL2.GL_MODELVIEW);
			gl.glLoadIdentity();
			if(rotateModel){
				rotation+=rotateModelFactor;
				gl.glRotatef(rotation*360/(2*(float)Math.PI), 0, 1, 0);
			}
				
			
			for (int gridIndex = 0; gridIndex < mesh.getPrimitive().getGridsCount(); gridIndex++) {
				
				SFPrimitiveBlock block=mesh.getPrimitive().getBlocks()[gridIndex];
				
				int index=mesh.getPrimitive().getIndicesPositions()[gridIndex];
				int size=mesh.getPrimitive().getIndicesSizes()[gridIndex];
				
				SFPrimitiveArray array=mesh.getArray();
				SFPrimitiveIndices indices=array.generateSample();
				SFArray<SFValuenf> values=array.getPrimitiveData(gridIndex);
				SFValuenf sample=values.generateSample();
				
				if(block==SFPrimitiveBlock.POSITION){
					gl.glBegin(GL2.GL_POINTS);		
					
					for (int primitive = mesh.getFirstElement(); primitive < mesh.getLastElement(); primitive++) {
						array.getElement(primitive, indices);
						int[] indicesV=indices.getPrimitiveIndices();
						for (int j = index; j < index+size; j++) {
							values.getElement(indicesV[j], sample);
							gl.glVertex3f(sample.get()[0],sample.get()[1],sample.get()[2]);
						}
					}
					
					gl.glEnd();
				}
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	public void setWireframe(boolean wireframe) {
		SFGeometryDebugger.wireframe = wireframe;
	}

	public void setRotateModel(boolean rotateModel,float rotateModelFactor){
		SFGeometryDebugger.rotateModel=rotateModel;
		SFGeometryDebugger.rotateModelFactor=rotateModelFactor;
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

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
			SFGeometryDebugger.wireframe=(index==0);
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


	public static SFFrameController getRotationController() {
		return rotationController;
	}

	public static SFFrameController getWireframeController() {
		return wireframeController;
	}

	public static SFFrameController getZoomController() {
		return zoomController;
	}
	
	
}
