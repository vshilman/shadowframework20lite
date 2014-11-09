/*
 * SFMeshCurve.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFMESHCURVE_H_
#define SFMESHCURVE_H_

#include "shadow/system/SFIResource.h"
#include "shadow/geometry/SFCurve.h"

namespace sf {

class SFMeshCurve {

	SFCurve** curve;
	short side;
	short vertices[2];
	SFResource resource;

public:

	SFCurve** getCurve();

	void setCurve(SFCurve** curve);

	int getSide();

	void setSide(short side);

	short* getVertices();

	void setVertices(short vertices[2]);

	SFResource getResource();

};

} /* namespace sf */
#endif /* SFMESHCURVE_H_ */
