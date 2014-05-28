#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "java/util/HashMap.h"

class SFVerticesParameters {

	static SFVerticesParameters verticesParameters=new SFVerticesParameters();

	SFVerticesParameters(){

	}

	static SFVerticesParameters getParameters() {
		return verticesParameters;
	}

	HashMap<String,Float> parameters=new HashMap<String, Float>();

	void clear(){
		parameters.clear();
	}

	void setParameter(String parameter,float value){
		parameters.put(parameter,value);
	}

	float getValue(String parameter){
		return parameters.get(parameter);
	}
}
;
}
#endif
