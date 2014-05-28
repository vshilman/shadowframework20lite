#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves.data.SFCurvesDataKeeperSurface.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

abstract class SFTwoCurvesFunctionData extends
	SFDataAsset<SFSurfaceFunction>  implements SFCurvesDataKeeperSurface{

	SFLibraryReference<SFCurve> firstCurve=
		new SFLibraryReference<SFCurve>();
	SFLibraryReference<SFCurve> secondCurve=
		new SFLibraryReference<SFCurve>();

	ArrayList<SFCurve> addingCurve=new ArrayList<SFCurve>();

	SFTwoCurvesFunctionData() {
		SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
		namedParameters->addObject("firstCurve", firstCurve);
		namedParameters->addObject("secondCurve", secondCurve);
		setData(namedParameters);
	}

	
	void addBuildingCurve(SFCurve curve) {
		addingCurve.add(curve);
	}

	SFLibraryReference<SFCurve> getFirstCurve(){
		return firstCurve;
	}

	SFLibraryReference<SFCurve> getSecondCurve(){
		return secondCurve;
	}

	void setFirstCurve(SFDataAsset<SFCurve> data){
		firstCurve.setDataset(data);
	}

	void setSecondCurve(SFDataAsset<SFCurve> data){
		secondCurve.setDataset(data);
	}
}
;
}
#endif
