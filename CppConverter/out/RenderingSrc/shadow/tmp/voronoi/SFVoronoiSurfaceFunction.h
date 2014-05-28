#ifndef shadow_tmp_voronoi_H_
#define shadow_tmp_voronoi_H_

#include "java/util/ArrayList.h"
#include "java/util/List.h"

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions.SFGenericSurfaceFunction.h"
#include "shadow/math/SFVertex2f.h"

class SFVoronoiSurfaceFunction extends SFGenericSurfaceFunction implements SFSurfaceFunction{

	ArrayList<SFVertex2f>();
//	SFVoronoiDistance distance;
//	SFIVoronoiModel model;

//	SFVoronoiSurfaceFunction() {
//		super();
	}


//	SFIVoronoiModel getModel() {
//		return model;
	}



//	void setModel(SFIVoronoiModel model) {
		this->model = model;
	}



//	List<SFVertex2f> getCenters() {
//		return centers;
	}

//	void setCenters(List<SFVertex2f> centers) {
//		this->centers.addAll(centers);
	}

//	SFVoronoiDistance getDistance() {
//		return distance;
	}

//	void setDistance(SFVoronoiDistance distance) {
		this->distance = distance;
	}

//	SFVertex2f projectVertex(SFVertex2f toProject,int index1,int index2){

//		SFVertex2f dir1=new SFVertex2f(toProject);
//		SFVertex2f dir2=new SFVertex2f(centers.get(index2));

//		dir1.subtract(centers.get(index1));
//		dir2.subtract(centers.get(index1));

//		dir2.normalize2f();
//		float scalar=dir1.dot2f(dir2);
//		dir2.mult(scalar);
//		dir2.add(centers.get(index1));

////		System.err.println("toProject "+toProject+" indices "+index1+","+index2);
////		System.err.println("scalar "+scalar);

//		return dir2;
	}



	
//	float getX(float u, float v) {
//		return u;
	}
	
//	float getY(float u, float v) {
//		return v;
	}
	
//	float getZ(float u, float v) {
//		return getValue(u, v);
	}



//	float getValue(float u, float v) {

//		float distance=1000;
//		int index=0;
//		float distance2=1000;
//		int index2=0;

//		for (int i = 0; i < centers.size(); i++) {
//			float distancei=this->distance.distance(u,v,centers.get(i));
//			if(distancei<distance2){
//				distance2=distancei;
//				index2=i;
			}
//			if(distancei<distance){
//				distance2=distance;
//				index2=index;
//				distance=distancei;
//				index=i;
			}
		}

//		SFVertex2f projected=projectVertex(new SFVertex2f(u,v), index, index2);

//		float value = model.getValue(index, index2, distance, distance2, 
//				SFVertex2f.getDistance(projected, centers.get(index)),
//				SFVertex2f.getDistance(projected, centers.get(index2)));

//		//System.err.println("SFVoronoiSurfaceFunction u "+u+" v "+v+" value "+value);

//		return value;
	}
}
;
}
#endif
