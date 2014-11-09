#ifndef shadow_geometry_functions_data_SFBicurvedLoftedSurfaceData_H_
#define shadow_geometry_functions_data_SFBicurvedLoftedSurfaceData_H_


#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves/data/SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/functions/SFBicurvedLoftedSurface.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFDataset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{

class SFBicurvedLoftedSurfaceData : public SFDataAsset<SFSurfaceFunction> , public SFCurvesDataKeeperSurface{

public:
	SFDataset* generateNewDatasetInstance();

	SFLibraryReference<SFCurve>* centralCurve;
	SFLibraryReference<SFCurve>* rayCurve;

	vector<SFCurve*> addingCurve;

	SFBicurvedLoftedSurfaceData();

	~SFBicurvedLoftedSurfaceData();
	
	void addBuildingCurve(SFCurve* curve);

	SFLibraryReference<SFCurve>* getFirstCurve();

	SFLibraryReference<SFCurve>* getSecondCurve();
	
	SFBicurvedLoftedSurface* buildResource();
	
	void updateResource(SFSurfaceFunction* resource);

};

}
#endif
