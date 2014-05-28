#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "shadow/geometry/geometries.structures.SFQuadsGrid.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/operational/grid.SFGridEngine.h"
#include "shadow/operational/grid.SFQuadrilateralGrid.h"
#include "shadow/operational/grid.SFRectangularGrid.h"
#include "shadow/operational/grid.SFTriangularGrid.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPipelineGrid.h"
#include "shadow/system/SFArray.h"

class SFQuadsSurfaceGeometry extends SFSurfaceMeshGeometry{

//	SFQuadsGrid quadsGrid=new SFQuadsGrid();
//	int[] gridSizes;
//	float gridUS[];
//	float gridVS[];

//	SFQuadsSurfaceGeometry(){

	}

//	void setQuadsGrid(SFQuadsGrid quadsGrid) {
		this->quadsGrid = quadsGrid;
	}

	
//	void compile() {
//		super.compile();

//		gridSizes=new int[primitive.getGridsCount()];

//		int primitivesSize=((quadsGrid.getNu()-1)*(quadsGrid.getNv()-1))<<(primitive.isQuad()?0:1);
//		int primitiveIndex=getArray().generateElements(primitivesSize);

		this->valuesPositions=new int[gridSizes.length];

//		for (int gridIndex = 0; gridIndex < gridSizes.length; gridIndex++) {

//			SFPipelineGrid pipelineGrid=primitive.getGrid(gridIndex);

//			int n1=pipelineGrid.getN()-1;
//			int width=SFQuadsGrid.getPartitionedSplitsSize(n1,quadsGrid.getNu());
//			int height=SFQuadsGrid.getPartitionedSplitsSize(n1,quadsGrid.getNv());

//			SFRectangularGrid<Integer> indices=new SFRectangularGrid<Integer>(width,height);

//			SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);

			this->valuesPositions[gridIndex]=arrayValues.generateElements(width*height);
//			//samples[gridIndex]=arrayValues.generateSample();
//			SFGridEngine.generateIndices(indices);

//			if(quadsGrid.isCloseU() || quadsGrid.isCloseV()){
//				SFGridEngine.closeRectangle(indices, quadsGrid.isCloseU()?1:0,  quadsGrid.isCloseV()?1:0);
			}

//			SFQuadrilateralGrid<Integer>[] quads=SFGridEngine.breakRectangularGrid(indices, pipelineGrid.getN());
//			if(pipelineGrid.isTriangular()){
//				SFTriangularGrid<Integer>[] triangles=(SFTriangularGrid<Integer>[])SFGridEngine.sliceQuads(quads);
//				SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, triangles);
			}
//				SFGridEngine.loadPrimitiveIndices(getArray(), primitiveIndex, gridIndex, quads);	
			}
		}

//		for (int i = 0; i < fuctionInformations.size(); i++) {

//			FunctionInformations fInfo=fuctionInformations.get(i);
//			int sampleGridIndex = fInfo.gridIndex[0];
//			int n=primitive.getGrid(sampleGridIndex).getN();;

//			int n1=n-1;
//			float stepn1=1.0f/n1;
			this->gridVS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getV_splits());
			this->gridUS=quadsGrid.generatePartitionedSplits(n1, stepn1, quadsGrid.getU_splits());

//			SFArray<SFValuenf> parametersArray=SFPipeline.getSfPipelineMemory().generateValues(2);
//			int positionArray=parametersArray.generateElements(gridUS.length*gridVS.length);
//			int index=positionArray;
//			for (int j = 0; j < gridVS.length; j++) {
//				for (int k = 0; k < gridUS.length; k++) {
//					parametersArray.setElement(index, new SFVertex2f(gridUS[k],gridVS[j]));
//					index++;
				}
			}
//			fInfo.parametersIndexRange=new SFIndexRange(positionArray, gridUS.length*gridVS.length);
//			fInfo.parametersArray=parametersArray;
		}


