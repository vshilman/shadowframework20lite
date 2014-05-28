#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/data.SFFixedFloat16.h"
#include "shadow/geometry/functions.SFRectangle2DFunction.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

namespace sf{
class SFRectangle2DFunctionData extends SFDataAsset<SFSurfaceFunction>{

	SFBinaryObject<SFFixedFloat16> x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16> y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16> w=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFBinaryObject<SFFixedFloat16> h=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());

	SFRectangle2DFunctionData() {
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("x", x);
		parametersObject.addObject("y", y);
		parametersObject.addObject("w", w);
		parametersObject.addObject("h", h);
		setData(parametersObject);
	}

	SFRectangle2DFunctionData(float x,float y,float w,float h) {
		//setData(data);
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("x", this->x);
		parametersObject.addObject("y", this->y);
		parametersObject.addObject("w", this->w);
		parametersObject.addObject("h", this->h);
		setData(parametersObject);
		this->x.getBinaryValue().setFloat(x);
		this->y.getBinaryValue().setFloat(y);
		this->w.getBinaryValue().setFloat(w);
		this->h.getBinaryValue().setFloat(h);
	}

	
	SFRectangle2DFunction buildResource() {
		SFRectangle2DFunction function=new SFRectangle2DFunction(
				x.getBinaryValue().getFloat(),
				y.getBinaryValue().getFloat(),
				w.getBinaryValue().getFloat(),
				h.getBinaryValue().getFloat()
		);
		return function;
	}

	
	void updateResource(SFSurfaceFunction resource) {
		SFRectangle2DFunction function=(SFRectangle2DFunction)resource;
		function.setX(x.getBinaryValue().getFloat());
		function.setY(y.getBinaryValue().getFloat());
		function.setW(w.getBinaryValue().getFloat());
		function.setH(h.getBinaryValue().getFloat());
	}
}
;
}
#endif
