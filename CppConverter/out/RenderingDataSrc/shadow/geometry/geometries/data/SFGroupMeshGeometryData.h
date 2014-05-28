#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/geometries.SFGroupMeshGeometry.h"
#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFGroupMeshGeometryData extends SFGraphicsAsset<SFGeometry>{

	SFDataAssetList<SFMeshGeometry> geometries = new SFDataAssetList<SFMeshGeometry>();
	SFString primitive=new SFString();

	SFGroupMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("geometries", geometries);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFGeometry buildResource() {
		final SFGroupMeshGeometry geometry=new SFGroupMeshGeometry();
		updateResource(geometry);
		return geometry;
	}

	
	void updateResource(SFGeometry resource) {
		final SFGroupMeshGeometry geometry=(SFGroupMeshGeometry)resource;
		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		geometry.setPrimitive(primitive);
		for (int i = 0; i < geometries.size(); i++) {
			geometry.addGeometry(geometries.get(i).getResource());
		}
	}
}
;
}
#endif
