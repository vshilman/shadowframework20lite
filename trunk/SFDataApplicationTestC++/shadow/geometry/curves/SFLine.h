/*
 * SFLine.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFLINE_H_
#define SFLINE_H_

#include "SFStandardAbstractCurve.h"

namespace sf {

class SFLine :public SFStandardAbstractCurve{

public:

	SFLine(SFValuenf a, SFValuenf b);

	virtual ~SFLine();

	SFLine(int size);

	void getDev2Dt(float t, SFValuenf* read);

	void getDevDt(float t, SFValuenf* read);

	void getVertex(float t, SFValuenf* read);

	SFLine* clone();

};

} /* namespace sf */
#endif /* SFLINE_H_ */
