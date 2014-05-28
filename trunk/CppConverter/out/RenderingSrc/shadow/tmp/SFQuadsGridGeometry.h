#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/geometries.SFMeshGeometry.h"
#include "shadow/geometry/geometries.structures.SFQuadsGrid.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/operational/grid.SFGridEngine.h"
#include "shadow/operational/grid.SFQuadrilateralGrid.h"
#include "shadow/operational/grid.SFRectangularGrid.h"
#include "shadow/operational/grid.SFTriangularGrid.h"
#include "shadow/pipeline/SFPipelineGrid.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/system/SFArray.h"
#include "shadow/system/SFException.h"


class SFQuadsGridGeometry extends SFMeshGeometry{

//	SFQuadsGrid quadsGrid = new SFQuadsGrid();

//	SFQuadsGridGeometry() {

	}

//	SFQuadsGridGeometry(SFPrimitive primitive,int N_u,int N_v,boolean closeU,boolean closeV) {
//		super(primitive);
//		if(primitive.getGridsCount()!=1){
//			throw new SFException("SFQuadsGridGeometry must be initialized with a primitive having size == 1 and with PrimitiveBlock[0]==UV");
		}
//		quadsGrid.setNu( N_u);
//		quadsGrid.setNv( N_v);
//		this->quadsGrid.setCloseU(closeU);
//		this->quadsGrid.setCloseV(closeV);
	}

//	SFQuadsGrid getQuadsGrid() {
//		return quadsGrid;
	}

	
//	void compile() {
//		super.compile();

//		SFPrimitive primitive=getPrimitive();

//		int primitivesSize=((quadsGrid.getNu()-1)*(quadsGrid.getNv()-1))<<(primitive.isQuad()?0:1);
//		int primitiveIndex=getArray().generateElements(primitivesSize);
//		//this->setFirstElement(primitiveIndex);
//		//this->setLastElement(primitiveIndex+primitivesSize);

//				//KEEP THIS COMMENTED LINES FOR TIME-ANALISYS!
//		//List<Long> times=new ArrayList<Long>();
//		//times.add(System.nanoTime());

//		//	SFIndexRange primitiveDataRanges[]=new SFIndexRange[primitive.getGridsCount()];
//		int gridIndex=0;

//		SFPipelineGrid pipelineGrid=primitive.getGrid(gridIndex);

//		int n1=pipelineGrid.getN()-1;
//		float stepn1=1.0f/n1;
//		float[] vs=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getV_splits());
//		float[] us=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getU_splits());

//		SFRectangularGrid<Integer> indices=new SFRectangularGrid<Integer>(us.length,vs.length);
//		SFRectangularGrid<SFValuenf> values=new SFRectangularGrid<SFValuenf>(us.length,vs.length);

//		for (int i = 0; i < us.length; i++) {
//			for (int j = 0; j < vs.length; j++) {
//				values.setValue(j, i, new SFVertex2f(us[i],vs[j]));
			}
		}

//		SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);

//		int position=arrayValues.generateElements(values.getWidth()*values.getHeight());
//		SFGridEngine.loadGridAndGenerateIndices(values, indices, arrayValues, position);

//		if(quadsGrid.isCloseU() || quadsGrid.isCloseV()){
//			//TODO: test this
//			//SFGridEngine.closeRectangle(values, closeU?1:0,  closeV?1:0);
//			SFGridEngine.closeRectangle(indices, quadsGrid.isCloseU()?1:0,  quadsGrid.isCloseV()?1:0);
		}

//		SFQuadrilateralGrid<Integer>[] quads=SFGridEngine.breakRectangularGrid(indices, pipelineGrid.getN());
//		if(pipelineGrid.isTriangular()){
//			SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
//			SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, triangles);
		}
//			SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, quads);	
		}
//	//	primitiveDataRanges[gridIndex]=new SFIndexRange(position, values.getWidth()*values.getHeight());

//		//getMesh().setPrimitiveDataRanges(primitiveDataRanges);

//	//		times.add(System.nanoTime());//1
//	//		SFGridEngine.correctValues(getMesh());
//	//
//	//		//KEEP THIS COMMENTED LINES FOR TIME-ANALISYS!
//	//		times.add(System.nanoTime());//1
//	//		for (int i = 1; i < times.size(); i++) {
//	//			long t1=times.get(i-1);
//	//			long t2=times.get(i);
//	//			System.out.println("\t\t T["+i+"]="+((t2-t1)*0.001*0.001)+"ms");
	}
	}


}
;
}
#endif
