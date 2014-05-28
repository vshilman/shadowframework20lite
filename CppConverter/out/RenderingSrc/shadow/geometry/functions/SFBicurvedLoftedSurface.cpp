///*
//	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
//    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

//    This file is part of The Shadow Framework.

//    The Shadow Framework is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.

//    The Shadow Framework is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.

//    You should have received a copy of the GNU General Public License
//    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
//*/
#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/math/SFVertex3f.h"

class SFBicurvedLoftedSurface  extends SFGenericSurfaceFunction{

	A;
//	SFCurve B;

	A=1;
//	float maxTB=1;

//	SFBicurvedLoftedSurface() {

	}

//	SFBicurvedLoftedSurface(SFCurve a, SFCurve b) {
//		super();
//		A = a;
//		B = b;
//		maxTA=A.getTMax();
//		maxTB=B.getTMax();
	}


//	SFVertex3f tmp=new SFVertex3f();


	
//	float getX(float u, float v) {
//		A.getVertex(u*maxTA, tmp);
//		SFVertex3f tmp2=new SFVertex3f();
//		B.getVertex(u*maxTB, tmp2);
//		tmp.mult(1-v);
//		tmp.addMult(v, tmp2);
//		return tmp.getX();
	}

	
//	float getY(float u, float v) {
//		return tmp.getY();
	}

	
//	float getZ(float u, float v) {
//		return tmp.getZ();
	}

//	SFCurve getA() {
//		return A;
	}

//	void setA(SFCurve a) {
//		A = a;

	}

//	SFCurve getB() {
//		return B;

	}

//	void setB(SFCurve b) {
//		B = b;
	}

//	float getMaxTA() {
//		return maxTA;
	}

//	void setMaxTA(float maxTA) {
		this->maxTA = maxTA;
	}

//	float getMaxTB() {
//		return maxTB;
	}

//	void setMaxTB(float maxTB) {
		this->maxTB = maxTB;
	}



}
;
}
#endif
