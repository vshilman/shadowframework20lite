#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/curves/SFLine.h"
#include "shadow/geometry/curves/SFSpline.h"
#include "shadow/geometry/curves/data/SFSplineData.h"

namespace sf{
class SFPolygonalSplineData : public SFSplineData {

	
	SFSpline* buildResource() {
		SFSpline* bezier2=new SFSpline(0);
		int size=0;
		if(addValues.size()>0){
			size=addValues.get(0).getSize();
		}
			size=vertices.getResource().getSize();
		}
		bezier2.setCurve(new SFLine(size));
		updateResource(bezier2);
		return bezier2;
	}

};

}
#endif