////		values=new SFRectangularGrid<SFValuenf[]>(gridUS.length,gridVS.length);
////		for (int i = 0; i < gridUS.length; i++) {
////			for (int j = 0; j < gridVS.length; j++) {
////				SFValuenf[] samplesList=new SFValuenf[samples.length];
////				for (int j2 = 0; j2 < samples.length; j2++) {
////					samplesList[j2]=new SFValuenf(samples[j2].getSize());
////					samplesList[j2].set(samples[j2]);
}
////				values.setValue(j, i, samplesList);
}
}

	}

//	SFQuadsGrid getQuadsGrid() {
//		return quadsGrid;
	}

static int getMaxGridsSize(SFPrimitive primitive,int[] gridSizes){
////		int maxGridSize=0;
////		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
////			gridSizes[gridIndex]=primitive.getGrid(gridIndex).getN();
////			if(maxGridSize<gridSizes[gridIndex])
////				maxGridSize=gridSizes[gridIndex];
}
////		return maxGridSize;
}


////	void update(SFRectangularGrid<SFValuenf[]> values,float[] us,float[] vs){
////		for (int i = 0; i < fuctionInformations.size(); i++) {
////			FunctionInformations fInfo=fuctionInformations.get(i);
////			fInfo.function.updateRectangularModel(values, us, vs, fInfo.infos);
}
}


////	void update() {
////		super.update();
////		
////		update(values,gridUS,gridVS);
////		
////		for (int gridIndex = 0; gridIndex < gridSizes.length; gridIndex++) {
////			
////			SFArray<SFValuenf> arrayValues=getArray().getPrimitiveData(gridIndex);
////
////			SFRectangularGrid<Integer> indices=this->indices[gridIndex];
////			
////			if(this->values.getWidth()==indices.getWidth()){
////				for (int i = 0; i < indices.getWidth(); i++) {
////					for (int j = 0; j < indices.getHeight(); j++) {
////						arrayValues.setElement(indices.getValue(j, i), values.getValue(j, i)[gridIndex]);
}
}
}
////				for (int i = 0; i < indices.getWidth(); i++) {
////					for (int j = 0; j < indices.getHeight(); j++) {
////						
////						int bigI=i*(values.getWidth()-1);
////						int bigJ=j*(values.getHeight()-1);
////						
////						int iValues=bigI/(indices.getWidth()-1);
////						int jValues=bigJ/(indices.getHeight()-1);
////						
////						int iresidual=bigI-iValues*(indices.getWidth()-1);
////						int jresidual=bigJ-jValues*(indices.getHeight()-1);
////						
////						if(iresidual==0 && jresidual==0){
////							arrayValues.setElement(indices.getValue(j, i), values.getValue(jValues, iValues)[gridIndex]);
}
////							int jPlus=jValues+1;
////							int iPlus=iValues+1;
////							if(jresidual==0)
////								jPlus=jValues;
////							if(iresidual==0)
////								iPlus=iValues;
////							SFValuenf value00=values.getValue(jValues, iValues)[gridIndex];
////							SFValuenf value01=values.getValue(jValues, iPlus)[gridIndex];
////							SFValuenf value10=values.getValue(jPlus, iValues)[gridIndex];
////							SFValuenf value11=values.getValue(jPlus, iPlus)[gridIndex];
////							float U=(iresidual*1.0f)/(indices.getWidth()-1);
////							float V=(jresidual*1.0f)/(indices.getHeight()-1);
////							SFValuenf interpolatedValue=new SFValuenf(value00.getSize());
////							interpolatedValue.set(value00);
////							interpolatedValue.mult((1-U)*(1-V));
////							interpolatedValue.addMult(U*(1-V), value10);
////							interpolatedValue.addMult(V*(1-U), value01);
////							interpolatedValue.addMult(V*U, value11);
////							arrayValues.setElement(indices.getValue(j, i), interpolatedValue);
}
}
}
////				
}
}
}
}
;
}
#endif
