#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/curves.data.SFCurvesListKeeper.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFMeshCurveData extends SFDataAsset<SFMeshCurve>{

	SFShort side=new SFShort((short)0);
	SFShort v1=new SFShort((short)0);
	SFShort v2=new SFShort((short)0);
	SFLibraryReferenceList<SFCurve> curves=new SFLibraryReferenceList<SFCurve>(new SFLibraryReference<SFCurve>());

	SFMeshCurveData() {

		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("side", side);
		parameters.addObject("v1", v1);
		parameters.addObject("v2", v2);
		parameters.addObject("curves", curves);
		setData(parameters);
	}

	
	SFMeshCurve buildResource() {

		final SFMeshCurve meshCurve=new SFMeshCurve();

		updateResource(meshCurve);

		return meshCurve;
	}


	
	void updateResource(SFMeshCurve meshCurve) {
		meshCurve.setSide(side.getShortValue());
		meshCurve.getVertices()[0]=v1.getShortValue();
		meshCurve.getVertices()[1]=v2.getShortValue();


		int size=curves.size();
		final SFCurve[] curves=new SFCurve[size];
		for (int i = 0; i < size; i++) {
			final int index=i;
			SFCurvesListKeeper.getKeeper().selectBlock(i);
			curves[index]=this->curves.get(i).getResource();

			getDataAssetResource().setResource(i, this->curves.get(i).getDataset().getDataAssetResource());
		}

		meshCurve.setCurve(curves);
	}
}
;
}
#endif
