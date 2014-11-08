#ifndef shadow_geometry_geometries_SFCurvesMesh_H_
#define shadow_geometry_geometries_SFCurvesMesh_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/geometry/geometries/structures/SFMeshCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFNetEdge.h"
#include "shadow/pipeline/SFNetPolygon.h"
#include "shadow/pipeline/SFPipeline.h"

namespace sf{

class SFCurvesMesh : public SFMeshGeometry{

	int verticesCount;
	SFMeshCurve* curves;//all the curves data
	short curves_length;
	short** polygons;
	short* polygon_length;
	short polygons_length;

  	//Build Data
	SFNetPolygon** netPolygons;
	short netPolygons_length;

	
  	void compile() {
  		SFMeshGeometry::compile();

		//Step 1. Allocate vertices
		int gridsCount=getArray()->getPrimitive()->getGridsCount();
		int* vPosition=new int[gridsCount];
		int* gridN=new int[gridsCount];
		for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
			SFArray<SFValuenf>* primitiveData=getArray()->getPrimitiveData(gridIndex);
			vPosition[gridIndex]=primitiveData->generateElements(verticesCount);
			gridN[gridIndex]=getArray()->getPrimitive()->getGrid(gridIndex)->getN();
		}

		//Step 2. Allocate And Set Up curves

		int** edgesPositions=new int*[curves_length];
		SFNetEdge** edges=new SFNetEdge*[curves_length];
		int* sizes=new int[gridsCount];
		for (int curveIndex = 0; curveIndex < curves_length; curveIndex++) {
			edgesPositions[curveIndex]=new int[gridsCount];
			SFMeshCurve curve=curves[curveIndex];

			//int curveIndex,int[] gridN,int cTessellation
			SFValuenf** value=extractValues(curveIndex,gridN,gridsCount,sizes,curve.getSide());

			int curveVertex1=curve.getVertices()[0];
			int curveVertex2=curve.getVertices()[1];

			edges[curveIndex]=new SFNetEdge(curve.getSide());
			short** indices=new short*[gridsCount];
			for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
				indices[gridIndex]=new short[sizes[gridIndex]];
				SFArray<SFValuenf>* primitiveData=getArray()->getPrimitiveData(gridIndex);
				edgesPositions[curveIndex][gridIndex]=primitiveData->generateElements(sizes[gridIndex]-2);
				int vLength=sizes[gridIndex];
				primitiveData->setElement(curveVertex1+vPosition[gridIndex], &(value[gridIndex][0]));
				indices[gridIndex][0]=(short)(curveVertex1+vPosition[gridIndex]);
				primitiveData->setElement(curveVertex2+vPosition[gridIndex], &(value[gridIndex][vLength-1]));
				indices[gridIndex][vLength-1]=(short)(curveVertex2+vPosition[gridIndex]);
				for (int i = 1; i < vLength-1; i++) {
					primitiveData->setElement(edgesPositions[curveIndex][gridIndex]+i-1, &(value[gridIndex][i]));
					indices[gridIndex][i]=(short)(edgesPositions[curveIndex][gridIndex]+i-1);
				}
			}
			edges[curveIndex]->setIndices(indices);
		}

		//Step 3. Create Net Polygons
		netPolygons=new SFNetPolygon*[polygons_length];
		for (int i = 0; i < polygons_length; i++) {

			SFNetEdge** polEdges=new SFNetEdge*[polygon_length[i]];
			for (int j = 0; j < polygon_length[i]; j++) {
				polEdges[j]=edges[polygons[i][j]];
			}

			netPolygons[i]=SFPipeline::getSfPipelineNets()->generateSFNetPolygon(getArray(),polEdges,polygon_length[i], getArray()->getPrimitive()->getGridModel());
//			virtual SFNetPolygon* generateSFNetPolygon(SFPrimitiveArray* array,
//						SFNetEdge* edges,int edgesLength,SFGridModel* primitiveModel)=0;
		}
	}


	
  	void update() {
  		SFMeshGeometry::update();

  		if(netPolygons!=0)
  			for (int i = 0; i < netPolygons_length; i++) {
  				if(netPolygons[i]!=0)
  					netPolygons[i]->update();
			}
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


  	short** getPolygons() {
  		return polygons;
	}


  	void setPolygons(short** polygons) {
		this->polygons = polygons;
	}

  	SFValuenf** extractValues(int curveIndex,int* gridN,int gridNlength,int* sizes,int cTessellation) {

  		SFCurve** curves=this->curves[curveIndex].getCurve();

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
  				value=values[gridIndex]+j;
  				curve->getVertex(t, value);
			}
  			delete value;
		}

  		return values;
	}

};

}
#endif
