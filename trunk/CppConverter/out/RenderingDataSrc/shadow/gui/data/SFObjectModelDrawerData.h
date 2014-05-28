#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFNodeDrawer.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFObjectModelDrawerData extends SFGraphicsAsset<SFRectDrawer>{

	SFLibraryReference<SFNode> model=new SFLibraryReference<SFNode>();

	SFObjectModelDrawerData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("model", model);
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		final SFNodeDrawer drawer=new SFNodeDrawer();
		updateResource(drawer);
		return drawer;
	}



	
	void updateResource(SFRectDrawer resource) {
		final SFNodeDrawer drawer=(SFNodeDrawer)resource;
		SFNode model=this->model.getResource();
		drawer.setModel(model);
	}
}
;
}
#endif
