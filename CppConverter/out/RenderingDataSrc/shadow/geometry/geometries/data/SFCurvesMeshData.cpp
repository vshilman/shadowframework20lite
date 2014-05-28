#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/geometries.SFCurvesMesh.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"
#include "shadow/system/data.objects.SFShortArray.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFCurvesMeshData extends SFGraphicsAsset<SFCurvesMesh> {

	SFShort verticesCount=new SFShort((short)0);
	AssetList<SFMeshCurve> meshcurves=new SFDataAssetList<SFMeshCurve>();
	Array> polygons=new SFDataObjectsList<SFShortArray>(new SFShortArray(new short[0]));
	SFString primitive=new SFString();

	SFCurvesMeshData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("verticesCount", verticesCount);
		parameters.addObject("meshcurves", meshcurves);
		parameters.addObject("polygons", polygons);
		setData(parameters);
	}

	
	SFCurvesMesh buildResource() {

		SFCurvesMesh mesh=new SFCurvesMesh();

		updateResource(mesh);


		return mesh;
	}

	
	void updateResource(SFCurvesMesh mesh) {

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

		short[][] polygons=new short[this->polygons.size()][];
		for (int i = 0; i < polygons.length; i++) {
			polygons[i]=this->polygons.get(i).getShortValues();
		}
		mesh.setPolygons(polygons);

	}
}
;
}
#endif
