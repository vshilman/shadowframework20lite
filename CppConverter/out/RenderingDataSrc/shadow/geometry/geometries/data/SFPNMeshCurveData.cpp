#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves.SFNCurve.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFPNMeshCurveData extends SFDataAsset<SFMeshCurve>{

	SFShort side=new SFShort((short)0);
	SFShort v1=new SFShort((short)0);
	SFShort v2=new SFShort((short)0);
	SFLibraryReference<SFValuesList<SFValuenf>> normals=new SFLibraryReference<SFValuesList<SFValuenf>>();
	SFLibraryReference<SFCurve> curve=new SFLibraryReference<SFCurve>();

	SFPNMeshCurveData() {

		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("side", side);
		parameters.addObject("v1", v1);
		parameters.addObject("v2", v2);
		parameters.addObject("normals", normals);
		parameters.addObject("curve", curve);
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

		final SFCurve[] curves=new SFCurve[2];
		final SFNCurve nCurve=new SFNCurve();
		curves[1]=nCurve;

		curves[0]=this->curve.getResource();
		nCurve.setP(curves[0]);

		SFValuesList<SFValuenf> values=this->normals.getResource();

		if(values.getSize()>=2){
			SFVertex3f normal1=new SFVertex3f();
			SFVertex3f normal2=new SFVertex3f();
			values.getValue(0, normal1);
			values.getValue(1, normal2);
			nCurve.setFirstNormal(normal1);
			nCurve.setSecondNormal(normal2);
		}

		meshCurve.setCurve(curves);
	}
}
;
}
#endif
