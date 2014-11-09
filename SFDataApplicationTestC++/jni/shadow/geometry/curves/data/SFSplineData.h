#ifndef shadow_geometry_curves_data_SFSplineData_H_
#define shadow_geometry_curves_data_SFSplineData_H_

#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves/SFspline.h"
#include "shadow/geometry/curves/data/SFValuesDataKeeperCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{

class SFSplineData : public SFDataAsset<SFSpline> , public SFValuesDataKeeperCurve{

	SFLibraryReference<SFValuesList>* vertices;
	vector<SFValuenf> addValues;

public:
	SFSplineData(){
		build();
	}
	
	void addBuildingValue(SFValuenf value) {
		addValues.push_back(value);
	}

	void build() {
		vertices=new SFLibraryReference<SFValuesList>()
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( vertices);
		setData(parameters);
	}

	
	void updateResource(SFSpline* spline) {

		//SFValuesIterator<SFValuenf> iterator=vertices.getResource().getIterator();
		int size=0;
		if(addValues.size()>0){
			size=addValues.size();

			spline->setSize(size);
			for (int i = 0; i < size; i++) {
				spline->setControlPoint(i, &(addValues[i]));
			}
		}else{
			size=vertices->getResource()->getSize();

			spline->setSize(size);
			int vertexSize=vertices->getResource()->getValueSize();
			for (int i = 0; i < size; i++) {
				SFValuenf* vertex=new SFValuenf(vertexSize);
				vertices->getResource()->getValue(i, vertex);
				spline->setControlPoint(i, vertex);
			}
		}

		spline->compileCurve();

	}
};

}
#endif
