#ifndef shadow_geometry_functions_data_SFTwoCurvesFunctionData_H_
#define shadow_geometry_functions_data_SFTwoCurvesFunctionData_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves/data/SFCurvesDataKeeperSurface.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{

class SFTwoCurvesFunctionData : public SFDataAsset<SFSurfaceFunction> , public SFCurvesDataKeeperSurface{

	SFLibraryReference<SFCurve>* firstCurve;
	SFLibraryReference<SFCurve>* secondCurve;

	vector<SFCurve*> addingCurve;

public:
	SFTwoCurvesFunctionData();

	~SFTwoCurvesFunctionData();
	
	void addBuildingCurve(SFCurve* curve);

	SFLibraryReference<SFCurve>* getFirstCurve();

	SFLibraryReference<SFCurve>* getSecondCurve();

};

}
#endif
