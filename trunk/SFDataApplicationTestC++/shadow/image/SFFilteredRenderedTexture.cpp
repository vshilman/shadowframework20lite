
#include "SFFilteredRenderedTexture.h"

namespace sf{

	SFPipelineTexture* SFFilteredRenderedTexture::getTexture(int index) {
		return textures[index];
	}

	SFResource* SFFilteredRenderedTexture::getResource() {
		return &resource;
	}


	int SFFilteredRenderedTexture::getTextureSize() {
		return texturesLength;
	}


	void SFFilteredRenderedTexture::init() {

		if(!initialized){
			SFBufferData** colorsData=new SFBufferData*[texturesLength];
			for (int i = 0; i < texturesLength; i++) {
				SFPipelineTexture* data=textures[i];
				textures[i]=SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->generateTextureBuffer(data->getWidth(), data->getHeight(),
						data->getFormat(),  data->getFilters(),
						data->getWrapS(), data->getWrapT());
				colorsData[i]=textures[i];
		}
			renderedTexture->setColorData(colorsData,texturesLength);

			update();
	}
		initialized=true;

}




	void SFFilteredRenderedTexture::update() {

		programs=new SFProgram*[stespSize];
		for (int i = 0; i < stespSize; i++) {
			programs[i]=SFPipeline::getStaticImageProgram(materials[i].getProgram(),steps[i].getProgram());
		}

		SFPipeline::getSfTexturePipeline()->beginNewRenderedTexture(renderedTexture);

			SFPipeline::getSfPipelineGraphics()->pushBlendMode( SFPipelineGraphics::COLORSUM);
			for (int i = 0; i < stespSize; i++) {
				SFProgram* program=programs[i];
				SFPipeline::getSfProgramBuilder()->loadProgram(program);

				SFRenderer::setupMaterialData(SFPipelineGraphics::MATERIAL,materials[i]);
				SFRenderer::setupMaterialData(SFPipelineGraphics::LIGHT,steps[i]);

				SFPipeline::getSfPipelineGraphics()->drawBaseQuad();
		}
			SFPipeline::getSfPipelineGraphics()->popBlendMode();

		SFPipeline::getSfTexturePipeline()->endRenderedTexture(renderedTexture);

		SFPipeline::getSfTexturePipeline()->destroyRenderedTexture(renderedTexture);
	}


	void SFFilteredRenderedTexture::destroy() {
		if(initialized){
			for (int i = 0; i < texturesLength; i++) {
				SFPipeline::getSfTexturePipeline()->getRenderedTextureFactory()->destroyBuffer(textures[i]);
		}
	}
}

	void SFFilteredRenderedTexture::setTextures(SFPipelineTexture** textures,short texturesLength) {
		this->textures = textures;
		this->texturesLength=texturesLength;
	}


	SFProgramModuleStructures* SFFilteredRenderedTexture::getLightComponent() {
		return steps;
	}

	void SFFilteredRenderedTexture::setLights(SFProgramModuleStructures* lightComponent) {
		this->steps = lightComponent;
	}

	void SFFilteredRenderedTexture::setLights(SFProgramModuleStructures lightComponent) {
		SFProgramModuleStructures* lightComponents=new SFProgramModuleStructures[1];
		lightComponents[0]=lightComponent;
		this->steps = lightComponents;
	}

	SFProgramModuleStructures* SFFilteredRenderedTexture::getMaterial() {
		return materials;
	}

	void SFFilteredRenderedTexture::setMaterials(SFProgramModuleStructures* materials) {
		this->materials = materials;
			for (int i = 0; i < stespSize; i++) {
				resource.setResource(i, materials[0].getResource());
		}
	}
}
