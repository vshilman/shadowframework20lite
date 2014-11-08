#ifndef shadow_geometry_curves_data_SFBasisSpline3Data_H_
#define shadow_geometry_curves_data_SFBasisSpline3Data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBasisSpline3.h"
#include "shadow/geometry/curves/data/SFBasisSplineData.h"

namespace sf{

class SFBasisSpline3Data :public SFBasisSplineData {
	
	SFCurve* buildResource() {
		SFBasisSpline3* spline=new SFBasisSpline3(getClosed(),0);
		updateResource(spline);
		return spline;
	}

};

}
#endif
