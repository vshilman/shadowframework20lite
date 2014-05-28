#ifndef shadow_image_data_H_
#define shadow_image_data_H_

#include "shadow/image/SFFilteredRenderedTexture.h"
#include "shadow/image/SFPipelineTexture.h"
#include "shadow/image/SFRenderedTexturesSet.h"
#include "shadow/renderer/SFProgramModuleStructures.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFFilteredRenderedTextureData extends SFGraphicsAsset<SFRenderedTexturesSet>{

	SFDataObjectsList<SFTextureDataObject> textures =  new SFDataObjectsList<SFTextureDataObject>(new SFTextureDataObject());
	SFDataAssetList<SFProgramModuleStructures> lightComponent=new SFDataAssetList<SFProgramModuleStructures>();
	SFDataAssetList<SFProgramModuleStructures> materialComponent=new SFDataAssetList<SFProgramModuleStructures>();

	SFFilteredRenderedTextureData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("textures", textures);
		parameters.addObject("lightComponent", lightComponent);
		parameters.addObject("materialComponent", materialComponent);
		setData(parameters);
	}

	
	SFDataset generateNewDatasetInstance() {
		return new SFFilteredRenderedTextureData();
	}

	SFFilteredRenderedTexture renderedTexture;


	
	SFRenderedTexturesSet buildResource() {

		renderedTexture=new SFFilteredRenderedTexture();

		updateResource(renderedTexture);

		return renderedTexture;
	}

	
	void updateResource(SFRenderedTexturesSet resource) {
		SFFilteredRenderedTexture renderedTexture=(SFFilteredRenderedTexture)resource;

		SFPipelineTexture[] textures=new SFPipelineTexture[this->textures.size()];
		for (int i = 0; i < textures.length; i++) {
			textures[i]=this->textures.get(i).getTexture();
		}
		renderedTexture.setTextures(textures);

		if(materialComponent.size()==lightComponent.size()){

			SFProgramModuleStructures[] materials=new SFProgramModuleStructures[materialComponent.size()];
			for (int i = 0; i < materials.length; i++) {
				materials[i]=materialComponent.get(i).getResource();
				//getDataAssetResource().setResource(resourceIndex, materialComponent.get(i).getDataAssetResource());
				//resourceIndex++;
			}
			renderedTexture.setMaterials(materials);

			SFProgramModuleStructures[] lights=new SFProgramModuleStructures[lightComponent.size()];
			for (int i = 0; i < lights.length; i++) {
				lights[i]=lightComponent.get(i).getResource();
				//getDataAssetResource().setResource(resourceIndex, lightComponent.get(i).getDataAssetResource());
				//resourceIndex++;
			}
			renderedTexture.setLights(lights);
		}
			SFProgramModuleStructures[] materials=new SFProgramModuleStructures[materialComponent.size()];
			SFProgramModuleStructures[] lights=new SFProgramModuleStructures[materialComponent.size()];
			for (int i = 0; i < materials.length; i++) {
				materials[i]=materialComponent.get(i).getResource();
				lights[i]=lightComponent.get(0).getResource();
			}
			renderedTexture.setMaterials(materials);
			renderedTexture.setLights(lights);
		}
	}

}
;
}
#endif
