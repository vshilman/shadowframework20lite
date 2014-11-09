/*
 * SFBasisSpline2.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBASISSPLINE2_H_
#define SFBASISSPLINE2_H_

#include "SFBasisSpline.h"

namespace sf {

class SFBasisSpline2: public SFBasisSpline {

	float* values;
	int values_length;
	int size;
	int n;
public:

	SFBasisSpline2(bool closed,int size);

	virtual ~SFBasisSpline2();

	void compileCurve();

	void getDev2Dt(float T, SFValuenf* write);

	void getDevDt(float T, SFValuenf* write);

	void getVertex(float T, SFValuenf* write);

};

} /* namespace sf */
#endif /* SFBASISSPLINE2_H_ */
