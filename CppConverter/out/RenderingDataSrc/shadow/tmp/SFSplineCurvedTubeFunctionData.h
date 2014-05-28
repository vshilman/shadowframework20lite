#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/tmp/SFSplineCurvedTubeFunction.h"

class SFSplineCurvedTubeFunctionData extends
SFDataAsset<SFSurfaceFunction> {

	SFLibraryReferenceList<SFCurve> curvesData = new SFLibraryReferenceList<SFCurve>(new SFLibraryReference<SFCurve>());

	SFSplineCurvedTubeFunctionData() {
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("curvesData", curvesData);
		setData(parametersObject);
	}

	
	SFSurfaceFunction buildResource() {
		SFSplineCurvedTubeFunction function = new SFSplineCurvedTubeFunction();

		updateResource(function);

		return function;
	}

	
	void updateResource(SFSurfaceFunction resource) {

		SFSplineCurvedTubeFunction function = (SFSplineCurvedTubeFunction)resource;

		for (int i = 0; i < curvesData.size(); i++) {
			function.getCurves().add(curvesData.get(i).getResource());
		}

	}

	void addCurve(SFDataAsset<SFCurve> curve) {
		SFLibraryReference<SFCurve> reference=new SFLibraryReference<SFCurve>();
		reference.setDataset(curve);
		curvesData.add(reference);
	}

}
;
}
#endif
