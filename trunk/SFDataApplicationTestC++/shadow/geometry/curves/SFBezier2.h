/*
 * SFBezier2.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBEZIER2_H_
#define SFBEZIER2_H_

#include "SFStandardAbstractCurve.h"

namespace sf {

class SFBezier2 :public SFStandardAbstractCurve{

public:

	SFBezier2(int n);

	virtual ~SFBezier2();

	SFBezier2(SFValuenf* a, SFValuenf* b, SFValuenf* c);

	void getDev2Dt(float t, SFValuenf* read);

	void getDevDt(float t, SFValuenf* read);

	void getVertex(float t, SFValuenf* read);

	SFBezier2* clone();

};

} /* namespace sf */
#endif /* SFBEZIER2_H_ */
