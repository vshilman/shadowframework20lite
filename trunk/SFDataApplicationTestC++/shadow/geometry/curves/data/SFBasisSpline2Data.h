#ifndef shadow_geometry_curves_data_SFBasisSpline2Data_H_
#define shadow_geometry_curves_data_SFBasisSpline2Data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBasisSpline2.h"
#include "shadow/geometry/curves/data/SFBasisSplineData.h"

namespace sf{

class SFBasisSpline2Data : public SFBasisSplineData{

	SFCurve* buildResource() {
		SFBasisSpline2* spline=new SFBasisSpline2(super.getClosed(),0);
		updateResource(spline);
		return spline;
	}

};

}
#endif
