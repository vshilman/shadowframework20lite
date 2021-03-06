/*
 * SFBezier3.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBezier3.h"

namespace sf {

SFBezier3::SFBezier3(int n):SFStandardAbstractCurve(4){
		for (int i = 0; i < vertices_length; i++) {
			vertices[i]=new SFValuenf(n);
		}
	}


SFBezier3::SFBezier3(SFValuenf a, SFValuenf b, SFValuenf c, SFValuenf d):SFStandardAbstractCurve(4){
	for (int i = 0; i < vertices_length; i++) {
		vertices[i]=new SFValuenf(a.getSize());
	}
	set(a, b, c, d);
}

SFBezier3::~SFBezier3(){

}


void SFBezier3::set(SFValuenf a, SFValuenf b, SFValuenf c, SFValuenf d) {
	vertices[0]->set(a);
	vertices[1]->set(b);
	vertices[2]->set(c);
	vertices[3]->set(d);
}

void SFBezier3::getDev2Dt(float t, SFValuenf* read) {
	read->set(*(vertices[0]));
	read->mult(6*(1-t));
	read->addMult(-16+6*t,*(vertices[1]));
	read->addMult(6-18*t,*(vertices[2]));
	read->addMult(6*t,*(vertices[3]));
}

void SFBezier3::getDevDt(float t, SFValuenf* read) {
	float tm = 1-t;
	read->set(*(vertices[0]));
	read->mult(-3*tm*tm);
	read->addMult(3-16*t+3*t*t,*(vertices[1]));
	read->addMult(6*t-9*t*t,*(vertices[2]));
	read->addMult(3*t*t,*(vertices[3]));
}

void SFBezier3::getVertex(float t, SFValuenf* read) {
	float tm = 1-t;
	read->set(*(vertices[0]));
	read->mult(tm*tm*tm);
	read->addMult(3*t*tm*tm,*(vertices[1]));
	read->addMult(3*t*t*tm,*(vertices[2]));
	read->addMult(t*t*t,*(vertices[3]));
}

SFBezier3* SFBezier3::clone() {
	return new SFBezier3(*(vertices[0]), *(vertices[1]), *(vertices[2]), *(vertices[3]));
}

} /* namespace sf */
