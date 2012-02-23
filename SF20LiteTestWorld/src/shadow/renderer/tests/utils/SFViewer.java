package shadow.renderer.tests.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL2;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.renderer.SFCamera;
import shadow.renderer.SFLodFilter;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFRenderer;
import shadow.renderer.SFRenderingAlgorithm;
import shadow.renderer.data.SFStructureReference;
import shadow.system.SFArrayElementException;
import shadow.system.SFDrawable;
import shadow.system.data.SFDataCenter;

public class SFViewer implements SFDrawable{

	private SFNode node;
	private SFRenderer renderer;

	private SFProgramStructureReference reference; 
	
	public SFViewer(){
		renderer=new SFRenderer();
		reference=getSceneLight();
		SFCamera camera=new SFCamera(new SFVertex3f(0,0,0), new SFVertex3f(0,0,1), new SFVertex3f(1,0,0), new SFVertex3f(0,1,0), 1, 1, 20,0.1f);
		camera.setChanged(true);
		
		camera.extractTransform();
		
		renderer.setCamera(camera);
		SFLightStep voidStep=new SFLightStep() {
			@Override
			public void prepareStep() {
				renderer.addLights(reference);
			}
			@Override
			public String getProgramName() {
				return "BasicLSPN";
			}
			
			@Override
			public void closeStep() {
				renderer.clearLights();
			}
		};
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
		renderer.setAlgorithm(new SFRenderingAlgorithm(steps,filters));
	}
	
	public static SFViewer generateFrame(SFNode node){
		SFViewer viewer=new SFViewer();
		viewer.node=node;
		SFViewerFrame frame=new SFViewerFrame("Scene Viewer", 600, 600, viewer);
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
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			e.printStackTrace();
		}

		//prepare Data Factor
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//laod the Objects Library
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary());
	}
	
	@Override
	public void draw() {
		
		
		GL2 gl=SFGL2.getGL(); 
		
		float[] matrix=renderer.getCamera().extractTransform();
		
		gl.glClearColor(1,1,1,1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		try {
			
			SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
			
			renderer.render(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Override
	public void init() {
		
	}

	private static SFProgramStructureReference getSceneLight(){
		String lightProgramName="BasicLSPN";
		SFStructureArray lightArray=generateLightData(lightProgramName, 0);
		SFVertex3f[] lightData={new SFVertex3f(2, 1, 1),new SFVertex3f(1, 1, -1)};
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
}
