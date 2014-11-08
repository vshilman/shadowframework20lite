#ifndef shadow_geometry_functions_data_SFGroupCurvesGeometryData_H_
#define shadow_geometry_functions_data_SFGroupCurvesGeometryData_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/curves/SFCurvesList.h"
#include "shadow/geometry/curves/data/SFCurvesListKeeper.h"
#include "shadow/geometry/geometries/SFGroupMeshGeometry.h"
#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/data/SFDataAssetList.h"
#include "shadow/system/data/SFDataObjectsList.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFLibraryReferenceList.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFString.h"

namespace sf{
class SFGroupCurvesGeometryData : public SFGraphicsAsset<SFGeometry>{

	SFDataObjectsList<SFString>* blocks = new SFDataObjectsList<SFString>(new SFString());
	SFLibraryReferenceList<SFCurvesList>* curves = new SFLibraryReferenceList<SFCurvesList>(new SFLibraryReference<SFCurvesList>());
	SFDataAssetList<SFMeshGeometry>* geometries = new SFDataAssetList<SFMeshGeometry>();
	SFString* primitive=new SFString();

public:
	SFGroupCurvesGeometryData();
	~SFGroupCurvesGeometryData();
	
	SFGeometry* buildResource();
	
	void updateResource(SFGeometry* resource);

};

}
#endif
