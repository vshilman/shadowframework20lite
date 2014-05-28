#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/image/bitmaps.data.SFBitmapData.h"
#include "shadow/system/data.objects.SFBinaryFloatArrayObject.h"

abstract class SFPerlineNoiseData extends SFBitmapData{

	SFBinaryFloatArrayObject weights=new SFBinaryFloatArrayObject(1,250);

	SFBinaryFloatArrayObject getWeights(){
		return weights;
	}

	void setWeights(float... weights){
		this->weights.setValues(weights);
	}

}
;
}
#endif
