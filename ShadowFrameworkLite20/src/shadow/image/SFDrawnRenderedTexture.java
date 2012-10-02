package shadow.image;

import shadow.pipeline.SFPipeline;
import shadow.renderer.SFNode;
import shadow.renderer.SFRenderer;

/**
 * @author Alessandro Martinelli
 */
public class SFDrawnRenderedTexture implements SFRenderedTexturesSet{

	private boolean initialized=false;
	private SFPipelineTexture[] textures;
	private boolean useDefaultDepthBuffer=false;
	private boolean useDefaultStencilBuffer=false;
	private int depthBuffer=-1;
	private int stencilBuffer=-1;
	private SFRenderer renderer=new SFRenderer();
	private SFNode node;
	SFRenderedTexture renderedTexture=new SFRenderedTexture();
	
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
		if(renderer==null || node==null)
			return;
		
		if(!initialized){
			
			for (int i = 0; i < textures.length; i++) {
				if(i!=depthBuffer && i!=stencilBuffer){
					SFPipelineTexture data=textures[i];
					textures[i]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
							data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
					renderedTexture.addColorData(textures[i]);
				}
			}
			
			if(depthBuffer>=0){
				SFPipelineTexture data=textures[depthBuffer];
				textures[depthBuffer]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
						data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
				renderedTexture.setDepthBuffer(textures[depthBuffer]);
			}else if(useDefaultDepthBuffer){
				SFPipelineTexture data=textures[0];
				SFBufferData bufferData=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().
					generatePlainBuffer(data.getWidth(), data.getHeight());
				renderedTexture.setDepthBuffer(bufferData);
			}
			
			if(stencilBuffer>=0){
				SFPipelineTexture data=textures[stencilBuffer];
				textures[stencilBuffer]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
						data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
				renderedTexture.setStencilBuffer(textures[stencilBuffer]);
			}else if(useDefaultStencilBuffer){
				SFPipelineTexture data=textures[0];
				SFBufferData bufferData=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().
					generatePlainBuffer(data.getWidth(), data.getHeight());
				renderedTexture.setStencilBuffer(bufferData);
			}
			
			SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

				float[] matrix=renderer.getCamera().extractTransform();
				SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
				renderer.render(node);
			
				renderer.getCamera().getF().set3f(-2.0f, 0, 0);
				matrix=renderer.getCamera().extractTransform();
				SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
				renderer.render(node);
			
				renderer.getCamera().getF().set3f(+2.0f, 0, 0);
				matrix=renderer.getCamera().extractTransform();
				SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
				renderer.render(node);
			
				renderer.getCamera().getF().set3f(0, +2.0f, 0);
				matrix=renderer.getCamera().extractTransform();
				SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
				renderer.render(node);
			
				renderer.getCamera().getF().set3f(0, -2.0f, 0);
				matrix=renderer.getCamera().extractTransform();
				SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
				renderer.render(node);
				
			SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);
			
			SFPipeline.getSfTexturePipeline().destroyRenderedTexture(renderedTexture);
		}
		initialized=true;
	}
	
	@Override
	public void update() {
		
		
	}
	
	@Override
	public void destroy() {
		if(initialized){
			for (int i = 0; i < textures.length; i++) {
				SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(textures[i]);
			}
		}
	}

	public int getDepthBuffer() {
		return depthBuffer;
	}

	public void setDepthBuffer(int depthBuffer) {
		this.depthBuffer = depthBuffer;
	}

	public int getStencilBuffer() {
		return stencilBuffer;
	}

	public void setStencilBuffer(int stencilBuffer) {
		this.stencilBuffer = stencilBuffer;
	}

	public void setTextures(SFPipelineTexture[] textures) {
		this.textures = textures;
	}

	public void setRenderer(SFRenderer renderer) {
		this.renderer = renderer;
	}

	public void setNode(SFNode node) {
		this.node = node;
	}

	public boolean isUseDefaultDepthBuffer() {
		return useDefaultDepthBuffer;
	}

	public void setUseDefaultDepthBuffer(boolean useDefaultDepthBuffer) {
		this.useDefaultDepthBuffer = useDefaultDepthBuffer;
	}

	public boolean isUseDefaultStencilBuffer() {
		return useDefaultStencilBuffer;
	}

	public void setUseDefaultStencilBuffer(boolean useDefaultStencilBuffer) {
		this.useDefaultStencilBuffer = useDefaultStencilBuffer;
	}

}
