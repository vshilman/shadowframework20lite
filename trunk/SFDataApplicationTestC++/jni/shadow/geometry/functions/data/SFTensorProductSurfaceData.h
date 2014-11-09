#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves/data/SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/functions/SFTensorProductSurface.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFLibraryReferenceList.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{
class SFTensorProductSurfaceData : public SFDataAsset<SFSurfaceFunction> , public SFCurvesDataKeeperSurface{

	SFLibraryReferenceList<SFCurve>* guideLines;
	SFLibraryReference<SFCurve>* productCurve;
	vector<SFCurve*> curves;

public:
	SFTensorProductSurfaceData();

	~SFTensorProductSurfaceData();
	
	void addBuildingCurve(SFCurve* curve);

	
	SFSurfaceFunction* buildResource();

	
	void updateResource(SFSurfaceFunction* resource);

};

}
#endif
