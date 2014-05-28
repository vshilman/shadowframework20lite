#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/geometry/data.SFFixedFloat16.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

namespace sf{
class SFTranslateAndScaleFixed16Data extends SFDataAsset<SFTransformResource>{

	SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	SFBinaryObject<SFFixedFloat16> z=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(0));
	SFBinaryObject<SFFixedFloat16> scale=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16(1));

	SFTranslateAndScaleFixed16Data(){
		setup();
	}

	SFTranslateAndScaleFixed16Data(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("z", z);
		parameters.addObject("scale", scale);
		setData(parameters);
	}

	void place(float x,float y,float z,float scale){
		this->x.getBinaryValue().setFloat(x);
		this->y.getBinaryValue().setFloat(y);
		this->z.getBinaryValue().setFloat(z);
		this->scale.getBinaryValue().setFloat(scale);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		matrix.mult(scale.getBinaryValue().getFloat());

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),z.getBinaryValue().getFloat());

		return transform;
	}


	
	void updateResource(SFTransformResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
