package shadow.image;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.renderer.SFRenderable;
import shadow.renderer.SFRenderer;

public class SFFilteredRenderedTexture extends SFRenderable implements SFRenderedTexturesSet{

	private boolean initialized=false;
	private SFPipelineTexture[] textures;
	private SFRenderedTexture renderedTexture=new SFRenderedTexture();
	private String lightStep;
	
	@Override
	public void apply(int index, int level) throws ArrayIndexOutOfBoundsException{
		textures[index].apply(level);
	}
	
	@Override
	public int getTextureSize() {
		return textures.length;
	}
	
	@Override
	public void init() {
		
		if(!initialized){
			for (int i = 0; i < textures.length; i++) {
				SFPipelineTexture data=textures[i];
				textures[i]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
						data.getFormat(),  data.getFilters(),
						data.getWrapS(), data.getWrapT());
				renderedTexture.addColorData(textures[i]);
			}
			
			SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

				SFProgram program=SFPipeline.getStaticImageProgram(getMaterialsNames(),lightStep);
				SFPipeline.getSfProgramBuilder().loadProgram(program);
			
				SFRenderer.setupMaterialData(this);
				
				SFPipeline.getSfPipelineGraphics().drawBaseQuad();
				
			SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
		}
		initialized=true;
	}
	
	public SFRenderedTexture getRenderedTexture() {
		return renderedTexture;
	}

	public void setTextures(SFPipelineTexture[] textures) {
		this.textures = textures;
	}

	public String getLightStep() {
		return lightStep;
	}

	public void setLightStep(String lightStep) {
		this.lightStep = lightStep;
	}
	
	
}
