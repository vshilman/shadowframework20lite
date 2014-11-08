#ifndef shadow_geometry_geometries_SFCompositeMeshGeometry_H_
#define shadow_geometry_geometries_SFCompositeMeshGeometry_H_


#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"

namespace sf{

class SFCompositeMeshGeometry : public SFMeshGeometry{

	vector<SFMeshGeometry*> geometries;

	SFCompositeMeshGeometry():SFMeshGeometry() {

	}

	void addGeometry(SFMeshGeometry* geometry){
		geometries.push_back(geometry);
	}

	void addMeshGeometry(SFMeshGeometry* geometry){
		this->geometries.push_back(geometry);
	}

	
	void compile() {

		vector<SFPrimitiveBlock*> writingBlock;

		for (unsigned int i = 0; i < geometries.size(); i++) {
			if(geometries.at(i)->getArray()==0){
				geometries.at(i)->compile();
			}
		}

		int firstElement=geometries.at(0)->getFirstElement();
		int lastElement=geometries.at(0)->getLastElement();

//		for (unsigned int i = 0; i < geometries.size(); i++) {
//			for (unsigned int j = 0; j < geometries.at(i)->getPrimitive()->getBlocks().size(); j++) {
//				SFPrimitiveBlock block=*(geometries.at(i)->getPrimitive()->getBlocks()[j];
//				//if(writingBlock.contains(block))
//				//	throw new SFException("SFCompositeMesh Trying to write "+block+" Two Times");
//			}
//			//System.err.println(geometries.get(i).getFirstElement()+" "+geometries.get(i).getLastElement());
//			//if(firstElement!=geometries.get(i).getFirstElement() || lastElement!=geometries.get(i).getLastElement() )
//			//	throw new SFException("SfCompositeMesh applied to geometries with a different number of elements");
//		}

		SFPrimitiveArray** arrays=new SFPrimitiveArray*[geometries.size()];

		for (unsigned int i = 0; i < geometries.size(); i++) {
			arrays[i]=geometries.at(i)->getArray();
		}

		//TODO : this is bad.
		SFPrimitiveArray* array=SFPipeline::getSfPipelineMemory()->getMixArray(arrays,geometries.size(),getPrimitive());

  		setPrimitive(array->getPrimitive());

  		//getMesh().setArray(array);
  		SFIndexRange* ranges=getMesh().getPrimitiveDataRanges();

  		int geometryIndex=0;
  		int inGeometryIndex=0;
  		for (int gridIndex = 0; gridIndex < getPrimitive()->getGridsCount(); gridIndex++) {
  			ranges[gridIndex]=geometries.at(geometryIndex)->getMesh().getPrimitiveDataRanges()[inGeometryIndex];
  			inGeometryIndex++;
  			if(inGeometryIndex>=geometries.at(geometryIndex)->getPrimitive()->getGridsCount()){
  				geometryIndex++;
  				inGeometryIndex=0;
			}
		}

  		setFirstElement(firstElement);
  		setLastElement(lastElement);
	}


	
	void update() {
  		SFMeshGeometry::update();

  		for (unsigned int i = 0; i < geometries.size(); i++) {
  			geometries.at(i)->update();
		}
	}

};

}
#endif
