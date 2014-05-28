#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/geometry/geometries.SFParametrizedGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFParametrizedSurfaceGeometryData  extends SFGraphicsAsset<SFGeometry>{

	SFLibraryReference<SFSurfaceFunction> surfaceFunction=new SFLibraryReference<SFSurfaceFunction>();
	SFLibraryReference<SFSurfaceFunction> texCoordFunction=new SFLibraryReference<SFSurfaceFunction>();
	SFLibraryReference<SFMeshGeometry> uvGeometry=new SFLibraryReference<SFMeshGeometry>();
	SFString primitive=new SFString();


	SFParametrizedSurfaceGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("uvGeometry", uvGeometry);
		parameters.addObject("surfaceFunction", surfaceFunction);
		parameters.addObject("texCoordFunction", texCoordFunction);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFParametrizedGeometry buildResource() {

		SFParametrizedGeometry surfaceGeometry=new SFParametrizedGeometry();

		updateResource(surfaceGeometry);


		return surfaceGeometry;
	}


	
	void updateResource(SFGeometry resource) {

		SFParametrizedGeometry surfaceGeometry=( SFParametrizedGeometry)resource;

		surfaceGeometry.addMeshGeometry(uvGeometry.getResource());

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		surfaceGeometry.setPrimitive(primitive);

		surfaceGeometry.setMainGeometryFunction(surfaceFunction.getResource());
		SFSurfaceFunction function=texCoordFunction.getResource();
		if(function!=null)
			surfaceGeometry.setTexCoordGeometry(function);

	}
}
;
}
#endif
