#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFIconDrawer.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/renderer/data.SFTextureData.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFIconDrawerData extends SFGraphicsAsset<SFRectDrawer>{

	SFTextureData texture=new SFTextureData();

	SFIconDrawerData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("texture", texture);
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		final SFIconDrawer drawer=new SFIconDrawer();
		updateResource(drawer);
		return drawer;
	}

	
	void updateResource(SFRectDrawer resource) {
		((SFIconDrawer)resource).setIcon(texture.buildTextureReference());
	}
}
;
}
#endif
