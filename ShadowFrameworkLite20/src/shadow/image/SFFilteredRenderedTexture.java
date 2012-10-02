package shadow.image;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFProgram;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.SFRenderer;

public class SFFilteredRenderedTexture implements SFRenderedTexturesSet{

	private boolean initialized=false;
	private SFPipelineTexture[] textures;
	private SFRenderedTexture renderedTexture=new SFRenderedTexture();
	private SFProgramModuleStructures lightComponent=new SFProgramModuleStructures();
	private SFProgramModuleStructures material=new SFProgramModuleStructures();
	

	public SFProgramModuleStructures getMaterialComponent() {
		return material;
	}

	public void setMaterialComponent(SFProgramModuleStructures material) {
		this.material = material;
	}

	@Override
	public SFPipelineTexture getTexture(int index) {
		return textures[index];
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
			update();
			
		}
		initialized=true;
	}
	
	
	
	@Override
	public void update() {

		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

			SFProgram program=SFPipeline.getStaticImageProgram(getMaterialComponent().getProgram(),lightComponent.getProgram());
			SFPipeline.getSfProgramBuilder().loadProgram(program);
		
			SFRenderer.setupMaterialData(Module.MATERIAL,material);
			SFRenderer.setupMaterialData(Module.LIGHT,lightComponent);
			
			SFPipeline.getSfPipelineGraphics().drawBaseQuad();
			
		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
		
		SFPipeline.getSfTexturePipeline().destroyRenderedTexture(renderedTexture);
	}
	
	@Override
	public void destroy() {
		if(initialized){
			for (int i = 0; i < textures.length; i++) {
				SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(textures[i]);
			}
		}
	}
	
	public SFRenderedTexture getRenderedTexture() {
		return renderedTexture;
	}

	public void setTextures(SFPipelineTexture[] textures) {
		this.textures = textures;
	}

	public SFProgramModuleStructures getLightComponent() {
		return lightComponent;
	}
	
	public void setLightComponent(SFProgramModuleStructures lightComponent) {
		this.lightComponent = lightComponent;
	}

	
	
}
