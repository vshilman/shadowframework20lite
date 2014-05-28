#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/curves.SFBezier3.h"
#include "shadow/geometry/curves.SFSpline.h"

class SFBezier3SplineData extends SFSplineData {

	
	SFSpline buildResource() {
		SFSpline bezier3=new SFSpline(0);
		bezier3.setCurve(new SFBezier3(vertices.getResource().getSize()));
		updateResource(bezier3);
		return bezier3;
	}

}
;
}
#endif
