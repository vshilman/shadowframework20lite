/*
 * SFGenericCurve.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFGENERICCURVE_H_
#define SFGENERICCURVE_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/geometry/SFCurve.h"

namespace sf {

class SFGenericCurve : public SFCurve {

public:

	static const double DEFAULT_APPROXIMATION = 0.001f;

	double approximation;

public:
	SFGenericCurve();

	void getDevDt(float t, SFValuenf* read);

	void getDev2Dt(float t, SFValuenf* read);

};

} /* namespace sf */
#endif /* SFGENERICCURVE_H_ */
