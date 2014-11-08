/*
 * SFBezier3.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFBEZIER3_H_
#define SFBEZIER3_H_

#include "SFStandardAbstractCurve.h"

namespace sf {

class SFBezier3 : public SFStandardAbstractCurve {

public:
	SFBezier3(int n);

	SFBezier3(SFValuenf a, SFValuenf b, SFValuenf c, SFValuenf d);

	virtual ~SFBezier3();

	void set(SFValuenf a, SFValuenf b, SFValuenf c, SFValuenf d);

	void getDev2Dt(float t, SFValuenf* read);

	void getDevDt(float t, SFValuenf* read);

	void getVertex(float t, SFValuenf* read);

	SFBezier3* clone();
};

} /* namespace sf */
#endif /* SFBEZIER3_H_ */
