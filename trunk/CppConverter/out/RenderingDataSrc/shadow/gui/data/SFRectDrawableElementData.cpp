#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFRectDrawableElement.h"
#include "shadow/gui/drawers.SFScreenRectData.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFRectDrawableElementData  extends SFGraphicsAsset<SFRectDrawableElement>{

	SFScreenRectData screenRect=new SFScreenRectData();
	SFLibraryReference<SFRectDrawer> drawer=new SFLibraryReference<SFRectDrawer>();

	SFRectDrawableElementData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("bounds", screenRect.getSFDataObject());
		parameters.addObject("drawer", drawer);
		setData(parameters);
	}

	
	SFRectDrawableElement buildResource() {
		final SFRectDrawableElement element=new SFRectDrawableElement();
		element.setDrawer(drawer.getResource());
		element.getRect().set(screenRect.getResource());
		return element;
	}

	
	void updateResource(SFRectDrawableElement resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
