#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/curves.SFCurvesList.h"
#include "shadow/geometry/curves.data.SFCurvesListKeeper.h"
#include "shadow/geometry/geometries.SFGroupMeshGeometry.h"
#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFGroupCurvesGeometryData extends SFGraphicsAsset<SFGeometry>{

	SFDataObjectsList<SFString> blocks = new SFDataObjectsList<SFString>(new SFString());
	SFLibraryReferenceList<SFCurvesList> curves = new SFLibraryReferenceList<SFCurvesList>(new SFLibraryReference<SFCurvesList>());
	SFDataAssetList<SFMeshGeometry> geometries = new SFDataAssetList<SFMeshGeometry>();
	SFString primitive=new SFString();

	SFGroupCurvesGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("blocks", blocks);
		parameters.addObject("curves", curves);
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
		for (int i = 0; i < this->curves.size(); i++) {
			SFPrimitiveBlock block=SFPrimitiveBlock.valueOf(this->blocks.get(i).getString());
			SFCurvesListKeeper.getKeeper().registerList(block,
					curves.get(i).getResource());
			SFCurvesListKeeper.getKeeper().setBlock(i, block);
		}

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
