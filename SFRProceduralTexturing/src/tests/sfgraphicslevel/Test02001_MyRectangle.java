package tests.sfgraphicslevel;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import sfogl2.SFOGLRenderedTexture;
import sfogl2.tests.objects.BlurredScreenObject;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipeline.PipelineModule;
import shadow.pipeline.openGL20.SFGL2;
import shadow.renderer.SFModel;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;
import shadow.system.data.SFDataCenter;
import tests.sfdatalevel.MainPTTest;

/* -disegna un quadrato */
public class Test02001_MyRectangle extends MainPTTest implements SFDrawable{

	private static final String FILENAME="test0001_myrectangle";
	
	public static void main(String[] args) {
		setFilename(FILENAME);
		execute(new Test02001_MyRectangle());
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		this.objectModel=(SFObjectModel)SFDataCenter.getDataCenter().makeDatasetAvailable("RectangleModel").getResource();
		
		SFDrawableFrame frame=new SFDrawableFrame("MyRectangle", 600, 600, this);
		frame.setVisible(true);
	}
	

	private SFObjectModel objectModel;
	private SFProgramModuleStructures lightComponent=new SFProgramModuleStructures("BasicColor");
	
	public static final int FRAMEBUFFERSIZE=512;
	private static SFOGLRenderedTexture renderedTexture=new SFOGLRenderedTexture(FRAMEBUFFERSIZE,FRAMEBUFFERSIZE,true);
	private boolean initialized=false;

	private BlurredScreenObject screen=new BlurredScreenObject(FRAMEBUFFERSIZE,renderedTexture);
	
	@Override
	public void draw() {
		
		GL2 gl=SFGL2.getGL();
		
		if(!initialized){
			renderedTexture.prepare(gl);
			screen.init(gl);
			initialized=true;
		}
		
		gl.glClearColor(1, 1, 0, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		int[] viewport=new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
		
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		
		float[] projection={
				1,0,0,0,
				0,1,0,0,
				0,0,1,0,
				0,0,0,1
		};
		SFPipeline.getPipeline().setupProjection(projection);

		renderedTexture.apply(gl);
		
		drawObjectModel(objectModel, lightComponent);

		renderedTexture.unapply(gl);

		gl.glViewport(0, 0, viewport[2],viewport[3]);

		screen.draw(gl);
	}

	private void drawObjectModel(SFObjectModel objectModel,
			SFProgramModuleStructures lightComponent) {
		//Get the model
		SFModel model=this.objectModel.getModel();
		//generate or retrieve the program
		int program=model.getProgram(lightComponent);
		SFPipeline.getPipeline().loadProgram(program);
		//Setup any program data
		model.getTransformComponent().setupData(PipelineModule.TRANSFORM);
		model.getMaterialComponent().setupData(PipelineModule.MATERIAL);
		lightComponent.setupData(PipelineModule.LIGHT);
		//apply object transform
		objectModel.getTransform().apply();
		//Draw the object
		objectModel.getModel().getGeometry().draw();
	}
	
	
}
