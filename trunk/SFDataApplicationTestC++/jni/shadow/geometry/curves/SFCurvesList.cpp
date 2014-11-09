/*
 * SFCurvesList.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFCurvesList.h"

namespace sf {


SFCurvesList::SFCurvesList(int n) {
	curves=new SFCurve*[n];
	curves_length=n;
	resource=new SFResource(1);
}
SFCurvesList::~SFCurvesList(){
	delete resource;
	for(int i=0;i<curves_length;i++){
		delete curves[i];
	}
	delete[] curves;
}

int SFCurvesList::getCurvesLength() {
	return curves_length;
}

SFCurve** SFCurvesList::getCurves() {
	return curves;
}

SFResource* SFCurvesList::getResource() {
	return resource;
}


} /* namespace sf */
