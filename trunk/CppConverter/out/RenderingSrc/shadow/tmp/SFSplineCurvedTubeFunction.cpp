#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/functions.SFGenericSurfaceFunction.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"

class SFSplineCurvedTubeFunction  extends SFGenericSurfaceFunction{

	ArrayList<SFCurve> curves=new ArrayList<SFCurve>();
//	SFVertex3f tempVertex=new SFVertex3f();

//	SFVertex3f evaluateCurve(int index,float v){
//		SFVertex3f tmpVertex=new SFVertex3f();
//		curves.get(index).getVertex(v, tmpVertex);
//		return tmpVertex;
	}

//	ArrayList<SFCurve> getCurves() {
//		return curves;
	}

	
//	float getX(float T, float v) {
//		int v_index=(int)(T*curves.size());
//		if(v_index==curves.size())
//			v_index--;

//		float t=(T*curves.size())-v_index;

//		if(v_index==0){
//			SFValuenf A=evaluateCurve(0,v);
//			SFValuenf B=SFValuenf.middle(evaluateCurve(0,v),evaluateCurve(1,v));
//			tempVertex.set(A);
//			tempVertex.mult(1-t);
//			tempVertex.addMult(t, B);
		}
//			SFValuenf A=SFValuenf.middle(evaluateCurve(v_index-1,v),evaluateCurve(v_index,v));
//			SFValuenf B=evaluateCurve(v_index,v);
//			tempVertex.set(A);
//			tempVertex.mult(1-t);
//			tempVertex.addMult(t, B);
		}
//			SFValuenf A=SFValuenf.middle(evaluateCurve(v_index-1,v), evaluateCurve(v_index,v));
//			SFValuenf B=evaluateCurve(v_index,v);
//			SFValuenf C=SFValuenf.middle(evaluateCurve(v_index,v), evaluateCurve(v_index+1,v));

//			tempVertex.set(A);
//			tempVertex.mult((1-t)*(1-t));
//			tempVertex.addMult(2*t*(1-t), B);
//			tempVertex.addMult(t*t, C);
		}
//		return tempVertex.getX();
	}

	
//	float getY(float u, float v) {
//		return tempVertex.getY();
	}

	
//	float getZ(float u, float v) {
//		return tempVertex.getZ();
	}
}
;
}
#endif
