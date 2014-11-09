#ifndef shadow_renderer_data_transforms_SFTranslateAndScaleFixed16Data_H_
#define shadow_renderer_data_transforms_SFTranslateAndScaleFixed16Data_H_

#include "shadow/geometry/data/SFFixedFloat16.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFBinaryObject.h"

namespace sf{
class SFTranslateAndScaleFixed16Data : public SFDataAsset<SFTransformResource>{

	SFBinaryObject<SFFixedFloat16>* x;
	SFBinaryObject<SFFixedFloat16>* y;
	SFBinaryObject<SFFixedFloat16>* z;
	SFBinaryObject<SFFixedFloat16>* scale;

public:
	SFTranslateAndScaleFixed16Data(){
		x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
		y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
		z=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
		scale=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(1));
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject(x);
		parameters->addObject(y);
		parameters->addObject(z);
		parameters->addObject( scale);
		setData(parameters);
	}

	SFTransformResource* buildResource() {
		SFMatrix3f* matrix=new SFMatrix3f();
		matrix->mult(scale->getBinaryValue()->getFloat());

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(x->getBinaryValue()->getFloat(),y->getBinaryValue()->getFloat(),z->getBinaryValue()->getFloat());

		return transform;
	}

	void updateResource(SFTransformResource* resource) {
		// TODO Auto-generated method stub

	}
};

}
#endif
