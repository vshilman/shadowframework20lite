#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFBlankDrawer.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFBlankDrawerData extends SFGraphicsAsset<SFRectDrawer>{

	SFBlankDrawerData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		return new SFBlankDrawer();
	}

	
	void updateResource(SFRectDrawer resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
