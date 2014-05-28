#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves.data.SFCurvesDataKeeperSurface.h"
#include "shadow/geometry/functions.SFTensorProductSurface.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFTensorProductSurfaceData extends SFDataAsset<SFSurfaceFunction> implements SFCurvesDataKeeperSurface{

	SFLibraryReferenceList<SFCurve> guideLines=new SFLibraryReferenceList<SFCurve>(new SFLibraryReference<SFCurve>());
	SFLibraryReference<SFCurve> productCurve=new SFLibraryReference<SFCurve>();
	ArrayList<SFCurve> curves=new ArrayList<SFCurve>();

	SFTensorProductSurfaceData(){
		SFNamedParametersObject* namedParameters=new SFNamedParametersObject();
		namedParameters->addObject("guideLines", guideLines);
		namedParameters->addObject("productCurve", productCurve);
		setData(namedParameters);
	}

	
	void addBuildingCurve(SFCurve curve) {
		curves.add(curve);
	}

	
	SFSurfaceFunction buildResource() {
		final SFTensorProductSurface surface=new SFTensorProductSurface();

		updateResource(surface);

		return surface;
	}

	
	void updateResource(SFSurfaceFunction resource) {

		final SFTensorProductSurface surface=(SFTensorProductSurface)resource;

		int guideLineSize=this->guideLines.size();
		final SFCurve[] guideLines=(SFCurve[])(new SFCurve[guideLineSize+this->curves.size()]);
		for (int i = 0; i < guideLineSize; i++) {
			guideLines[i]=(this->guideLines.get(i).getResource());
		}

		for (int i = 0; i < curves.size(); i++) {
			guideLines[i+guideLineSize]=(this->curves.get(i));
		}

		surface.setProductCurve(productCurve.getResource());

		surface.setGuideLines(guideLines);
	}
}
;
}
#endif
