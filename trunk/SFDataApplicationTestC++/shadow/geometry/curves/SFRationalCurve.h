/*
 * SFRationalCurve.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFRATIONALCURVE_H_
#define SFRATIONALCURVE_H_

#include "SFGenericCurve.h"
#include "shadow/math/SFVertex4f.h"

namespace sf {

class SFRationalCurve: SFGenericCurve {

	SFCurve* curve;
	SFResource resource;

	float vertices_length;
	float* weights;
	SFValuenf** vertices;
	SFValuenf* sampleVertex;

public:
	SFRationalCurve(SFCurve* curve,int size);

	virtual ~SFRationalCurve();

	void setSize(int size);

	void setWeights(float* weights,short weights_length);

	void setControlPoint(int index, SFValuenf* vertex);

	int getControlPointSize();

	SFValuenf* getControlPoint(int index);

	void compileCurve();

	SFResource* getResource();

	SFValuenf* generateValue();

	float getTMax();

	float getTMin();

	void getVertex(float t, SFValuenf* read);
};

} /* namespace sf */
#endif /* SFRATIONALCURVE_H_ */
