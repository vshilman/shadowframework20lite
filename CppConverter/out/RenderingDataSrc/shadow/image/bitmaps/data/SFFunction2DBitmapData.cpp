#ifndef shadow_image_bitmaps_data_H_
#define shadow_image_bitmaps_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/image/SFBitmap.h"
#include "shadow/image/SFSurfaceFunctionBitmap.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

class SFFunction2DBitmapData extends SFBitmapData{

	SFLibraryReference<SFSurfaceFunction> function=new SFLibraryReference<SFSurfaceFunction>(); 

	SFFunction2DBitmapData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("function", function);
		setData(parameters);
	}


	
	SFBitmap buildResource() {
		final SFSurfaceFunctionBitmap bitmap=new SFSurfaceFunctionBitmap(width.getShortValue(), height.getShortValue(), false, null);
		bitmap.setFunction(function.getResource());
		return bitmap;
	}

	
	void updateResource(SFBitmap resource) {

		final SFSurfaceFunctionBitmap bitmap=(SFSurfaceFunctionBitmap)resource;
		bitmap.setWidth(width.getShortValue());
		bitmap.setHeight(height.getShortValue());
		bitmap.setFunction(function.getResource());

		getDataAssetResource().setResource(1, function.getDataset().getDataAssetResource());
	}
}
;
}
#endif
