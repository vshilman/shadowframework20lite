#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFDerivedTextCoordData extends SFGraphicsAsset<SFMeshGeometry>{

	SFLibraryReference<SFDerivedTexCoordFunctionuv> function=new SFLibraryReference<SFDerivedTexCoordFunctionuv>();
	SFString primitive=new SFString();
	SFLibraryReference<SFMeshGeometry> geometry=new SFLibraryReference<SFMeshGeometry>();

	SFDerivedTextCoordData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("function", function);
		parameters.addObject("geometry", geometry);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFMeshGeometry buildResource() {
		final SFDerivedTexCoord derivedTextCoord=new SFDerivedTexCoord();

		updateResource(derivedTextCoord);

		return derivedTextCoord;
	}

	
	void updateResource(SFMeshGeometry resource) {

		final SFDerivedTexCoord derivedTextCoord=(SFDerivedTexCoord)resource;

		derivedTextCoord.setPrimitive(SFPipeline.getPrimitive(primitive.getString()));

		derivedTextCoord.setDerivedTexCoordFunction(function.getResource());

		derivedTextCoord.addMeshGeometry(geometry.getResource());
	}
}
;
}
#endif
