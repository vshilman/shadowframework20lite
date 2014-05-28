#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBasisSpline3.h"

class SFBasisSpline3Data extends SFBasisSplineData {

	
	SFCurve buildResource() {
		SFBasisSpline3 spline=new SFBasisSpline3(super.getClosed(),0);
		updateResource(spline);
		return spline;
	}

}
;
}
#endif
