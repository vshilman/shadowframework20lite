#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFBorderIconDrawer.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/renderer/data.SFTextureData.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFBorderIconDrawerData extends SFGraphicsAsset<SFRectDrawer>{

	SFTextureData texture=new SFTextureData();
	SFShort borderSize=new SFShort((short)12);

	SFBorderIconDrawerData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("texture", texture);
		parameters.addObject("borderSize", borderSize);
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		final SFBorderIconDrawer drawer=new SFBorderIconDrawer();
		updateResource(drawer);
		return drawer;
	}

	
	void updateResource(SFRectDrawer resource) {
		((SFBorderIconDrawer)resource).setIcon(texture.buildTextureReference());
		((SFBorderIconDrawer)resource).setBorderSize(borderSize.getShortValue());
	}
}
;
}
#endif
