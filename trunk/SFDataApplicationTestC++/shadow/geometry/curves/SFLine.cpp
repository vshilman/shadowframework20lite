/*
 * SFLine.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFLine.h"

namespace sf {


SFLine::SFLine(SFValuenf a, SFValuenf b):SFStandardAbstractCurve(2) {
	vertices[0] = new SFValuenf(a.getSize());
	vertices[0]->set(a);
	vertices[1] = new SFValuenf(b.getSize());
	vertices[1]->set(b);
}

SFLine::~SFLine(){

}

SFLine::SFLine(int size):SFStandardAbstractCurve(2) {
	vertices[0] = new SFValuenf(size);
	vertices[1] = new SFValuenf(size);
}

void SFLine::getDev2Dt(float t, SFValuenf* read) {
	float* data = read->getV();
	for (int i = 0; i < read->getSize(); i++) {
		data[i]=0;
	}
}

void SFLine::getDevDt(float t, SFValuenf* read) {
	read->set(*(vertices[1]));
	read->addMult(-1,*(vertices[0]));
}

void SFLine::getVertex(float t, SFValuenf* read) {
	//System.err.println("line "+vertices[0].getV()+" "+vertices[1].getV());
	read->set(*(vertices[0]));
	read->mult(1-t);
	read->addMult(t,*(vertices[1]));
}

SFLine* SFLine::clone() {
	return new SFLine(*(vertices[0]), *(vertices[1]));
}

} /* namespace sf */
