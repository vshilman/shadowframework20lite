#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves.data.SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/functions.SFBicurvedLoftedSurface.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFBicurvedLoftedSurfaceData extends SFDataAsset<SFSurfaceFunction> implements SFCurvesDataKeeperSurface{


	
	SFDataset generateNewDatasetInstance() {
		return new SFBicurvedLoftedSurfaceData();
	}

	SFLibraryReference<SFCurve> centralCurve=
			new SFLibraryReference<SFCurve>();
	SFLibraryReference<SFCurve> rayCurve=
		new SFLibraryReference<SFCurve>();

	ArrayList<SFCurve> addingCurve=new ArrayList<SFCurve>();

	SFBicurvedLoftedSurfaceData() {
		SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
		namedParameters->addObject("firstCurve", centralCurve);
		namedParameters->addObject("secondCurve", rayCurve);
		setData(namedParameters);
	}

	
	void addBuildingCurve(SFCurve curve) {
		addingCurve.add(curve);
	}


	SFLibraryReference<SFCurve> getFirstCurve(){
		return centralCurve;
	}

	SFLibraryReference<SFCurve> getSecondCurve(){
		return rayCurve;
	}


	
	SFBicurvedLoftedSurface buildResource() {
		SFBicurvedLoftedSurface loftCurve = new SFBicurvedLoftedSurface();
		updateResource(loftCurve);
		return loftCurve;
	}

	
	void updateResource(SFSurfaceFunction resource) {
		SFBicurvedLoftedSurface loftCurve=(SFBicurvedLoftedSurface)resource;

		if(addingCurve.size()==2){
			loftCurve.setA(addingCurve.get(0));
			loftCurve.setB(addingCurve.get(1));
		}
			loftCurve.setA(getFirstCurve().getResource());
			loftCurve.setB(getSecondCurve().getResource());
		}

	}
}
;
}
#endif
