#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/renderer/SfPrimitiveArrayResource.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/renderer/data.SFPrimitiveArrayData.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFMeshGeometryData extends SFGraphicsAsset<SFGeometry> {

	ArrayResource> primitivesArray=new SFLibraryReference<SfPrimitiveArrayResource>(null);
	SFShort firstElement=new SFShort((short)0);
	SFShort lastElement=new SFShort((short)0);

	SFMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitivesArray", primitivesArray);
		parameters.addObject("firstElement", firstElement);
		parameters.addObject("lastElement", lastElement);
		setData(parameters);
	}

	
	SFGeometry buildResource() {
		SFPrimitiveArray array=primitivesArray.getResource().getArray();
		SFMesh mesh=new SFMesh(array.getPrimitive(),array);
		final SFMeshGeometry geometry=new SFMeshGeometry(mesh);
		geometry.setFirstElement(firstElement.getShortValue());
		geometry.setLastElement(lastElement.getShortValue());
		return geometry;
	}

	
	void updateResource(SFGeometry resource) {
		//TODO (will you really need this?)
	}

	void setupGeometry(SfPrimitiveArrayResource array,String primitive,int firstElement,int lastElement){
		SFPrimitiveArrayData sfPrimitiveArrayData=new SFPrimitiveArrayData();
		sfPrimitiveArrayData.setPrimitive(primitive);
		sfPrimitiveArrayData.setArray(array.getArray());
		this->primitivesArray.setDataset(sfPrimitiveArrayData);
		this->firstElement.setShortValue((short)firstElement);
		this->lastElement.setShortValue((short)lastElement);
	}

	void setupGeometry(String array,int firstElement,int lastElement){
		this->primitivesArray.setReference(array);
		this->firstElement.setShortValue((short)firstElement);
		this->lastElement.setShortValue((short)lastElement);
	}
}
;
}
#endif
