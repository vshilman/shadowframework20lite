/*
 * SFGenericCurve.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFGenericCurve.h"

namespace sf {


SFGenericCurve::SFGenericCurve(){
	approximation=DEFAULT_APPROXIMATION;
}

void SFGenericCurve::getDevDt(float t, SFValuenf* read) {
	SFValuenf* value1=new SFValuenf(read->getSize());
	SFValuenf* value2=new SFValuenf(read->getSize());
	getVertex((float)(t+approximation), value1);
	getVertex((float)(t-approximation), value2);
	read->set(*value1);
	read->subtract(*value2);
	read->mult((float)(1.0/(2*approximation)));
}

void SFGenericCurve::getDev2Dt(float t, SFValuenf* read) {
	SFValuenf* value1=new SFValuenf(read->getSize());
	SFValuenf* value2=new SFValuenf(read->getSize());
	SFValuenf* value3=new SFValuenf(read->getSize());
	getVertex((float)(t+approximation), value1);
	getVertex((float)(t-approximation), value2);
	getVertex((float)(t), value3);
	read->set(*value1);
	read->addMult(-2, *value2);
	read->add(*value3);
	read->mult((float)(1.0/(approximation*approximation)));
}


} /* namespace sf */
