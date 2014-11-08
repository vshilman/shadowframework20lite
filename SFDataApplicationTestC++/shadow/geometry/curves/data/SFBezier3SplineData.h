#ifndef shadow_geometry_curves_data_SFBezier3SplineData_H_
#define shadow_geometry_curves_data_SFBezier3SplineData_H_

#include "shadow/geometry/curves/SFBezier3.h"
#include "shadow/geometry/curves/SFSpline.h"
#include "shadow/geometry/curves/data/SFSplineData.h"

namespace sf{

class SFBezier3SplineData : public SFSplineData {

	
	SFSpline* buildResource() {
		SFSpline* bezier3=new SFSpline(0);
		bezier3->setCurve(new SFBezier3(vertices.getResource().getSize()));
		updateResource(bezier3);
		return bezier3;
	}

};

}
#endif
