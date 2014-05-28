#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/curves.data.SFCurvesListKeeper.h"
#include "shadow/geometry/geometries.SFParametrizedGeometry.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortByteField.h"
#include "shadow/system/data.objects.SFString.h"
#include "shadow/tmp/SFQuadsGridGeometry.h"

namespace sf{
class SFQuadsSurfaceFunctionsGeometryData extends SFGraphicsAsset<SFGeometry>{

	SFShortByteField NuNv=new SFShortByteField((short)0);
	SFDataObjectsList<SFString> blocks=new SFDataObjectsList<SFString>(new SFString());
	SFLibraryReferenceList<SFSurfaceFunction> surfaceFunctions=new SFLibraryReferenceList<SFSurfaceFunction>(new SFLibraryReference<SFSurfaceFunction>(null));

	SFString primitive=new SFString();


	SFQuadsSurfaceFunctionsGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("NuNv", NuNv);
		parameters.addObject("blocks", blocks);
		parameters.addObject("surfaceFunctions", surfaceFunctions);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	
	SFParametrizedGeometry buildResource() {

		SFParametrizedGeometry surfaceGeometry=new SFParametrizedGeometry();

		updateResource(surfaceGeometry);

		return surfaceGeometry;
	}

	
	void updateResource(SFGeometry resource) {


		SFParametrizedGeometry surfaceGeometry=(SFParametrizedGeometry)resource;

		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry();
		gridGeometry.getQuadsGrid().setNu( NuNv.getByte(0));
		gridGeometry.getQuadsGrid().setNv( NuNv.getByte(1));

		//surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
		surfaceGeometry.setParametersGeometry(gridGeometry);

		SFPrimitive primitive=SFPipeline.getPrimitive(this->primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		gridGeometry.setPrimitive(primitive.getConstructionPrimitive());

		for (int i = 0; i < this->surfaceFunctions.size(); i++) {
			SFPrimitiveBlock block=SFPrimitiveBlock.valueOf(this->blocks.get(i).getString());
			SFCurvesListKeeper.getKeeper().selectRegister(block);
			surfaceGeometry.setFunction(block, this->surfaceFunctions.get(i).getResource(), SFPrimitiveBlock.POSITION);
		}

//		surfaceGeometry.setMainGeometryFunction(surfaceFunction.getResource());
//		SFSurfaceFunction function=texCoordFunction.getResource();
//		if(function!=null)
//			surfaceGeometry.setTexCoordGeometry(function);
//
//		getDataAssetResource().setResource(1, surfaceFunction.getDataset().getDataAssetResource());

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


	SFString getPrimitive() {
		return primitive;
	}

}
;
}
#endif
