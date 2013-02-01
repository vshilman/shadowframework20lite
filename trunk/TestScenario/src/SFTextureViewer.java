
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;

import shadow.image.SFTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFProgram;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.system.SFDrawable;

public class SFTextureViewer implements SFDrawable{

	private SFTexture texture;
	private SFProgram program;
	
	public SFTextureViewer(){
		program=SFPipeline.getStaticImageProgram("TexturedMat", "BasicColor");
	}
	
	public static SFTextureViewer generateFrame(SFTexture texture,SFFrameController... controllers){
		SFTextureViewer viewer=new SFTextureViewer();
		viewer.texture=texture;
		SFDrawableFrame frame=new SFDrawableFrame("Scene Viewer", 600, 600, viewer,controllers);
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
		
		gl.glClearColor(1,1,1,1);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		
		try {
			
			program.load();
			
			SFPipeline.getSfProgramBuilder().loadProgram(program);
			
			SFPipeline.getSfPipelineGraphics().loadTexture(Module.MATERIAL, texture.getTexture(), 0);
			
			SFPipeline.getSfPipelineGraphics().drawBaseQuad();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void setTexture(SFTexture texture) {
		this.texture = texture;
	}

	
}
