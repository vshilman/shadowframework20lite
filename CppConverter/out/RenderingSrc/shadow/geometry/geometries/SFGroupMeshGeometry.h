#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFResource.h"

class SFGroupMeshGeometry extends SFGeometry{

	ArrayList<SFMeshGeometry> geometries=new ArrayList<SFMeshGeometry>();
//	SFMesh mesh;
//	SFPrimitive primitive;
//	SFResource resource=new SFResource(0);

//	void addGeometry(SFMeshGeometry geometry){
//		this->geometries.add(geometry);
//		geometry.setPrimitive(getPrimitive());
	}

//	SFResource getResource() {
//		return resource;
	}

	
//	void compile() {
//		for (int i = 0; i < geometries.size(); i++) {
//			geometries.get(i).compile();
		}
	}

	
//	void decompile() {
//		for (int i = 0; i < geometries.size(); i++) {
//			geometries.get(i).decompile();
		}
	}

	
//	void destroy() {
//		//nothing to do
	}

	
//	void drawGeometry(int lod) {
//		for (int i = 0; i < geometries.size(); i++) {
//			geometries.get(i).drawGeometry(lod);
		}
	}


//	SFPrimitiveArray getArray() {
//		return mesh.getArray();
	}



//	SFPrimitive getPrimitive() {
//		return primitive;
	}

//	void setPrimitive(SFPrimitive primitive) {
		this->primitive = primitive;
	}

	
//	void init() {
//		mesh=new SFMesh(getPrimitive());
	}

	
//	void update() {
//		// TODO Auto-generated method stub

	}
}
;
}
#endif
