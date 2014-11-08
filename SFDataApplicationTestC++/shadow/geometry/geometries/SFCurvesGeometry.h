#ifndef shadow_geometry_geometries_SFCurvesGeometry_H_
#define shadow_geometry_geometries_SFCurvesGeometry_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/geometry/geometries/structures/SFMeshCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFPrimitiveIndices.h"

namespace sf{
class SFCurvesGeometry : public SFMeshGeometry{

//	//Info
	int verticesCount;
	SFMeshCurve* curves;//all the curves data
	int curves_length;

public:
	void compile() {
		SFMeshGeometry::compile();

			int gridsCount=getArray()->getPrimitive()->getGridsCount();
			int* vPosition=new int[gridsCount];

			int* gridN=new int[gridsCount];
			for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
				SFArray<SFValuenf>* primitiveData=getArray()->getPrimitiveData(gridIndex);
				vPosition[gridIndex]=primitiveData->generateElements(verticesCount);
				gridN[gridIndex]=getArray()->getPrimitive()->getGrid(gridIndex)->getN();
			}

			int** edgesPositions=new int*[curves_length];
			//SFNetEdge[] edges=new SFNetEdge[curves.length];
			short* sizes=new short[gridsCount];
			for (int curveIndex = 0; curveIndex < curves_length; curveIndex++) {

				edgesPositions[curveIndex]=new int[gridsCount];
				SFMeshCurve curve=curves[curveIndex];

				//int curveIndex,int[] gridN,int cTessellation
				SFValuenf** value=extractValues(curveIndex,gridN,gridsCount,sizes,curve.getSide());

				int curveVertex1=curve.getVertices()[0];
				int curveVertex2=curve.getVertices()[1];

				//edges[curveIndex]=new SFNetEdge(curve.getSide());
				short** indices=new short*[gridsCount];
				for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
					indices[gridIndex]=new short[sizes[gridIndex]];
					SFArray<SFValuenf>* primitiveData=getArray()->getPrimitiveData(gridIndex);
					edgesPositions[curveIndex][gridIndex]=primitiveData->generateElements(sizes[gridIndex]-2);
					int vLength=sizes[gridIndex];
					primitiveData->setElement(curveVertex1+vPosition[gridIndex], value[gridIndex]+0);
					indices[gridIndex][0]=(short)(curveVertex1+vPosition[gridIndex]);
					primitiveData->setElement(curveVertex2+vPosition[gridIndex], value[gridIndex]+(vLength-1));
					indices[gridIndex][vLength-1]=(short)(curveVertex2+vPosition[gridIndex]);
					for (int i = 1; i < vLength-1; i++) {
						primitiveData->setElement(edgesPositions[curveIndex][gridIndex]+i-1, value[gridIndex]+i);
						indices[gridIndex][i]=(short)(edgesPositions[curveIndex][gridIndex]+i-1);
					}
				}
				//edges[curveIndex].setIndices(indices);

				int side=curve.getSide();

				//System.err.println(side+" "+indices[0].length+" "+gridN[0]);

				int elementsPosition=getArray()->generateElements(side);

			SFPrimitiveIndices* indicesSample=getArray()->generateSample();

				int* positions=getPrimitive()->getIndicesPositions();
				int* sizes=getPrimitive()->getIndicesSizes();
				for (int i = 0; i < side; i++) {
					int* primitiveIndices=indicesSample->getPrimitiveIndices();
					for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
						int position=positions[gridIndex];
						int size=sizes[gridIndex];

						primitiveIndices[position]=indices[gridIndex][(size-1)*i];
						primitiveIndices[position+1]=indices[gridIndex][(size-1)*i+size-1];
						for (int j = 1; j < size-1; j++) {
							//System.err.println("position+j "+(position+j)+" (size*i+j) "+(size*i+j)+" gridIndex "+gridIndex+" sideIndex "+i+" "+indices[gridIndex].length);
							primitiveIndices[position+j+1]=indices[gridIndex][(size-1)*i+j];
						}
					}
					getArray()->setElement(elementsPosition+i, indicesSample);
				}

			}

	}


	
	void update() {
		SFMeshGeometry:update();
	}


	int getVerticesCount() {
		return verticesCount;
	}


	void setVerticesCount(int verticesCount) {
		this->verticesCount = verticesCount;
	}

	SFMeshCurve* getCurves() {
		return curves;
	}

	void setCurves(SFMeshCurve* curves) {
		this->curves = curves;
	}

	SFValuenf** extractValues(int curveIndex,int* gridN,int gridNlength,short* sizes,int cTessellation) {

		SFCurve** curves=this->curves[curveIndex].getCurve();

//		if(gridN.length!=curves.length)
//			throw new SFException("Not well done "+gridN.length+" "+curves.length);

		SFValuenf** values=new SFValuenf*[gridNlength];

		for (int gridIndex = 0; gridIndex < gridNlength; gridIndex++) {

			int tessellation = (gridN[gridIndex]-1)*cTessellation;

			float step=1.0f/tessellation;
			values[gridIndex]=new SFValuenf[tessellation+1];
			sizes[gridIndex]=tessellation+1;

			SFCurve* curve=curves[gridIndex];

			SFValuenf* value=curve->generateValue();
				for (int j = 0; j <= tessellation; j++) {
					float t=j*step;
					t=curve->getTMin()*(1-t)+curve->getTMax()*t;
					curve->getVertex(t, value);
					values[gridIndex][j]=*value;
				}
			delete value;
		}

		return values;
	}

};

}

#endif
