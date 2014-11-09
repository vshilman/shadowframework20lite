#ifndef shadow_geometry_curves_data_SFRBezierSplineData_H_
#define shadow_geometry_curves_data_SFRBezierSplineData_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{
class SFRBezierSplineData : public SFRationalSplineData , public SFValuesDataKeeperCurve{

	vector<SFValuenf*> addValues;

public:
	SFRBezierSplineData() {
		build();
	}

	
	void addBuildingValue(SFValuenf* value) {
		addValues.push_back(value);
	}

	void build() {
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( vertices);
		parameters->addObject( weights);
		setData(parameters);
	}

};

}
#endif
