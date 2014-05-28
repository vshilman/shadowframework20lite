#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/HashMap.h"

#include "shadow/geometry/curves.SFValuenfList.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"

class SFValuesListKeeper {

	static SFValuesListKeeper keeper=new SFValuesListKeeper();

	SFValuesListKeeper(){

	}

	HashMap<SFPrimitiveBlock, SFValuenfList> registeredList= new HashMap<SFPrimitiveBlock, SFValuenfList>();

	SFValuenfList valuesList;

	static SFValuesListKeeper getKeeper() {
		return keeper;
	}

	SFValuenfList getValuesList() {
		return valuesList;
	}

	void registerList(SFPrimitiveBlock block,SFValuenfList list){
		registeredList.put(block, list);
	}

	void selectRegister(SFPrimitiveBlock block){
		valuesList=registeredList.get(block);
	}

	void setValuesList(SFValuenfList valuesList) {
		this->valuesList = valuesList;
	}
}
;
}
#endif
