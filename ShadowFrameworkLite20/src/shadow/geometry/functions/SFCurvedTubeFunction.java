package shadow.geometry.functions;

import java.util.ArrayList;

import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFVertex3f;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFCurvedTubeFunction extends SFSurfaceFunction{

	private static float pi2=(float)(2*Math.PI);
	
	public ArrayList<SFVertex3f> Cc=new ArrayList<SFVertex3f>();
	public ArrayList<SFVertex3f> Rc=new ArrayList<SFVertex3f>();
	private SFVertex3f Ccv=new SFVertex3f();
	private SFVertex3f Rcv=new SFVertex3f();
	private SFVertex3f dCcdv=new SFVertex3f();
	private SFVertex3f Vec1v=new SFVertex3f();
	private SFVertex3f Vec2v=new SFVertex3f();
	
	private float lastV=-1;
	private float cos=0;
	private float sin=0;
	
	private void evalCurve(float v,ArrayList<SFVertex3f> pts,SFVertex3f P,SFVertex3f dPdv/*,SFVertex3f d2Pdv2*/){
		
		int v_index=(int)(v*pts.size());
		if(v_index==pts.size())
			v_index--;
		
		float t=(v*pts.size())-v_index;
		
		if(v_index==0){
			SFVertex3f A=pts.get(0);
			SFVertex3f B=SFVertex3f.middle(pts.get(0),pts.get(1));
			P.set3f(A);P.mult(1-t);P.addMult3f(t, B);
			dPdv.set3f(B);dPdv.subtract3f(A);
			//d2Pdv2.set(0,0,0);
		}else if(v_index==pts.size()-1){
			SFVertex3f A=SFVertex3f.middle(pts.get(v_index-1),pts.get(v_index));
			SFVertex3f B=pts.get(v_index);
			P.set3f(A);P.mult(1-t);P.addMult3f(t, B);
			dPdv.set3f(B);dPdv.subtract3f(A);
			//d2Pdv2.set(0,0,0);
		}else{
			SFVertex3f A=SFVertex3f.middle(pts.get(v_index-1), pts.get(v_index));
			SFVertex3f B=pts.get(v_index);
			SFVertex3f C=SFVertex3f.middle(pts.get(v_index), pts.get(v_index+1));
			
			P.set3f(A);P.mult((1-t)*(1-t));P.addMult3f(2*t*(1-t), B);P.addMult3f(t*t, C);
			dPdv.set3f(A);dPdv.mult(-2*(1-t));dPdv.addMult3f(2-4*t, B);dPdv.addMult3f(2*t, C);
			//d2Pdv2.set(A);d2Pdv2.mult(2);d2Pdv2.addMult3f(-4,B);d2Pdv2.addMult3f(2, C);
		}
	}
	
	private void evalAll(float v){
		if(v!=lastV){
			evalCurve(v, Cc, Ccv, dCcdv/*, d2Ccdv2*/);
			evalCurve(v, Rc, Rcv/*, dRcdv*/, new SFVertex3f());
			Vec1v.set3f(Rcv);Vec1v.subtract3f(Ccv);
			
			Vec2v.set3f(dCcdv.cross(Vec1v));
			Vec2v.normalize3f();
			Vec2v.mult((float)(Math.sqrt(Vec1v.dot3f(Vec1v))));
						
			lastV=v;
		}
	}
	
	@Override
	public float getX(float u, float v) {
		evalAll(v);
		cos=(float)(Math.cos(2*Math.PI*u));
		sin=(float)(Math.sin(2*Math.PI*u));
		return Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	}

	
	@Override
	public SFDataset generateNewDatasetInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void readFromStream(SFInputStream stream) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		// TODO Auto-generated method stub
		
	}
	
	
}