#ifndef shadow_image_data_H_
#define shadow_image_data_H_

#include "shadow/image/SFBitmap.h"
#include "shadow/image/SFBitmapTexture.h"
#include "shadow/image/SFPipelineTexture.h"
#include "shadow/image/SFRenderedTexturesSet.h"
#include "shadow/image/SFPipelineTexture.Filter.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFEnumObject.h"

namespace sf{
class SFBitmapTextureData extends SFGraphicsAsset<SFRenderedTexturesSet> {

	AssetObject<SFBitmap> bitmap=new SFDataAssetObject<SFBitmap>(null);
	String[] filtersNames={"NEAREST",
			"LINEAR",
			}
	SFEnumObject<SFPipelineTexture.Filter> filter=new SFEnumObject<SFPipelineTexture.Filter>(SFPipelineTexture.Filter.values(),filtersNames);
	String[] wrapModeNames={"REPEAT",
			"MIRRORED_REPEAT",
			}
	SFEnumObject<SFPipelineTexture.WrapMode> wrapMode=new SFEnumObject<SFPipelineTexture.WrapMode>(SFPipelineTexture.WrapMode.values(),wrapModeNames);

	SFBitmapTextureData() {
		filter.setIndex(2);//old default value
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("bitmap", bitmap);
		parameters.addObject("filter", filter);
		parameters.addObject("wrapMode", wrapMode);
		setData(parameters);
	}

	void setBitmap(SFDataAsset<SFBitmap> bitmap) {
		this->bitmap.setDataset(bitmap);
	}

	SFDataset getBitmap() {
		return this->bitmap.getDataset();
	}

	
	SFDataset generateNewDatasetInstance() {
		return new SFBitmapTextureData();
	}

	
	SFRenderedTexturesSet buildResource() {
		SFBitmapTexture bitmapTexture=new SFBitmapTexture(bitmap.getResource());
		bitmapTexture.setFilter(filter.getElement());
		bitmapTexture.setWrapMode(wrapMode.getElement());
		return bitmapTexture;
	}

	
	void updateResource(SFRenderedTexturesSet resource) {

		SFBitmapTexture bitmapTexture=(SFBitmapTexture)resource;

		getDataAssetResource().setResource(1, (((SFDataAsset<?>)bitmap.getDataset()).getDataAssetResource()));

		bitmapTexture.setFilter(filter.getElement());
		bitmapTexture.setWrapMode(wrapMode.getElement());
		bitmapTexture.setBitmap(bitmap.getResource());
	}
}
;
}
#endif
