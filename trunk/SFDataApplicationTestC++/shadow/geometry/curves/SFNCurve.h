/*
 * SFNCurve.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFNCURVE_H_
#define SFNCURVE_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

namespace sf {

class SFNCurve : public SFCurve{

	SFCurve* P;
	SFVertex3f firstNormal;
	SFVertex3f secondNormal;
	SFResource resource;

public:

	SFNCurve();

	virtual ~SFNCurve();

	void compileCurve();

	SFValuenf* getControlPoint(int index);

	int getControlPointSize();

	void setControlPoint(int index, SFValuenf* vertex);

	SFResource* getResource() ;

	void getVertex(float t, SFValuenf* read);

	SFValuenf* generateValue();

	float getTMax();

	float getTMin();

	SFCurve* getP();

	void setP(SFCurve* p);

	SFVertex3f getFirstNormal();

	void setFirstNormal(SFVertex3f secondNormal);

	SFVertex3f getSecondNormal();

	void setSecondNormal(SFVertex3f secondNormal);

};

} /* namespace sf */
#endif /* SFNCURVE_H_ */
