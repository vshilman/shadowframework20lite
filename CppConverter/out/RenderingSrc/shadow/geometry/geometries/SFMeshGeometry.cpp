#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

///**
// * Responsibility: draw.
// * 
// * @author Alessandro Martinelli
// */
class SFMeshGeometry  extends SFGeometry implements SFGraphicsResource{

//	protected SFPrimitive primitive;
//	SFMesh mesh ;
//	int compiledIndex=-1;

//	SFResource resource=new SFResource(0);

//	SFMeshGeometry() {
//		super();
	}

//	SFResource getResource() {
//		return resource;
	}

//	SFMeshGeometry(SFMesh mesh) {
//		super();
		this->mesh=mesh;
		this->primitive=mesh.getPrimitive();
	}

//	void setPrimitive(SFPrimitive primitive) {
		this->primitive=primitive;
	}

	
//	SFPrimitive getPrimitive() {
//		return primitive;
	}

	
//	void drawGeometry(int lod) {
//		//lod is still ignored
//		//this is much ok...

//		if(mesh!=null){
//			SFPipeline.getSfPipelineGraphics().setLod(lod);
////			if(compiledIndex!=-1)
////				SFPipeline.getSfPipelineGraphics().drawCompiledPrimitives(getArray(), compiledIndex);
////			else if(mesh.getFirstElement()!=-1)
//				SFPipeline.getSfPipelineGraphics().drawPrimitives(mesh.getArray(),mesh.getFirstElement(),mesh.getLastElement()-mesh.getFirstElement());
		}
	}

	
//	void compile() {
	}

	
//	void update() {
//		//Do nothing
	}


	
//	void decompile() {
//		//destroy mesh
//		mesh.destroy();
//		mesh=null;
	}

	
//	void init() {

//		if(mesh==null){

//			mesh=new SFMesh(this->primitive);
//			mesh.init();

		}

//		if(mesh.getFirstElement()==-1){
//			setFirstElement(getArray().getElementsCount());
//			int[] rangePositions=new int[getPrimitive().getGridsCount()];
//			for (int i = 0; i < rangePositions.length; i++) {
//				rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
			}

//			compile();

//			setLastElement(getArray().getElementsCount());
//			SFIndexRange[] ranges=getMesh().getPrimitiveDataRanges();
//			for (int i = 0; i < rangePositions.length; i++) {
//				int rangePosition=getArray().getPrimitiveData(i).getElementsCount();
//				ranges[i]=new SFIndexRange(rangePositions[i], rangePosition-rangePositions[i]);
			}

//			update();

//			compiledIndex=SFPipeline.getSfPipelineGraphics().compilePrimitiveArray(
//					getArray(),getFirstElement(), getLastElement());

		}
//			try {
////				int[] rangePositions=new int[getPrimitive().getGridsCount()];
////				for (int i = 0; i < rangePositions.length; i++) {
////					rangePositions[i]=getArray().getPrimitiveData(i).getElementsCount();
}
//				//setLastElement(getArray().getElementsCount());
//				SFIndexRange[] ranges=getMesh().getPrimitiveDataRanges();
//				for (int i = 0; i < ranges.length; i++) {
//					int rangePosition=getArray().getPrimitiveData(i).getElementsCount();
//					ranges[i]=new SFIndexRange(0, rangePosition);
				}
			}
//				// TODO Auto-generated catch block
//				e.printStackTrace();
			}

		}
	}

//	boolean isOn2D(float x,float y){
//		return getArray().isOn2D(getMesh().getFirstElement(), getMesh().getLastElement(), x, y);
	}

	
//	void destroy() {
//		if(mesh.getFirstElement()!=-1){
//			decompile();	
		}
	}

//	protected SFMeshGeometry(SFPrimitive primitive) {
//		super();
//		if(primitive==null)
//			throw new RuntimeException("ex");
		this->primitive=primitive;
	}


//	protected int getCompiledIndex() {
//		return compiledIndex;
	}

//	SFMesh getMesh() {
//		return mesh;
	}

//	int getFirstElement() {
//		return mesh.getFirstElement();
	}

//	int getElementsCount() {
//		return mesh.getLastElement()-mesh.getFirstElement();
	}


//	void setFirstElement(int firstElement) {
//		this->mesh.setFirstElement(firstElement);
	}


//	int getLastElement() {
//		return mesh.getLastElement();
	}


//	void setLastElement(int lastElement) {
//		this->mesh.setLastElement(lastElement);
	}

//	SFPrimitiveArray getArray() {
//		return mesh.getArray();
	}


}
;
}
#endif
