#ifndef shadow_image_H_
#define shadow_image_H_

#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPipelineGraphics.BlendMode.h"
#include "shadow/pipeline/SFPipelineGraphics.Module.h"
#include "shadow/pipeline/SFProgram.h"
#include "shadow/renderer/SFProgramModuleStructures.h"
#include "shadow/renderer/SFRenderer.h"
#include "shadow/system/SFResource.h"

class SFFilteredRenderedTexture implements SFRenderedTexturesSet{

//	boolean initialized=false;
//	SFPipelineTexture[] textures;
//	SFRenderedTexture renderedTexture=new SFRenderedTexture();

//	SFProgramModuleStructures[] steps;
//	SFProgramModuleStructures[] materials;
//	SFProgram[] programs;
//	SFResource resource=new SFResource(10,this);


	
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

//		if(!initialized){
//			SFBufferData[] colorsData=new SFBufferData[textures.length];
//			for (int i = 0; i < textures.length; i++) {
//				SFPipelineTexture data=textures[i];
//				textures[i]=SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().generateTextureBuffer(data.getWidth(), data.getHeight(), 
//						data.getFormat(),  data.getFilters(),
//						data.getWrapS(), data.getWrapT());
//				colorsData[i]=textures[i];
			}
//			renderedTexture.setColorData(colorsData);

//			update();
		}
//		initialized=true;

	}



	
//	void update() {

//		programs=new SFProgram[steps.length];
//		for (int i = 0; i < programs.length; i++) {
//			programs[i]=SFPipeline.getStaticImageProgram(materials[i].getProgram(),steps[i].getProgram());
		}

//		SFPipeline.getSfTexturePipeline().beginNewRenderedTexture(renderedTexture);

//			SFPipeline.getSfPipelineGraphics().pushBlendMode( BlendMode.COLORSUM);
//			for (int i = 0; i < steps.length; i++) {
//				SFProgram program=programs[i];
//				SFPipeline.getSfProgramBuilder().loadProgram(program);

//				SFRenderer.setupMaterialData(Module.MATERIAL,materials[i]);
//				SFRenderer.setupMaterialData(Module.LIGHT,steps[i]);

//				SFPipeline.getSfPipelineGraphics().drawBaseQuad();
			}
//			SFPipeline.getSfPipelineGraphics().popBlendMode();

//		SFPipeline.getSfTexturePipeline().endRenderedTexture(renderedTexture);

//		SFPipeline.getSfTexturePipeline().destroyRenderedTexture(renderedTexture);
	}

	
//	void destroy() {
//		if(initialized){
//			for (int i = 0; i < textures.length; i++) {
//				SFPipeline.getSfTexturePipeline().getRenderedTextureFactory().destroyBuffer(textures[i]);
			}
		}
	}

//	void setTextures(SFPipelineTexture[] textures) {
		this->textures = textures;
	}


//	SFProgramModuleStructures[] getLightComponent() {
//		return steps;
	}

//	void setLights(SFProgramModuleStructures[] lightComponent) {
		this->steps = lightComponent;
	}

//	void setLights(SFProgramModuleStructures lightComponent) {
		}
		this->steps = lightComponents;
	}

//	SFProgramModuleStructures[] getMaterial() {
//		return materials;
	}

//	void setMaterials(SFProgramModuleStructures[] materials) {
		this->materials = materials;
//		for (int i = 0; i < materials.length; i++) {
//			resource.setResource(i, materials[0].getResource());
		}
	}

}
;
}
#endif
