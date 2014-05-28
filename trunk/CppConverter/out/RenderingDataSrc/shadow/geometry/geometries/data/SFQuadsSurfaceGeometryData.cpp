#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/geometries.SFQuadsSurfaceGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortByteField.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFQuadsSurfaceGeometryData extends SFGraphicsAsset<SFGeometry>{

	SFShortByteField NuNv=new SFShortByteField((short)0);
	SFLibraryReference<SFSurfaceFunction> surfaceFunction=new SFLibraryReference<SFSurfaceFunction>();
	SFLibraryReference<SFSurfaceFunction> texCoordFunction=new SFLibraryReference<SFSurfaceFunction>();
	SFString primitive=new SFString();

	SFQuadsSurfaceGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("NuNv", NuNv);
		parameters.addObject("surfaceFunction", surfaceFunction);
		parameters.addObject("texCoordFunction", texCoordFunction);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFQuadsSurfaceGeometry buildResource() {

		SFQuadsSurfaceGeometry surfaceGeometry=new SFQuadsSurfaceGeometry();

		updateResource(surfaceGeometry);

		return surfaceGeometry;
	}

	
	void updateResource(SFGeometry resource) {

		SFQuadsSurfaceGeometry surfaceGeometry=(SFQuadsSurfaceGeometry)resource;

		surfaceGeometry.getQuadsGrid().setNu( NuNv.getByte(0));
		surfaceGeometry.getQuadsGrid().setNv( NuNv.getByte(1));

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		surfaceGeometry.setPrimitive(primitive);

		surfaceGeometry.setMainGeometryFunction(surfaceFunction.getResource());
		SFSurfaceFunction function=texCoordFunction.getResource();
		if(function!=null)
			surfaceGeometry.setTexCoordGeometry(function);

		//getDataAssetResource().setResource(1, surfaceFunction.getDataset().getDataAssetResource());

	}

	void setNu(int nu){
		NuNv.setByte(1, nu);
	}

	void setNv(int nv){
		NuNv.setByte(0, nv);
	}

	int getNu() {
		return NuNv.getByte(1);
	}

	int getNv() {
		return NuNv.getByte(0);
	}

	void setNuNv(int nu,int nv){
		setNu(nu);
		setNv(nv);
	}

	SFLibraryReference<SFSurfaceFunction> getSurfaceFunction() {
		return surfaceFunction;
	}

	SFLibraryReference<SFSurfaceFunction> getTexCoordFunction() {
		return texCoordFunction;
	}

	SFString getPrimitive() {
		return primitive;
	}


	void setup(String function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceFunction> textCoordData, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setString(primitive);
	}

	void setup(SFDataAsset<SFSurfaceFunction> function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceFunction> textCoordData, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setDataset(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setString(primitive);
	}


	void setup(String function, int Nu, int Nv, String texCoordFunction,String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getTexCoordFunction().setReference(texCoordFunction);
		getPrimitive().setString(primitive);
	}

	void setup(String function, int Nu, int Nv, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getPrimitive().setString(primitive);
	}

	void setup(SFDataAsset<SFSurfaceFunction> function, int Nu, int Nv, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setDataset(function);
		getPrimitive().setString(primitive);
	}
}
;
}
#endif
