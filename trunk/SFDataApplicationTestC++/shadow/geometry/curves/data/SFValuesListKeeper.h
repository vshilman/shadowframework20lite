#ifndef shadow_geometry_curves_data_SFValuesListKeeper_H_
#define shadow_geometry_curves_data_SFValuesListKeeper_H_

#include "shadow/geometry/curves/SFValuenfList.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"

namespace sf{
class SFValuesListKeeper {

	static SFValuesListKeeper keeper;

public:
	SFValuesListKeeper();

	map<SFPrimitiveBlock, SFValuenfList*> registeredList;

	SFValuenfList* valuesList;

	static SFValuesListKeeper* getKeeper();

	SFValuenfList* getValuesList();

	void registerList(SFPrimitiveBlock block,SFValuenfList* list);

	void selectRegister(SFPrimitiveBlock block);

	void setValuesList(SFValuenfList* valuesList);
};

}
#endif
