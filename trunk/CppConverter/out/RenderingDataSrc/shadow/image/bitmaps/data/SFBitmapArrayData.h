#ifndef shadow_image_bitmaps_data_H_
#define shadow_image_bitmaps_data_H_

#include "shadow/image/SFBitmap.h"
#include "shadow/image/SFImageFormat.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryArrayObject.h"
#include "shadow/system/data.objects.SFEnumObject.h"

namespace sf{
class SFBitmapArrayData extends SFBitmapData{

	static SFImageFormat[] bitmapFormats={
		SFImageFormat.RGB8,SFImageFormat.ARGB8,SFImageFormat.ARGB4,SFImageFormat.RGB565,SFImageFormat.GRAY8
	}
	static String[] bitmapFormatsName={
		"RGB8","ARGB8","ARGB4","RGB565","GRAY8"
	}

	ArrayObject bitmap=new SFBinaryArrayObject(1);
	SFEnumObject<SFImageFormat> type=new SFEnumObject<SFImageFormat>(bitmapFormats,bitmapFormatsName);

	SFBitmapArrayData(){
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("type", type);
		parameters.addObject("bitmap", bitmap);
		setData(parameters);
	}

	
	SFBitmap buildResource() {
		SFBitmap bitmap=new SFBitmap();
		updateResource(bitmap);
		return bitmap;
	}

	
	void updateResource(SFBitmap bitmap) {
		bitmap.generateBitmap(width.getShortValue(), height.getShortValue(), this->bitmap.getValues(), type.getElement());
	}
}
;
}
#endif
