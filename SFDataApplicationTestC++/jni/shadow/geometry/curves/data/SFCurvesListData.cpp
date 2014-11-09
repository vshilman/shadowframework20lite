
#include "SFCurvesListData.h"

namespace sf{

	SFCurvesListData::SFCurvesListData() {
		curves=new SFDataAssetList<SFCurve*>();
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject(curves);
		setData(parameters);
	}

	SFCurvesList* SFCurvesListData::buildResource() {
		SFCurvesList* curveList=new SFCurvesList(this->curves->size());
		updateResource(curveList);
		return curveList;
	}
	
	void SFCurvesListData::updateResource(SFCurvesList* curveList) {
		for (int i = 0; i < this->curves->size(); i++) {
			curveList->getCurves()[i]=this->curves->at(i);
		}
	}


}
