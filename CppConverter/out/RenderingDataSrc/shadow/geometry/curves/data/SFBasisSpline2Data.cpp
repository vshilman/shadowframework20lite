#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFBasisSpline2.h"

class SFBasisSpline2Data extends SFBasisSplineData{

	
	SFCurve buildResource() {
		SFBasisSpline2 spline=new SFBasisSpline2(super.getClosed(),0);
		updateResource(spline);
		return spline;
	}

}
;
}
#endif
