#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "java/util/Arrays.h"

#include "shadow/geometry/data.SFFixedFloat16.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

namespace sf{
class SFTranslateFixed16Data extends SFDataAsset<SFTransformResource>{

	SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16> z=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());

	SFTranslateFixed16Data(){
		setup();
	}

	SFTranslateFixed16Data(float x,float y,float z){
		setup();
		place(x, y, z);
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("z", z);
		setData(parameters);
	}

	void place(float x,float y,float z){
		this->x.getBinaryValue().setFloat(x);
		this->y.getBinaryValue().setFloat(y);
		this->z.getBinaryValue().setFloat(z);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),z.getBinaryValue().getFloat());

		return transform;
	}


	
	void updateResource(SFTransformResource resource) {
		SFTransformResource transform=(SFTransformResource)resource;
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),z.getBinaryValue().getFloat());
		System.err.println(Arrays.toString(transform.getV()));
	}
}
;
}
#endif
