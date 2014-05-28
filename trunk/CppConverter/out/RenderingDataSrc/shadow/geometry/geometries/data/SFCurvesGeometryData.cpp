#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/geometries.SFCurvesGeometry.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFCurvesGeometryData extends SFGraphicsAsset<SFCurvesGeometry> {

	SFShort verticesCount=new SFShort((short)0);
	AssetList<SFMeshCurve> meshcurves=new SFDataAssetList<SFMeshCurve>();
	SFString primitive=new SFString();

	SFCurvesGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("verticesCount", verticesCount);
		parameters.addObject("meshcurves", meshcurves);
		setData(parameters);
	}

	
	SFCurvesGeometry buildResource() {

		SFCurvesGeometry mesh=new SFCurvesGeometry();

		updateResource(mesh);

		return mesh;
	}

	
	void updateResource(SFCurvesGeometry mesh) {

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		mesh.setPrimitive(primitive);

		mesh.setVerticesCount(verticesCount.getShortValue());
		final SFMeshCurve[] curves=new SFMeshCurve[this->meshcurves.size()];
		for (int i = 0; i < curves.length; i++) {
			//final int index=i;
			curves[i]=this->meshcurves.get(i).getResource();
			getDataAssetResource().setResource(i, this->meshcurves.get(i).getDataAssetResource());
		}

		mesh.setCurves(curves);
	}
}
;
}
#endif
