
#include "SFNoTransformData.h"

namespace sf{

	SFNoTransformData::SFNoTransformData() {
		setData(new SFNamedParametersObject());
	}

	SFNoTransformData* SFNoTransformData::generateNewDatasetInstance() {
		return this;
	}

	SFTransformResource* SFNoTransformData::buildResource() {
		return &voidTransform;
	}


	void SFNoTransformData::updateResource(SFTransformResource* resource) {
		// TODO Auto-generated method stub

	}
}
