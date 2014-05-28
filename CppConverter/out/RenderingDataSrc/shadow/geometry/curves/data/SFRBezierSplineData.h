#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/ArrayList.h"

#include "shadow/math/SFValuenf.h"
#include "shadow/system/data.SFNamedParametersObject.h"

abstract class SFRBezierSplineData extends SFRationalSplineData implements SFValuesDataKeeperCurve{

	ArrayList<SFValuenf> addValues=new ArrayList<SFValuenf>();

	SFRBezierSplineData() {
		build();
	}

	
	void addBuildingValue(SFValuenf value) {
		addValues.add(value);
	}

	void build() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("weights", weights);
		setData(parameters);
	}

}
;
}
#endif
