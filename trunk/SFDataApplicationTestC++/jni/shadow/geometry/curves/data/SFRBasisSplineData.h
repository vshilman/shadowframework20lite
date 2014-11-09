#ifndef shadow_geometry_curves_data_SFRBasisSplineData_H_
#define shadow_geometry_curves_data_SFRBasisSplineData_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShort.h"

namespace sf{
class SFRBasisSplineData : public SFRationalSplineData , public SFValuesDataKeeperCurve{

	SFShort closed=new SFShort((short)0);

	vector<SFValuenf> addValues;

	SFRBasisSplineData() {
		build();
	}

	
	void addBuildingValue(SFValuenf value) {
		addValues.push_back(value);
	}

	void build() {
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( vertices);
		parameters->addObject( weights);
		parameters->addObject( closed);
		setData(parameters);
	}

	bool getClosed(){
		return this->closed.getShortValue()==1;
	}
};

}
#endif
