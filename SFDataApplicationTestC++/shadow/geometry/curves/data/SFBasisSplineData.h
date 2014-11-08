#ifndef shadow_geometry_curves_data_SFBasisSplineData_H_
#define shadow_geometry_curves_data_SFBasisSplineData_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves/SFBasisspline.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/geometry/curves/data/SFCurvesVerticesData.h"

namespace sf{

class SFBasisSplineData : public SFCurvesVerticesData{

public:
	void updateResource(SFCurve* resource) {
		SFBasisSpline* spline=(SFBasisSpline*)resource;

		int size=0;
		if(addValues.size()>0){
			size=addValues.size();
			spline->setSize(size);
			for (int i = 0; i < size; i++) {
				spline->setControlPoint(i, *addValues.at(i));
			}
		}else{
			size=vertices->getResource()->getSize();
			spline->setSize(size);
			int vertexSize=vertices->getResource().getValueSize();
			for (int i = 0; i < size; i++) {
				SFValuenf vertex=new SFValuenf(vertexSize);
				vertices->getResource().getValue(i, vertex);
				spline->setControlPoint(i, vertex);
			}
		}

		spline->compileCurve();
	}
};

}
#endif

