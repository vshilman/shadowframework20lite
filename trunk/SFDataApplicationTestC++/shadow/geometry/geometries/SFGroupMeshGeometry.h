#ifndef shadow_geometry_geometries_SFGroupMeshGeometry_H_
#define shadow_geometry_geometries_SFGroupMeshGeometry_H_


#include "shadow/geometry/SFGeometry.h"
#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFResource.h"

namespace sf{

class SFGroupMeshGeometry : public SFGeometry{

	vector<SFMeshGeometry*> geometries;
  	SFMesh* mesh;
  	SFPrimitive* primitive;
  	SFResource resource;

public:
  	void addGeometry(SFMeshGeometry* geometry){
  		this->geometries.push_back(geometry);
  		geometry->setPrimitive(getPrimitive());
	}

  	SFResource* getResource() {
  		return &resource;
	}

	
  	void compile() {
  		for (unsigned int i = 0; i < geometries.size(); i++) {
  			geometries.at(i)->compile();
		}
	}

	
  	void decompile() {
  		for (unsigned int i = 0; i < geometries.size(); i++) {
  			geometries.at(i)->decompile();
		}
	}

	
  	void destroy() {
  		//nothing to do
	}

	
  	void drawGeometry(int lod) {
  		for (unsigned int i = 0; i < geometries.size(); i++) {
  			geometries.at(i)->drawGeometry(lod);
		}
	}


  	SFPrimitiveArray* getArray() {
  		return mesh->getArray();
	}



  	SFPrimitive* getPrimitive() {
  		return primitive;
	}

  	void setPrimitive(SFPrimitive* primitive) {
		this->primitive = primitive;
	}

	
  	void init() {
  		mesh=new SFMesh(getPrimitive());
	}

	
  	void update() {

	}

};

}
#endif
