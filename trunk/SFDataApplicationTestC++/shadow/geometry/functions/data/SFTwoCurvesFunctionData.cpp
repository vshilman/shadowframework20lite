
#include "SFTwoCurvesFunctionData.h"

namespace sf{

SFTwoCurvesFunctionData::SFTwoCurvesFunctionData() {
	firstCurve=new SFLibraryReference<SFCurve>();
	secondCurve=new SFLibraryReference<SFCurve>();
	SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
	namedParameters->addObject( firstCurve);
	namedParameters->addObject( secondCurve);
	setData(namedParameters);
}


SFTwoCurvesFunctionData::~SFTwoCurvesFunctionData() {
	delete firstCurve;
	delete secondCurve;
}

void SFTwoCurvesFunctionData::addBuildingCurve(SFCurve* curve) {
	addingCurve.push_back(curve);
}

SFLibraryReference<SFCurve>* SFTwoCurvesFunctionData::getFirstCurve(){
	return firstCurve;
}

SFLibraryReference<SFCurve>* SFTwoCurvesFunctionData::getSecondCurve(){
	return secondCurve;
}

}
