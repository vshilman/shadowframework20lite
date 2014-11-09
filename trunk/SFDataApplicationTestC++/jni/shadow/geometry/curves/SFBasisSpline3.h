/*
 * SFBasisSpline3.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBASISSPLINE3_H_
#define SFBASISSPLINE3_H_

#include "SFBasisSpline.h"

namespace sf {

class SFBasisSpline3 : public SFBasisSpline {

	float* values;
	short values_length;
	short size;
	short n;

public:

	SFBasisSpline3(bool closed,int size);

	virtual ~SFBasisSpline3();

	void compileCurve();

	void getDev2Dt(float T, SFValuenf* write);

	void getDevDt(float T, SFValuenf* write);

	void getVertex(float T, SFValuenf* write);

};

} /* namespace sf */
#endif /* SFBASISSPLINE3_H_ */
