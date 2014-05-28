#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/ArrayList.h"

#include "shadow/math/SFValuenf.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

abstract class SFRBasisSplineData extends SFRationalSplineData implements SFValuesDataKeeperCurve{

	SFShort closed=new SFShort((short)0);

	ArrayList<SFValuenf> addValues=new ArrayList<SFValuenf>();

	SFRBasisSplineData() {
		build();
	}

	
	void addBuildingValue(SFValuenf value) {
		addValues.add(value);
	}

	void build() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("weights", weights);
		parameters.addObject("closed", closed);
		setData(parameters);
	}

	boolgetClosed(){
		this->closed.getShortValue()==1;
	}
}
;
}
#endif
