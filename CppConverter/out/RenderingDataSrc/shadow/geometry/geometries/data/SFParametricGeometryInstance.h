#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/renderer/data.SFDataParametersSet.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFParametricGeometryInstance extends SFGraphicsAsset<SFGeometry>{

	SFLibraryReference<SFGeometry> parametricGeometry=new SFLibraryReference<SFGeometry>();
	SFDataParametersSet parameters=new SFDataParametersSet();

	SFParametricGeometryInstance() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("parameters", this->parameters);
		parameters.addObject("geometry", parametricGeometry);
		setData(parameters);
	}

	SFGeometry geometry;

	
	SFGeometry buildResource() {

		setUpdateMode(true);

		parameters.apply();

		geometry = parametricGeometry.getResource();

		setUpdateMode(false);

		return geometry;
	}

	
	void updateResource(SFGeometry resource) {
		//TODO

		buildResource(); //??
	}
}
;
}
#endif
