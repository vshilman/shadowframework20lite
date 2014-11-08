#ifndef shadow_geometry_curves_data_SFCurvesVerticesData_H_
#define shadow_geometry_curves_data_SFCurvesVerticesData_H_


#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShort.h"
#include "shadow/geometry/curves/data/SFValuesDataKeeperCurve.h"

namespace sf{

class SFCurvesVerticesData : public SFDataAsset<SFCurve> , public SFValuesDataKeeperCurve{

protected:
	SFLibraryReference<SFValuesList*>* vertices;
	SFShort* closed;

	vector<SFValuenf> addValues;

public:
	SFCurvesVerticesData() {
		vertices = new SFLibraryReference<SFValuesList*>();
		closed=new SFShort((short)0);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( vertices);
		parameters->addObject( closed);
		setData(parameters);
	}

	
	void addBuildingValue(SFValuenf value) {
		addValues.push_back(value);
	}

	bool getClosed(){
		return this->closed->getShortValue()==1;
	}

};

}
#endif
