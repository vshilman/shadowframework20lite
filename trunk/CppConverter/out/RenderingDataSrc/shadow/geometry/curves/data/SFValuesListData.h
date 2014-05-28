#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves.SFValuenfList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFValuesListData extends SFDataAsset<SFValuenfList>{

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	SFValuesListData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}

	
	SFValuenfList buildResource() {
		SFValuenfList curveList=new SFValuenfList(this->vertices.getResource().getSize());
		updateResource(curveList);
		return curveList;
	}

	
	void updateResource(SFValuenfList list) {
		SFValuesList<SFValuenf> values=this->vertices.getResource();
		 SFValuesIterator<SFValuenf> iterator=values.getIterator();

		 int index=0;
		 while(iterator.hasNext()){
			 SFVertex3f value=new SFVertex3f();
				iterator.getNext(value);
			 list.getValues()[index]=value;
				index++;
		 }

	}

}

;
}
#endif
