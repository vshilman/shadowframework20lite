/*
 * SFBasisSpline1.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBASISSPLINE1_H_
#define SFBASISSPLINE1_H_

#include "SFBasisSpline.h"

namespace sf {

class SFBasisSpline1 : public SFBasisSpline{

	float* values;
	int values_length;
	int size;
	int n;

public:

	SFBasisSpline1(bool closed,int size);

	virtual ~SFBasisSpline1();

	void compileCurve();

	void getDev2Dt(float T, SFValuenf* write) ;

	void getDevDt(float T, SFValuenf* write);

	void getVertex(float T, SFValuenf* write);


};

} /* namespace sf */
#endif /* SFBASISSPLINE1_H_ */
