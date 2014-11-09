#ifndef shadow_image_H_
#define shadow_image_H_

#include "SFPipeline.h"
#include "SFNode.h"
#include "SFRenderer.h"
#include "SFResource.h"

namespace sf{
class SFDrawnRenderedTexture : public SFRenderedTexturesSet{

  	bool initialized;
  	SFPipelineTexture** textures;
  	short texturesSize;
  	bool useDefaultDepthBuffer;
  	bool useDefaultStencilBuffer;
  	int depthBuffer;
  	int stencilBuffer;
  	SFRenderer* renderer;
  	SFNode* node;
  	SFRenderedTexture renderedTexture;
  	SFResource resource;

public:
  	SFDrawnRenderedTexture(){
  		initialized=false;
  		renderer=new SFRenderer();
  		useDefaultDepthBuffer=false;
		useDefaultStencilBuffer=false;
		depthBuffer=-1;
		stencilBuffer=-1;
  	}

  	SFPipelineTexture* getTexture(int index) {
  		return textures[index];
	}

  	SFResource* getResource() {
  		return &resource;
	}

	
  	int getTextureSize() {
  		return texturesSize;
	}

	
  	void init() {
  		if(renderer==0 || node==0)
  			return;

  		if(!initialized){

  			//TODO : need a redesign of this
  			int colorsSize=texturesSize-(depthBuffer>=0?1:0);
  			SFBufferData** colorsData=new SFBufferData*[texturesSize-(depthBuffer>=0?1:0)];

  			for (int i = 0; i < texturesSize; i++) {
  				if(i!=depthBuffer && i!=stencilBuffer){
  					SFPipelineTexture* data=textures[i];
  					textures[i]=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateTextureBuffer(data->getWidth(), data->getHeight(),
  							data->getFormat(),  data->getFilters(),	data->getWrapS(), data->getWrapT());
  					colorsData[i]=textures[i];
  					//renderedTexture.addColorData(textures[i]);
				}
			}
  			renderedTexture.setColorData(colorsData,colorsSize);

  			if(depthBuffer>=0){
  				SFPipelineTexture* data=textures[depthBuffer];
  				textures[depthBuffer]=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateTextureBuffer(data->getWidth(), data->getHeight(),
  						data->getFormat(),  data->getFilters(),	data->getWrapS(), data->getWrapT());
  				renderedTexture.setDepthBuffer(textures[depthBuffer]);
  			}else if(useDefaultDepthBuffer){
  				SFPipelineTexture* data=textures[0];
  				SFBufferData* bufferData=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->
  					generatePlainBuffer(data->getWidth(), data->getHeight());
  				renderedTexture.setDepthBuffer(bufferData);
			}

  			if(stencilBuffer>=0){
  				SFPipelineTexture* data=textures[stencilBuffer];
  				textures[stencilBuffer]=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateTextureBuffer(data->getWidth(), data->getHeight(),
  						data->getFormat(),  data->getFilters(),	data->getWrapS(), data->getWrapT());
  				renderedTexture.setStencilBuffer(textures[stencilBuffer]);
  			}else if(useDefaultStencilBuffer){
  				SFPipelineTexture* data=textures[0];
  				SFBufferData* bufferData=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->
  					generatePlainBuffer(data->getWidth(), data->getHeight());
  				renderedTexture.setStencilBuffer(bufferData);
			}
  			initialized=true;

  			update();
		}
	}

	
  	void update() {

  		if(initialized){
  			SFPipeline::getSfTexturePipeline()->beginNewRenderedTexture(&renderedTexture);

  			  	renderer->getCamera()->getF().set3f(0.0f, 0, 0);
				float* matrix=renderer->getCamera()->extractTransform();
				SFPipeline::getSfPipelineGraphics()->setupProjection(matrix);
				renderer->render(node);

				renderer->getCamera()->getF().set3f(-2.0f, 0, 0);
				matrix=renderer->getCamera()->extractTransform();
				SFPipeline::getSfPipelineGraphics()->setupProjection(matrix);
				renderer->render(node);

				renderer->getCamera()->getF().set3f(+2.0f, 0, 0);
				matrix=renderer->getCamera()->extractTransform();
				SFPipeline::getSfPipelineGraphics()->setupProjection(matrix);
				renderer->render(node);

				renderer->getCamera()->getF().set3f(0, +2.0f, 0);
				matrix=renderer->getCamera()->extractTransform();
				SFPipeline::getSfPipelineGraphics()->setupProjection(matrix);
				renderer->render(node);

				renderer->getCamera()->getF().set3f(0, -2.0f, 0);
				matrix=renderer->getCamera()->extractTransform();
				SFPipeline::getSfPipelineGraphics()->setupProjection(matrix);
				renderer->render(node);

			SFPipeline::getSfTexturePipeline()->endRenderedTexture(&renderedTexture);

			SFPipeline::getSfTexturePipeline()->destroyRenderedTexture(&renderedTexture);
		}
	}

	
  	void destroy() {
  		if(initialized){
  			for (int i = 0; i < texturesSize; i++) {
  				SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->destroyBuffer(textures[i]);
			}
		}
	}

  	int getDepthBuffer() {
  		return depthBuffer;
	}

  	void setDepthBuffer(int depthBuffer) {
		this->depthBuffer = depthBuffer;
	}

  	int getStencilBuffer() {
  		return stencilBuffer;
	}

  	void setStencilBuffer(int stencilBuffer) {
		this->stencilBuffer = stencilBuffer;
	}

  	void setTextures(SFPipelineTexture** textures,int texturesSize) {
		this->textures = textures;
		this->texturesSize=texturesSize;
	}

  	void setRenderer(SFRenderer* renderer) {
		this->renderer = renderer;
	}

  	void setNode(SFNode* node) {
		this->node = node;
  		resource.setResource(0, node->getResource());
	}

  	bool isUseDefaultDepthBuffer() {
  		return useDefaultDepthBuffer;
	}

  	void setUseDefaultDepthBuffer(bool useDefaultDepthBuffer) {
		this->useDefaultDepthBuffer = useDefaultDepthBuffer;
	}

  	bool isUseDefaultStencilBuffer() {
  		return useDefaultStencilBuffer;
	}

  	void setUseDefaultStencilBuffer(bool useDefaultStencilBuffer) {
		this->useDefaultStencilBuffer = useDefaultStencilBuffer;
	}

};
}
#endif
