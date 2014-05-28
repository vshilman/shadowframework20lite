#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves.SFNCurve.h"
#include "shadow/geometry/geometries.SFCurvesMesh.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"
#include "shadow/system/data.objects.SFShortArray.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFPNTxOCurvesMeshData extends SFGraphicsAsset<SFCurvesMesh> {

	static class PNCurveDataobject extends SFDataAsset<SFMeshCurve>{

		static SFLibraryReference<SFValuesList<SFValuenf>> normals;
		static SFLibraryReference<SFValuesList<SFValuenf>> positions;
		static SFLibraryReference<SFValuesList<SFValuenf>> texCoords;

		SFShort index1=new SFShort((short)0);
		SFShort index2=new SFShort((short)1);
		SFShort side=new SFShort((short)1);
		AssetObject<SFCurve> curveVertices=new SFDataAssetObject<SFCurve>(null);
		AssetObject<SFCurve> curveTxo=new SFDataAssetObject<SFCurve>(null);

		PNCurveDataobject() {
			super();
			SFNamedParametersObject parameters=new SFNamedParametersObject();
			parameters.addObject("index1", index1);
			parameters.addObject("index2", index2);
			parameters.addObject("side", side);
			parameters.addObject("curveVertices", curveVertices);
			parameters.addObject("curveTxo", curveTxo);
			setData(parameters);
		}

		
		SFMeshCurve buildResource() {	

			SFMeshCurve meshCurve=new SFMeshCurve();
			updateResource(meshCurve);

			return meshCurve;
		}

		AssetObject<SFCurve> curve,
				SFLibraryReference<SFValuesList<SFValuenf>> mainValues){
			SFCurve curve_=curve.getResource();

			ArrayList<SFValuenf> list=new ArrayList<SFValuenf>();
			for (int i = 0; i < curve_.getControlPointSize(); i++) {
				list.add(curve_.getControlPoint(i));
			}

			SFVertex3f vertex=new SFVertex3f();
			mainValues.getResource().getValue(index1.getShortValue(), vertex);

			curve_.setControlPoint(0, vertex);
			for (int i = 1; i < list.size()-1; i++) {
				curve_.setControlPoint(i, list.get(i));
			}
			vertex=new SFVertex3f();
			mainValues.getResource().getValue(index2.getShortValue(), vertex);
			curve_.setControlPoint(list.size()-1, vertex);

			return curve_;
		}

		
		void updateResource(SFMeshCurve meshCurve) {
			meshCurve.setSide(side.getShortValue());

			meshCurve.getVertices()[0]=index1.getShortValue();
			meshCurve.getVertices()[1]=index2.getShortValue();

			final SFCurve[] curves=new SFCurve[3];
			final SFNCurve nCurve=new SFNCurve();
			curves[1]=nCurve;

			curves[0]=getCurve(curveVertices,positions);
			curves[2]=getCurve(curveTxo,texCoords);
			nCurve.setP(curves[0]);

			SFValuesList<SFValuenf> values=normals.getResource();

			SFVertex3f normal1=new SFVertex3f();
			SFVertex3f normal2=new SFVertex3f();
			values.getValue(index1.getShortValue(), normal1);
			values.getValue(index2.getShortValue(), normal2);
			nCurve.setFirstNormal(normal1);
			nCurve.setSecondNormal(normal2);

			meshCurve.setCurve(curves);
		}
	}


	SFLibraryReference<SFValuesList<SFValuenf>> normals=new SFLibraryReference<SFValuesList<SFValuenf>>();
	SFLibraryReference<SFValuesList<SFValuenf>> positions=new SFLibraryReference<SFValuesList<SFValuenf>>();
	SFLibraryReference<SFValuesList<SFValuenf>> texCoords=new SFLibraryReference<SFValuesList<SFValuenf>>();

	AssetList<SFMeshCurve> curves=new SFDataAssetList<SFMeshCurve>();//will be PNCurveDataobject;

	Array> polygons=new SFDataObjectsList<SFShortArray>(new SFShortArray(new short[0]));

	SFString primitive=new SFString();


	SFPNTxOCurvesMeshData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("normals", normals);
		parameters.addObject("positions", positions);
		parameters.addObject("texCoords", texCoords);
		parameters.addObject("curves", curves);
		parameters.addObject("polygons", polygons);
		setData(parameters);
	}

	PNCurveDataobject generatCurveData(){
		PNCurveDataobject curve=new PNCurveDataobject();
		curves.add(curve);
		return curve;
	}

	
	SFCurvesMesh buildResource() {

		SFCurvesMesh mesh=new SFCurvesMesh();

		updateResource(mesh);

		return mesh;
	}

	
	void updateResource(SFCurvesMesh mesh) {

		PNCurveDataobject.normals=normals;
		PNCurveDataobject.positions=positions;
		PNCurveDataobject.texCoords=texCoords;

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		mesh.setPrimitive(primitive);

		mesh.setVerticesCount(positions.getResource().getSize());
		final SFMeshCurve[] curves=new SFMeshCurve[this->curves.size()];
		for (int i = 0; i < curves.length; i++) {
			//final int index=i;
			curves[i]=this->curves.get(i).getResource();
			getDataAssetResource().setResource(i, this->curves.get(i).getDataAssetResource());
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
