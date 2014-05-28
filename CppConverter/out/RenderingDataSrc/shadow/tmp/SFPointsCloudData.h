#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/renderer/SfPrimitiveArrayResource.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/renderer/data.SFPrimitiveArrayData.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"
#include "shadow/tmp/SFPointsCloud.h"

namespace sf{
class SFPointsCloudData extends SFGraphicsAsset<SFGeometry> {

	ArrayResource> primitiveData=new SFLibraryReference<SfPrimitiveArrayResource>(null);
	SFShort firstPoint=new SFShort((short)0);
	SFShort pointsSize=new SFShort((short)0);

	SFPointsCloudData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitiveData", primitiveData);
		parameters.addObject("firstPoint", firstPoint);
		parameters.addObject("pointsSize", pointsSize);
		setData(parameters);
	}

	
	SFGeometry buildResource() {
		final SFPointsCloud geometry=new SFPointsCloud();
		updateResource(geometry);
		return geometry;
	}

	
	void updateResource(SFGeometry resource) {
		final SFPointsCloud geometry=(SFPointsCloud)resource;

		SFPrimitiveArray array=primitiveData.getResource().getArray();
		geometry.setArray(array);
		geometry.setPrimitive(array.getPrimitive());
		geometry.setFirstPoint(firstPoint.getShortValue());
		geometry.setPointsSize(pointsSize.getShortValue());

	}

	void setupGeometry(SFPrimitiveArray array,String primitive,int firstPoint,int pointsSize){
		SFPrimitiveArrayData sfPrimitiveArrayData=new SFPrimitiveArrayData();
		sfPrimitiveArrayData.setPrimitive(primitive);
		sfPrimitiveArrayData.setArray(array);
		this->primitiveData.setDataset(sfPrimitiveArrayData);
		this->firstPoint.setShortValue((short)firstPoint);
		this->pointsSize.setShortValue((short)pointsSize);
	}

	void setupGeometry(String array,int firstElement,int pointsSize){
		this->primitiveData.setReference(array);
		this->firstPoint.setShortValue((short)firstElement);
		this->pointsSize.setShortValue((short)pointsSize);
	}
}
;
}
#endif
