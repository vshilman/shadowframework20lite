#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBezier2.h"
#include "shadow/geometry/curves/SFRationalCurve.h"
#include "shadow/geometry/curves/SFSpline.h"

namespace sf{
class SFRBezierSpline2Data : public SFRBezierSplineData{

	SFCurve* buildResource() {
		SFSpline* spline=new SFSpline(vertices.getResource().getSize());
		spline.setCurve(new SFBezier2(vertices.getResource().getValueSize()));
		SFRationalCurve rational=new SFRationalCurve(spline, 0);
		updateResource(rational);
		return rational;
	}

};

}
#endif
