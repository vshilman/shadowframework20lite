#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBasisSpline2.h"
#include "shadow/geometry/curves.SFRationalCurve.h"

class SFRBasisSpline2Data extends SFRBasisSplineData{

	
	SFCurve buildResource() {
		int size=vertices.getResource().getSize();
		SFBasisSpline2 spline=new SFBasisSpline2(super.getClosed(),size);
		SFRationalCurve rational=new SFRationalCurve(spline, size);
		updateResource(rational);
		return rational;
	}


//	SFSpline spline=new SFSpline(vertices.getResource().getSize());
//	spline.setCurve(new SFBezier2(vertices.getResource().getValueSize()));
//	SFRationalCurve rational=new SFRationalCurve(spline, 0);
//	updateResource(rational);
//	return rational;
}
;
}
#endif
