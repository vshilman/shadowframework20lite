#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBasisSpline3.h"
#include "shadow/geometry/curves.SFRationalCurve.h"

class SFRBasisSpline3Data extends SFRBasisSplineData{

	
	SFCurve buildResource() {
		int size=vertices.getResource().getSize();
		SFBasisSpline3 spline=new SFBasisSpline3(super.getClosed(),size);
		SFRationalCurve rational=new SFRationalCurve(spline, size);
		updateResource(rational);
		return rational;
	}

}
;
}
#endif
