#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/renderer/SFCamera.h"
#include "shadow/renderer/SFProgramModuleStructures.h"
#include "shadow/renderer/SFRenderer.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFRendererData extends SFGraphicsAsset<SFRenderer>{

	AssetObject<SFCamera> camera = new SFDataAssetObject<SFCamera>(null);
	SFDataAssetObject<SFProgramModuleStructures> light=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());

	SFRendererData() {
		prepare();
	}

	SFRendererData(String lightProgramName,SFDataAsset<SFCamera> camera) {
		prepare();
		this->camera.setDataset(camera);
		((SFProgramAssetData)this->light.getDataset()).setProgram(lightProgramName);
	}


	void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("camera", camera);
		parameters.addObject("light", light);
		setData(parameters);
	}

	
	SFRenderer buildResource() {
		final SFRenderer renderer = new SFRenderer();
		renderer.setLight(light.getResource());
		renderer.setCamera(this->camera.getDataset().getResource());
		return renderer;
	}

	
	void updateResource(SFRenderer resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
