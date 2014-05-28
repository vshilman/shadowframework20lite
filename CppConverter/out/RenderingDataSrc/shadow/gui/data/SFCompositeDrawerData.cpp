#ifndef shadow_gui_data_H_
#define shadow_gui_data_H_

#include "shadow/gui/SFRectDrawer.h"
#include "shadow/gui/drawers.SFCompositeDrawer.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFCompositeDrawerData extends SFGraphicsAsset<SFRectDrawer>{

	SFLibraryReferenceList<SFRectDrawer> drawers=
			new SFLibraryReferenceList<SFRectDrawer>(new SFLibraryReference<SFRectDrawer>());

	SFCompositeDrawerData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
			parameters.addObject("components", drawers);
		setData(parameters);
	}

	
	SFRectDrawer buildResource() {
		final SFCompositeDrawer drawer=new SFCompositeDrawer();

		for(SFLibraryReference<SFRectDrawer> component: drawers){
			drawer.addDrawer(component.getResource());
//			component.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFRectDrawer>>() {

//				void onDatasetAvailable(String name,SFDataAsset<SFRectDrawer> dataset) {
//					drawer.addDrawer(dataset.getResource());
}
}
		}

		return drawer;
	}

	
	void updateResource(SFRectDrawer resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
