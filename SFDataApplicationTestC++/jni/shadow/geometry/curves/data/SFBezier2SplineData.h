#ifndef shadow_geometry_curves_data_SFBezier2SplineData_H_
#define shadow_geometry_curves_data_SFBezier2SplineData_H_

#include "shadow/geometry/curves/SFBezier2.h"
#include "shadow/geometry/curves/SFSpline.h"
#include "shadow/geometry/curves/data/SFSplineData.h"

namespace sf{

class SFBezier2SplineData : public SFSplineData {

	
	SFSpline buildResource() {
		SFSpline bezier2=new SFSpline(0);
		int size=0;
		if(addValues.size()>0){
			size=addValues.get(0).getSize();
		}
			size=vertices.getResource().getSize();
		}
		bezier2.setCurve(new SFBezier2(size));
		updateResource(bezier2);
		return bezier2;
	}

};

}
#endif
