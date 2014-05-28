#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/geometry/data.SFFixedFloatUnit8.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

namespace sf{
class SFUnit8Transform2DData extends SFDataAsset<SFTransformResource>{

	SFBinaryObject<SFFixedFloatUnit8> x=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	SFBinaryObject<SFFixedFloatUnit8> y=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	SFBinaryObject<SFFixedFloatUnit8> rot=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(0));
	SFBinaryObject<SFFixedFloatUnit8> scale=new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8(1));

	SFUnit8Transform2DData(){
		setup();
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("x", x);
		parameters.addObject("y", y);
		parameters.addObject("rot", rot);
		parameters.addObject("scale", scale);
		setData(parameters);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=SFMatrix3f.getRotationY(rot.getBinaryValue().getFloat()*(float)(2*Math.PI));
		matrix.mult(scale.getBinaryValue().getFloat());

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(x.getBinaryValue().getFloat(),y.getBinaryValue().getFloat(),0);

		return transform;
	}


	
	void updateResource(SFTransformResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
