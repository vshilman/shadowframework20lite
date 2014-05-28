#ifndef shadow_image_bitmaps_data_H_
#define shadow_image_bitmaps_data_H_

#include "shadow/image/SFBitmap.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.objects.SFShort.h"

abstract class SFBitmapData extends SFGraphicsAsset<SFBitmap> {

	SFShort width=new SFShort((short)0);
	SFShort height=new SFShort((short)0);

	SFBitmapData() {
		super();
	}

	SFShort getWidth(){
		return width;
	}

	SFShort getHeight(){
		return height;
	}

	void setHeight(int height){
		this->height.setShortValue((short)height);
	}

	void setWidth(int height){
		width.setShortValue((short)height);
	}
}
;
}
#endif
