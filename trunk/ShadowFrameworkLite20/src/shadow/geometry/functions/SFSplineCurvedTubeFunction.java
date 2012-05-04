package shadow.geometry.functions;

import java.util.ArrayList;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;

public class SFSplineCurvedTubeFunction extends SFSurfaceFunction {

	private ArrayList<SFCurve<SFValuenf>> curves=new ArrayList<SFCurve<SFValuenf>>();
	private SFVertex3f tempVertex=new SFVertex3f();
 	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public SFVertex3f evaluateCurve(int index,float v){
		SFVertex3f tmpVertex=new SFVertex3f();
		curves.get(index).getVertex(v, tmpVertex);
		return tmpVertex;
	}
	
	public ArrayList<SFCurve<SFValuenf>> getCurves() {
		return curves;
	}

	@Override
	public float getX(float T, float v) {
		int v_index=(int)(T*curves.size());
		if(v_index==curves.size())
			v_index--;
		
		float t=(T*curves.size())-v_index;
		
		if(v_index==0){
			SFValuenf A=evaluateCurve(0,v);
			SFValuenf B=SFValuenf.middle(evaluateCurve(0,v),evaluateCurve(1,v));
			tempVertex.set(A);
			tempVertex.mult(1-t);
			tempVertex.addMult(t, B);
		}else if(v_index==curves.size()-1){
			SFValuenf A=SFValuenf.middle(evaluateCurve(v_index-1,v),evaluateCurve(v_index,v));
			SFValuenf B=evaluateCurve(v_index,v);
			tempVertex.set(A);
			tempVertex.mult(1-t);
			tempVertex.addMult(t, B);
		}else{
			SFValuenf A=SFValuenf.middle(evaluateCurve(v_index-1,v), evaluateCurve(v_index,v));
			SFValuenf B=evaluateCurve(v_index,v);
			SFValuenf C=SFValuenf.middle(evaluateCurve(v_index,v), evaluateCurve(v_index+1,v));
			
			tempVertex.set(A);
			tempVertex.mult((1-t)*(1-t));
			tempVertex.addMult(2*t*(1-t), B);
			tempVertex.addMult(t*t, C);
		}
		return tempVertex.getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return tempVertex.getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return tempVertex.getZ();
	}
}
