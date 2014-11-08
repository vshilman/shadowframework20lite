#ifndef shadow_geometry_curves_data_SFRBasisSpline3Data_H_
#define shadow_geometry_curves_data_SFRBasisSpline3Data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBasisSpline3.h"
#include "shadow/geometry/curves/SFRationalCurve.h"
#include "shadow/geometry/curves/data/SFRBasisSplineData.h"

namespace sf{

class SFRBasisSpline3Data : public SFRBasisSplineData{

	
	SFCurve* buildResource() {
		int size=vertices->getResource()->getSize();
		SFBasisSpline3* spline=new SFBasisSpline3(getClosed(),size);
		SFRationalCurve* rational=new SFRationalCurve(spline, size);
		updateResource(rational);
		return rational;
	}

};

}
#endif
