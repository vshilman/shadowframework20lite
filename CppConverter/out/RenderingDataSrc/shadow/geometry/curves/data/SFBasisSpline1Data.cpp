#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBasisSpline1.h"

class SFBasisSpline1Data extends SFBasisSplineData {

	
	SFCurve buildResource() {
		SFBasisSpline1 spline=new SFBasisSpline1(super.getClosed(),0);
		updateResource(spline);
		return spline;
	}


}
;
}
#endif
