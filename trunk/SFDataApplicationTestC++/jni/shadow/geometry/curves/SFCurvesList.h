/*
 * SFCurvesList.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFCURVESLIST_H_
#define SFCURVESLIST_H_

#include "shadow/system/SFIResource.h"
#include "shadow/system/SFResource.h"
#include "shadow/geometry/SFCurve.h"


namespace sf {

class SFCurvesList {

	SFCurve** curves;
	short curves_length;
	SFResource* resource;

public:
	SFCurvesList(int n) ;

	virtual ~SFCurvesList();

	int getCurvesLength();

	SFCurve** getCurves();

	SFResource* getResource();

};

} /* namespace sf */
#endif /* SFCURVESLIST_H_ */
