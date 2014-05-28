#ifndef shadow_geometry_H_
#define shadow_geometry_H_

#include "java/util/ArrayList.h"

#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/system/SFGraphicsResource.h"

//abstract class SFGeometry implements SFGraphicsResource{

//	static final int LOD_HINT_DISCARD=-1;

//	//TODO: define all possible rendering hints : NO, rendering HINT SHOULD BE A Type

//	int rendering_hint=0;
//	int available_lods=0;//more a constant then 

//	SFGeometry fatherGeometry;

	ArrayList<SFGeometry> sonGeometries=new ArrayList<SFGeometry>();

//	boolean changed=true;


//	abstract SFPrimitive getPrimitive();
//	//abstract String[] getTransforms();

//	abstract void drawGeometry(int lod);


//	int getRendering_hint() {
//		return rendering_hint;
	}

//	protected void setRendering_hint(int rendering_hint) {
		this->rendering_hint = rendering_hint;
	}

//	int getAvailable_lods() {
//		return available_lods;
	}

//	protected void setAvailable_lods(int available_lods) {
		this->available_lods = available_lods;
	}

//	int getSonsCount(){
//		return sonGeometries.size();
	}

//	int addSon(SFGeometry son){
//		 sonGeometries.add(son);
//		 son.setFatherGeometry(this);
//		return sonGeometries.size()-1;
	}

//	SFGeometry getSon(int index){
//		return sonGeometries.get(index);
	}


//	SFGeometry getFatherGeometry() {
//		return fatherGeometry;
	}

//	void setFatherGeometry(SFGeometry fatherGeometry) {
		this->fatherGeometry = fatherGeometry;
	}

//	abstract void compile();

//	abstract void update();

//	abstract void decompile();

//	void rebuild(){
//		compile();
//		for (int i = 0; i < sonGeometries.size(); i++) {
//			sonGeometries.get(i).rebuild();
		}
	}

}
;
}
#endif
