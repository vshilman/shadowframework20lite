#ifndef shadow_geometry_curves_data_SFBasisSpline1Data_H_
#define shadow_geometry_curves_data_SFBasisSpline1Data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBasisSpline1.h"
#include "shadow/geometry/curves/data/SFBasisSplineData.h"

namespace sf{

class SFBasisSpline1Data : public SFBasisSplineData {
	
	SFCurve* buildResource() {
		SFBasisSpline1* spline=new SFBasisSpline1(SFBasisSplineData::getClosed(),0);
		updateResource(spline);
		return spline;
	}

};

}
#endif
