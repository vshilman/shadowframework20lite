#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/curves/SFCurvesList.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"


namespace sf{
class SFCurvesListKeeper {

	static SFCurvesListKeeper keeper=new SFCurvesListKeeper();

	SFCurvesListKeeper();

	vector<SFPrimitiveBlock*> blockList;

	map<SFPrimitiveBlock*, SFCurvesList*> registeredList;

	SFCurvesList curvesList;

public:
	static SFCurvesListKeeper getKeeper();

	SFCurvesList getCurvesList();

	void registerList(SFPrimitiveBlock block,SFCurvesList list);

	void selectRegister(SFPrimitiveBlock* block);

	void setBlock(int index,SFPrimitiveBlock* block);

	void selectBlock(int index);
};

}
#endif
