#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/geometries.structures.SFMeshCurve.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFNetEdge.h"
#include "shadow/pipeline/SFNetPolygon.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/system/SFException.h"

class SFCurvesMesh extends SFMeshGeometry{

//	//Info
//	int verticesCount;
//	SFMeshCurve[] curves;//all the curves data
//	short[][] polygons;

//	//Build Data
//	SFNetPolygon[] netPolygons;

	
//	void compile() {
//		super.compile();

//		try {
//			//Step 1. Allocate vertices
//			int gridsCount=getArray().getPrimitive().getGridsCount();
//			int[] vPosition=new int[gridsCount];
//			int[] gridN=new int[gridsCount];
//			for (int gridIndex = 0; gridIndex < vPosition.length; gridIndex++) {
//				vPosition[gridIndex]=getArray().getPrimitiveData(gridIndex).generateElements(verticesCount);
//				gridN[gridIndex]=getArray().getPrimitive().getGrid(gridIndex).getN();
			}

//			//Step 2. Allocate And Set Up curves

//			int[][] edgesPositions=new int[curves.length][gridsCount];
//			SFNetEdge[] edges=new SFNetEdge[curves.length];
//			for (int curveIndex = 0; curveIndex < curves.length; curveIndex++) {

//				SFMeshCurve curve=curves[curveIndex];

//				//int curveIndex,int[] gridN,int cTessellation
//				SFValuenf[][] value=extractValues(curveIndex,gridN,curve.getSide());

//				int curveVertex1=curve.getVertices()[0];
//				int curveVertex2=curve.getVertices()[1];

//				edges[curveIndex]=new SFNetEdge(curve.getSide());
//				short[][] indices=new short[gridsCount][];
//				for (int gridIndex = 0; gridIndex < gridsCount; gridIndex++) {
//					indices[gridIndex]=new short[value[gridIndex].length];
//					edgesPositions[curveIndex][gridIndex]=getArray().getPrimitiveData(gridIndex).generateElements(value[gridIndex].length-2);
//					int vLength=value[gridIndex].length;
//					getArray().getPrimitiveData(gridIndex).setElement(curveVertex1+vPosition[gridIndex], value[gridIndex][0]);
//					indices[gridIndex][0]=(short)(curveVertex1+vPosition[gridIndex]);
//					getArray().getPrimitiveData(gridIndex).setElement(curveVertex2+vPosition[gridIndex], value[gridIndex][vLength-1]);
//					indices[gridIndex][vLength-1]=(short)(curveVertex2+vPosition[gridIndex]);
//					for (int i = 1; i < vLength-1; i++) {
//						getArray().getPrimitiveData(gridIndex).setElement(edgesPositions[curveIndex][gridIndex]+i-1, value[gridIndex][i]);
//						indices[gridIndex][i]=(short)(edgesPositions[curveIndex][gridIndex]+i-1);
					}
				}
//				edges[curveIndex].setIndices(indices);
			}

//			//Step 3. Create Net Polygons
//			netPolygons=new SFNetPolygon[polygons.length];
//			for (int i = 0; i < polygons.length; i++) {

//				SFNetEdge[] polEdges=new SFNetEdge[polygons[i].length];
//				for (int j = 0; j < polEdges.length; j++) {
//					polEdges[j]=edges[polygons[i][j]];
				}

//				netPolygons[i]=SFPipeline.getSfPipelineNets().generateSFNetPolygon(getArray(),polEdges, getArray().getPrimitive().getGridModel());

			}


		}
//			e.printStackTrace();
		}
	}


	
//	void update() {
//		super.update();

//		if(netPolygons!=null)
//			for (int i = 0; i < netPolygons.length; i++) {
//				if(netPolygons[i]!=null)
//					netPolygons[i].update();
			}
	}


//	int getVerticesCount() {
//		return verticesCount;
	}


//	void setVerticesCount(int verticesCount) {
		this->verticesCount = verticesCount;
	}

//	SFMeshCurve[] getCurves() {
//		return curves;
	}

//	void setCurves(SFMeshCurve[] curves) {
		this->curves = curves;
	}


//	short[][] getPolygons() {
//		return polygons;
	}


//	void setPolygons(short[][] polygons) {
		this->polygons = polygons;
	}

//	SFValuenf[][] extractValues(int curveIndex,int[] gridN,int cTessellation) {

//		SFCurve[] curves=this->curves[curveIndex].getCurve();

//		if(gridN.length!=curves.length)
//			throw new SFException("Not well done "+gridN.length+" "+curves.length);

//		SFValuenf[][] values=new SFValuenf[gridN.length][];

//		for (int gridIndex = 0; gridIndex < values.length; gridIndex++) {

//			int tessellation = (gridN[gridIndex]-1)*cTessellation;
//			float step=1.0f/tessellation;
//			values[gridIndex]=new SFValuenf[tessellation+1];

//			SFCurve curve=curves[gridIndex];

//			for (int j = 0; j <= tessellation; j++) {
//				float t=j*step;
//				t=curve.getTMin()*(1-t)+curve.getTMax()*t;
//				values[gridIndex][j]=curve.generateValue();
//				curve.getVertex(t, values[gridIndex][j]);
			}
		}

//		return values;
	}
}
;
}
#endif
