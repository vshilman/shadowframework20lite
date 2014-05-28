#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.SFCurvesList.h"
#include "shadow/geometry/curves.SFValuenfList.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFVListCurvesListData extends SFDataAsset<SFCurvesList>{

	SFLibraryReference<SFValuenfList> values = new SFLibraryReference<SFValuenfList>();
	AssetList<SFCurve> curves=new SFDataAssetList<SFCurve>();

	SFVListCurvesListData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("values", values);
		parameters.addObject("curves", curves);
		setData(parameters);
	}

	
	SFCurvesList buildResource() {
		SFCurvesList curveList=new SFCurvesList(this->curves.size());
		updateResource(curveList);
		return curveList;
	}

	
	void updateResource(SFCurvesList curveList) {

		SFValuesListKeeper.getKeeper().setValuesList(values.getResource());

		for (int i = 0; i < this->curves.size(); i++) {
			curveList.getCurves()[i]=this->curves.get(i).getResource();
		}
	}

}
;
}
#endif
