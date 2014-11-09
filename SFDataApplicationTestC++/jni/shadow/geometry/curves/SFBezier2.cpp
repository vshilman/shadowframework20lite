/*
 * SFBezier2.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFBezier2.h"

namespace sf {
	SFBezier2::SFBezier2(int n):SFStandardAbstractCurve(3){
		for (int i = 0; i < vertices_length; i++) {
			vertices[i]=new SFValuenf(n);
		}
	}

	SFBezier2::~SFBezier2(){

	}

	SFBezier2::SFBezier2(SFValuenf* a, SFValuenf* b, SFValuenf* c):SFStandardAbstractCurve(3){
		vertices[0] = a;
		vertices[1] = b;
		vertices[2] = c;
	}

	void SFBezier2::getDev2Dt(float t, SFValuenf* read) {
		read->set(*(vertices[0]));
		read->mult(2);
		read->addMult(-4,*(vertices[1]));
		read->addMult(2,*(vertices[2]));
	}

	void SFBezier2::getDevDt(float t, SFValuenf* read) {
		read->set(*(vertices[0]));
		read->mult(-2*(1-t));
		read->addMult(2-4*t,*(vertices[1]));
		read->addMult(2*t,*(vertices[2]));
	}

	void SFBezier2::getVertex(float t, SFValuenf* read) {
		float tm = 1-t;
		read->set(*(vertices[0]));
		read->mult(tm*tm);
		read->addMult(2*t*tm,*(vertices[1]));
		read->addMult(t*t,*(vertices[2]));
	}

	SFBezier2* SFBezier2::clone() {
		return new SFBezier2(vertices[0], vertices[1], vertices[2]);
	}

} /* namespace sf */
