#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBezier3.h"
#include "shadow/geometry/curves.SFRationalCurve.h"
#include "shadow/geometry/curves.SFSpline.h"

class SFRBezierSpline3Data extends SFRBezierSplineData{

	
	SFCurve buildResource() {
		SFSpline spline=new SFSpline(vertices.getResource().getSize());
		spline.setCurve(new SFBezier3(vertices.getResource().getValueSize()));
		SFRationalCurve rational=new SFRationalCurve(spline, 0);
		updateResource(rational);
		return rational;
	}

}

;
}
#endif
