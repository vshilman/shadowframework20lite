#ifndef shadow_image_H_
#define shadow_image_H_

#include "shadow/pipeline/SFPipeline.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFRenderer.h"
#include "shadow/system/SFResource.h"

///**
// * @author Alessandro Martinelli
// */
class SFDrawnRenderedTexture implements SFRenderedTexturesSet{

//	boolean initialized=false;
//	SFPipelineTexture[] textures;
//	boolean useDefaultDepthBuffer=false;
//	boolean useDefaultStencilBuffer=false;
//	int depthBuffer=-1;
//	int stencilBuffer=-1;
//	SFRenderer renderer=new SFRenderer();
//	SFNode node;
//	SFRenderedTexture renderedTexture=new SFRenderedTexture();
//	SFResource resource=new SFResource(1,this);

	
//	SFPipelineTexture getTexture(int index) {
//		return textures[index];
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	int getTextureSize() {
//		return textures.length;
	}

	
//	void init() {
//		if(renderer==null || node==null)
//			return;

//		if(!initialized){

//			System.err.println("SO depthBuffer IS "+depthBuffer+" stencilBuffer "+stencilBuffer+" depthBuffer ");

//			//TODO : need a redesign of this
//			SFBufferData[] colorsData=new SFBufferData[textures.length-(depthBuffer>=0?1:0)];
//			for (int i = 0; i < textures.length; i++) {
//				if(i!=depthBuffer && i!=stencilBuffer){
//					SFPipelineTexture data=textures[i];
//					textures[i]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
//							data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
//					colorsData[i]=textures[i];
//					//renderedTexture.addColorData(textures[i]);
				}
			}
//			renderedTexture.setColorData(colorsData);

//			if(depthBuffer>=0){
//				SFPipelineTexture data=textures[depthBuffer];
//				textures[depthBuffer]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
//						data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
//				renderedTexture.setDepthBuffer(textures[depthBuffer]);
			}
//				SFPipelineTexture data=textures[0];
//				SFBufferData bufferData=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().
//					generatePlainBuffer(data.getWidth(), data.getHeight());
//				renderedTexture.setDepthBuffer(bufferData);
			}

//			if(stencilBuffer>=0){
//				SFPipelineTexture data=textures[stencilBuffer];
//				textures[stencilBuffer]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
//						data.getFormat(),  data.getFilters(),	data.getWrapS(), data.getWrapT());
//				renderedTexture.setStencilBuffer(textures[stencilBuffer]);
			}
//				SFPipelineTexture data=textures[0];
//				SFBufferData bufferData=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().
//					generatePlainBuffer(data.getWidth(), data.getHeight());
//				renderedTexture.setStencilBuffer(bufferData);
			}
//			initialized=true;

//			update();
		}
	}

	
//	void update() {

//		if(initialized){
//			try {

//				SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

//					renderer.getCamera().getF().set3f(0.0f, 0, 0);
//					float[] matrix=renderer.getCamera().extractTransform();
//					SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
//					renderer.render(node);

//					renderer.getCamera().getF().set3f(-2.0f, 0, 0);
//					matrix=renderer.getCamera().extractTransform();
//					SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
//					renderer.render(node);

//					renderer.getCamera().getF().set3f(+2.0f, 0, 0);
//					matrix=renderer.getCamera().extractTransform();
//					SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
//					renderer.render(node);

//					renderer.getCamera().getF().set3f(0, +2.0f, 0);
//					matrix=renderer.getCamera().extractTransform();
//					SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
//					renderer.render(node);

//					renderer.getCamera().getF().set3f(0, -2.0f, 0);
//					matrix=renderer.getCamera().extractTransform();
//					SFPipeline.getSfPipelineGraphics().setupProjection(matrix);
//					renderer.render(node);

//				SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);

//				SFPipeline.getSfTexturePipeline().destroyRenderedTexture(renderedTexture);
			}
//				e.printStackTrace();
			}
		}
	}

	
//	void destroy() {
//		if(initialized){
//			for (int i = 0; i < textures.length; i++) {
//				SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(textures[i]);
			}
		}
	}

//	int getDepthBuffer() {
//		return depthBuffer;
	}

//	void setDepthBuffer(int depthBuffer) {
		this->depthBuffer = depthBuffer;
	}

//	int getStencilBuffer() {
//		return stencilBuffer;
	}

//	void setStencilBuffer(int stencilBuffer) {
		this->stencilBuffer = stencilBuffer;
	}

//	void setTextures(SFPipelineTexture[] textures) {
		this->textures = textures;
	}

//	void setRenderer(SFRenderer renderer) {
		this->renderer = renderer;
	}

//	void setNode(SFNode node) {
		this->node = node;
//		resource.setResource(0, node.getResource());
	}

//	boolean isUseDefaultDepthBuffer() {
//		return useDefaultDepthBuffer;
	}

//	void setUseDefaultDepthBuffer(boolean useDefaultDepthBuffer) {
		this->useDefaultDepthBuffer = useDefaultDepthBuffer;
	}

//	boolean isUseDefaultStencilBuffer() {
//		return useDefaultStencilBuffer;
	}

//	void setUseDefaultStencilBuffer(boolean useDefaultStencilBuffer) {
		this->useDefaultStencilBuffer = useDefaultStencilBuffer;
	}

}
;
}
#endif
