#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/SFToolWindow.h"
#include "shadow/gui/drawers.SFDrawersContainer.h"
#include "shadow/gui/drawers.SFRectDrawableElement.h"
#include "shadow/gui/drawers.SFScreenRectData.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFDrawerContainerData extends SFGraphicsAsset<SFRectDrawer>{

	AssetList<SFRectDrawableElement> elements=new SFDataAssetList<SFRectDrawableElement>();

	SFShort windowW=new SFShort((short)100);
	SFShort windowH=new SFShort((short)100);

	SFDrawerContainerData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
			parameters.addObject("elements", elements);
			parameters.addObject("windowW", windowW);
			parameters.addObject("windowH", windowH);
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		SFScreenRectData.setBuildingWindow(new SFToolWindow(windowW.getShortValue(), windowH.getShortValue()));
		SFDrawersContainer container=new SFDrawersContainer();
		for(int i=0;i<elements.size();i++){
			SFRectDrawableElement element=elements.get(i).getResource();
			container.addDrawer(element.getDrawer(), element.getRect());
		}
		return container;
	}

	
	void updateResource(SFRectDrawer resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
