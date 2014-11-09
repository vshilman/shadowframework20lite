
#include "SFValuesListKeeper.h"


namespace sf{

	//static SFValuesListKeeper SFValuesListKeeper::keeper;

	SFValuesListKeeper::SFValuesListKeeper(){
		keeper=new SFValuesListKeeper();
		valuesList=0;
	}

	SFValuesListKeeper* SFValuesListKeepergetKeeper() {
		return &keeper;
	}

	SFValuenfList* SFValuesListKeeper::SFValuesListKeepergetValuesList() {
		return valuesList;
	}

	void SFValuesListKeeper::registerList(SFPrimitiveBlock block,SFValuenfList* list){
		registeredList[block]= list;
	}

	void SFValuesListKeeper::selectRegister(SFPrimitiveBlock block){
		valuesList=registeredList.at(block);
	}

	void SFValuesListKeeper::setValuesList(SFValuenfList* valuesList) {
		this->valuesList = valuesList;
	}

}
