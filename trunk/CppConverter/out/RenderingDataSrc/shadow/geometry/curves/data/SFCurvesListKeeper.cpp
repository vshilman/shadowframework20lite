#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/ArrayList.h"
#include "java/util/HashMap.h"

#include "shadow/geometry/curves.SFCurvesList.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"

class SFCurvesListKeeper {

	static SFCurvesListKeeper keeper=new SFCurvesListKeeper();

	SFCurvesListKeeper(){

	}

	ArrayList<SFPrimitiveBlock> blockList=new ArrayList<SFPrimitiveBlock>();

	HashMap<SFPrimitiveBlock, SFCurvesList> registeredList= new HashMap<SFPrimitiveBlock, SFCurvesList>();

	SFCurvesList curvesList;

	static SFCurvesListKeeper getKeeper() {
		return keeper;
	}

	SFCurvesList getCurvesList() {
		return curvesList;
	}

	void registerList(SFPrimitiveBlock block,SFCurvesList list){
		registeredList.put(block, list);
	}

	void selectRegister(SFPrimitiveBlock block){
		curvesList=registeredList.get(block);
	}

	void setBlock(int index,SFPrimitiveBlock block){

		if(blockList.size()==index)
			blockList.add(block);
		else
			blockList.set(index,block);
	}

	void selectBlock(int index){
		if(blockList.size()>index)
			selectRegister(blockList.get(index));
	}
}
;
}
#endif
