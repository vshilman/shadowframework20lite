#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/geometries.SFCompositeMeshGeometry.h"
#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFCompositeMeshGeometryData extends SFGraphicsAsset<SFMeshGeometry>{

	SFLibraryReferenceList<SFMeshGeometry> geometries=
		new SFLibraryReferenceList<SFMeshGeometry>(new SFLibraryReference<SFMeshGeometry>());
	SFString primitive=new SFString();

	SFCompositeMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("geometries", geometries);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	void setPrimitive(String primitive) {
		this->primitive.setString(primitive);
	}

	void addGeometry(String geometry) {
		this->geometries.add(geometry);
	}

	
	SFMeshGeometry buildResource() {
		final SFCompositeMeshGeometry compositeMeshGeometry=new SFCompositeMeshGeometry();
		updateResource(compositeMeshGeometry);

		return compositeMeshGeometry;
	}

	
	void updateResource(SFMeshGeometry resource) {
		final SFCompositeMeshGeometry compositeMeshGeometry=(SFCompositeMeshGeometry)resource;
		compositeMeshGeometry.setPrimitive(SFPipeline.getPrimitive(this->primitive.getString()));
		for (int i = 0; i < geometries.size(); i++) {
			compositeMeshGeometry.addGeometry(geometries.get(i).getResource());
		}
	}
}
;
}
#endif
