#ifndef shadow_geometry_functions_H_
#define shadow_geometry_functions_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/system/SFResource.h"

//abstract class SFGenericSurfaceFunction implements SFSurfaceFunction{

//	SFResource resource=new SFResource(0);

//	SFResource getResource() {
//		return resource;
	}

////	void updateRectangularModel(SFRectangularGrid<SFValuenf[]> values, float[] us,
////			float[] vs, SFPrimitiveBlock[] infos) {
////		for (int gridIndex = 0; gridIndex < infos.length; gridIndex++) {
////			if(infos[gridIndex]==SFPrimitiveBlock.NORMAL){
////				for (int j = 0; j < values.getWidth(); j++) {
////					for (int k = 0; k < values.getHeight(); k++) {
////						values.getValue(k, j)[gridIndex].set(getNormal(us[j], vs[k]));
}
}
}
////				for (int j = 0; j < values.getWidth(); j++) {
////					for (int k = 0; k < values.getHeight(); k++) {
////						values.getValue(k, j)[gridIndex].set(getDu(us[j], vs[k]));
}
}
}
////				for (int j = 0; j < values.getWidth(); j++) {
////					for (int k = 0; k < values.getHeight(); k++) {
////						values.getValue(k, j)[gridIndex].set(getDv(us[j], vs[k]));
}
}
}
////				for (int j = 0; j < values.getWidth(); j++) {
////					for (int k = 0; k < values.getHeight(); k++) {
////						values.getValue(k, j)[gridIndex].set(getPosition(us[j], vs[k]));
}
}
}
}
}

////	static final float eps=0.01f;
////	
////	abstract float getX(float u,float v);
////	abstract float getY(float u,float v);
////	abstract float getZ(float u,float v);
////	
////	SFResource getResource() {
////		return resource;
}
////	
////	SFVertex3f getPosition(float u,float v){
////		return new SFVertex3f(getX(u, v),getY(u, v),getZ(u, v));
}
////	
////	void getPosition(float u,float v,SFVertex3f write){	
////		write.set3f(getX(u, v),getY(u, v),getZ(u, v));
}
////
////	SFVertex3f getDu(float u,float v){
////		SFVertex3f p1=getPosition(u-eps, v);
////		SFVertex3f p2=getPosition(u+eps, v);
////		p2.subtract3f(p1);
////		p2.mult(1.0f/(2*eps));
////		return p2;
}
////	
////	SFVertex3f getDv(float u,float v){
////		SFVertex3f p1=getPosition(u, v-eps);
////		SFVertex3f p2=getPosition(u, v+eps);
////		p2.subtract3f(p1);
////		p2.mult(1.0f/(2*eps));
////		return p2;
}
////	
////	SFVertex3f getNormal(float u,float v){
////		SFVertex3f normal=getDu(u, v).cross(getDv(u, v));
////		normal.normalize3f();
////		return normal;
}
////	
}
;
}
#endif
