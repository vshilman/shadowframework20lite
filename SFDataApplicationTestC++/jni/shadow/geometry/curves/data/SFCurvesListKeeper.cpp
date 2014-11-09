
#include "SFCurvesListKeeper.h"

namespace sf{

	SFCurvesListKeeper::SFCurvesListKeeper(){

	}

	SFCurvesListKeeper SFCurvesListKeeper::getKeeper() {
		return keeper;
	}

	SFCurvesList SFCurvesListKeeper::getCurvesList() {
		return curvesList;
	}

	void SFCurvesListKeeper::registerList(SFPrimitiveBlock block,SFCurvesList list){
		registeredList[block] = list;
	}

	void SFCurvesListKeeper::selectRegister(SFPrimitiveBlock* block){
		curvesList=registeredList.at(block);
	}

	void SFCurvesListKeeper::setBlock(int index,SFPrimitiveBlock* block){

		if(blockList.size()==index)
			blockList.push_back(block);
		else
			blockList[index]=block;
	}

	void SFCurvesListKeeper::selectBlock(int index){
		if(blockList.size()>index)
			selectRegister(blockList.at(index));
	}

}
