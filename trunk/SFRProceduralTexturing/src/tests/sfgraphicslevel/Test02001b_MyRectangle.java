package tests.sfgraphicslevel;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import shadow.nodes.SFObjectModel;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipeline.PipelineModule;
import shadow.pipeline.openGL20.SFGL2;
import shadow.renderer.SFModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFRenderer;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;
import shadow.system.data.SFDataCenter;
import tests.sfdatalevel.MainPTTest;

/* -disegna un quadrato */
public class Test02001b_MyRectangle extends MainPTTest implements SFDrawable{

	private static final String FILENAME="test0001_myrectangle";
	
	public static void main(String[] args) {
		setFilename(FILENAME);
		execute(new Test02001b_MyRectangle());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		this.objectModel=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		
		SFDrawableFrame frame=new SFDrawableFrame("MyRectangle", 600, 600, this);
		frame.setVisible(true);
	}
	

	private SFObjectModel objectModel;
	private SFProgramModuleStructures lightComponent=new SFProgramModuleStructures("BasicColor");
	
	@Override
	public void draw() {
		
		GL2 gl=SFGL2.getGL();
		
		gl.glClearColor(1, 1, 0, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		
		float[] projection={
				1,0,0,0,
				0,1,0,0,
				0,0,1,0,
				0,0,0,1
		};
		
		SFPipeline.getSfPipelineGraphics().setupProjection(projection);

		drawObjectModel(objectModel, lightComponent);
	}

	private void drawObjectModel(SFObjectModel objectModel,
			SFProgramModuleStructures lightComponent) {
		//Get the model
		SFModel model=this.objectModel.getModel();
		//generate or retrieve the program
		int program=model.getProgram(lightComponent);
		SFPipeline.getSfPipelineGraphics().loadProgram(program);
		//Setup any program data
		SFRenderer.setupMaterialData(PipelineModule.TRANSFORM,model.getTransformComponent());
		SFRenderer.setupMaterialData(PipelineModule.MATERIAL,model.getMaterialComponent());
		SFRenderer.setupMaterialData(PipelineModule.LIGHT,lightComponent);
		//apply object transform
		objectModel.getTransform().apply();
		//Draw the object
		objectModel.getModel().getGeometry().draw();
	}
	
	
}
