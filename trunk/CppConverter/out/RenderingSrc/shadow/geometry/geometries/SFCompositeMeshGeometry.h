#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "java/util/ArrayList.h"

#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/system/SFException.h"

class SFCompositeMeshGeometry extends SFMeshGeometry{

	ArrayList<SFMeshGeometry> geometries=new ArrayList<SFMeshGeometry>();

//	SFCompositeMeshGeometry() {
//		super();
	}

//	void addGeometry(SFMeshGeometry geometry){
//		geometries.add(geometry);
	}

//	void addMeshGeometry(SFMeshGeometry geometry) throws SFException {
//		this->geometries.add(geometry);
	}

	
//	void compile() {

//		ArrayList<SFPrimitiveBlock> writingBlock=new ArrayList<SFPrimitiveBlock>();

//		for (int i = 0; i < geometries.size(); i++) {
//			if(geometries.get(i).getArray()==null){
//				geometries.get(i).compile();
			}
		}

//		int firstElement=geometries.get(0).getFirstElement();
//		int lastElement=geometries.get(0).getLastElement();

//		for (int i = 0; i < geometries.size(); i++) {
//			for (int j = 0; j < geometries.get(i).getPrimitive().getBlocks().length; j++) {
//				SFPrimitiveBlock block=geometries.get(i).getPrimitive().getBlocks()[j];
//				if(writingBlock.contains(block))
//					throw new SFException("SFCompositeMesh Trying to write "+block+" Two Times");
			}
//			System.err.println(geometries.get(i).getFirstElement()+" "+geometries.get(i).getLastElement());
//			if(firstElement!=geometries.get(i).getFirstElement() || lastElement!=geometries.get(i).getLastElement() )
//				throw new SFException("SfCompositeMesh applied to geometries with a different number of elements");
		}

//		SFPrimitiveArray[] arrays=new SFPrimitiveArray[geometries.size()];

//		for (int i = 0; i < geometries.size(); i++) {
//			arrays[i]=geometries.get(i).getArray();
		}

//		//TODO : this is bad.
//		SFPrimitiveArray array=SFPipeline.getSfPipelineMemory().getMixArray(arrays,getPrimitive());

//		setPrimitive(array.getPrimitive());

//		//getMesh().setArray(array);
//		SFIndexRange[] ranges=getMesh().getPrimitiveDataRanges();

//		int geometryIndex=0;
//		int inGeometryIndex=0;
//		for (int gridIndex = 0; gridIndex < ranges.length; gridIndex++) {
//			ranges[gridIndex]=geometries.get(geometryIndex).getMesh().getPrimitiveDataRanges()[inGeometryIndex];
//			inGeometryIndex++;
//			if(inGeometryIndex>=geometries.get(geometryIndex).getPrimitive().getGridsCount()){
//				geometryIndex++;
//				inGeometryIndex=0;
			}
		}

//		setFirstElement(firstElement);
//		setLastElement(lastElement);
	}


	
//	void update() {
//		super.update();

//		for (int i = 0; i < geometries.size(); i++) {
//			geometries.get(i).update();
		}
	}
}
;
}
#endif
