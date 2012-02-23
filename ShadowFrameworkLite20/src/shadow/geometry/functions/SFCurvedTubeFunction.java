package shadow.geometry.functions;

import java.util.List;

import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFVertex3f;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFVertex3fData;

public class SFCurvedTubeFunction extends SFSurfaceFunction{

	public class SFCurvedTubeData extends SFCompositeDataArray{
		
		private SFDataList<SFVertex3fData> Cc;
		private SFDataList<SFVertex3fData> Rc;
		private SFVertex3fData Ccv;
		private SFVertex3fData Rcv;
		private SFVertex3fData dCcdv;
		private SFVertex3fData Vec1v;
		private SFVertex3fData Vec2v;
		
		@Override
		public void generateData() {
			Cc=new SFDataList<SFVertex3fData>(new SFVertex3fData());
			Rc=new SFDataList<SFVertex3fData>(new SFVertex3fData());
			Ccv=new SFVertex3fData();
			Rcv=new SFVertex3fData();
			dCcdv=new SFVertex3fData();
			Vec1v=new SFVertex3fData();
			Vec2v=new SFVertex3fData();
			
			addDataObject(Cc);
			addDataObject(Rc);
			addDataObject(Ccv);
			addDataObject(Rcv);
			addDataObject(dCcdv);
			addDataObject(Vec1v);
			addDataObject(Vec2v);
		}
		
		@Override
		public SFCurvedTubeData clone() {
			return new SFCurvedTubeData();
		}

		public SFDataList<SFVertex3fData> getCc() {
			return Cc;
		}

		public SFDataList<SFVertex3fData> getRc() {
			return Rc;
		}

		public SFVertex3fData getCcv() {
			return Ccv;
		}

		public SFVertex3fData getRcv() {
			return Rcv;
		}

		public SFVertex3fData getdCcdv() {
			return dCcdv;
		}

		public SFVertex3fData getVec1v() {
			return Vec1v;
		}

		public SFVertex3fData getVec2v() {
			return Vec2v;
		}

	}

	
	public SFCurvedTubeFunction() {
		super();
		setData(new SFCurvedTubeData());
	}

	private float lastV=-1;
	private float cos=0;
	private float sin=0;
	
	/*TODO this may be part of a new abstraction...*/
	private static void evalCurve(float v,List<SFVertex3fData> pts,SFVertex3fData P,SFVertex3fData dPdv/*,SFVertex3f d2Pdv2*/){
		
		int v_index=(int)(v*pts.size());
		if(v_index==pts.size())
			v_index--;
		
		float t=(v*pts.size())-v_index;
		
		if(v_index==0){
			SFVertex3f A=pts.get(0).getVertex3f();
			SFVertex3f B=SFVertex3f.middle(pts.get(0).getVertex3f(),pts.get(1).getVertex3f());
			P.getVertex3f().set3f(A);
			P.getVertex3f().mult(1-t);
			P.getVertex3f().addMult3f(t, B);
			dPdv.getVertex3f().set3f(B);
			dPdv.getVertex3f().subtract3f(A);
			//d2Pdv2.set(0,0,0);
		}else if(v_index==pts.size()-1){
			SFVertex3f A=SFVertex3f.middle(pts.get(v_index-1).getVertex3f(),pts.get(v_index).getVertex3f());
			SFVertex3f B=pts.get(v_index).getVertex3f();
			P.getVertex3f().set3f(A);
			P.getVertex3f().mult(1-t);
			P.getVertex3f().addMult3f(t, B);
			dPdv.getVertex3f().set3f(B);
			dPdv.getVertex3f().subtract3f(A);
			//d2Pdv2.set(0,0,0);
		}else{
			SFVertex3f A=SFVertex3f.middle(pts.get(v_index-1).getVertex3f(), pts.get(v_index).getVertex3f());
			SFVertex3f B=pts.get(v_index).getVertex3f();
			SFVertex3f C=SFVertex3f.middle(pts.get(v_index).getVertex3f(), pts.get(v_index+1).getVertex3f());
			
			P.getVertex3f().set3f(A);
			P.getVertex3f().mult((1-t)*(1-t));
			P.getVertex3f().addMult3f(2*t*(1-t), B);
			P.getVertex3f().addMult3f(t*t, C);
			dPdv.getVertex3f().set3f(A);
			dPdv.getVertex3f().mult(-2*(1-t));
			dPdv.getVertex3f().addMult3f(2-4*t, B);
			dPdv.getVertex3f().addMult3f(2*t, C);
			//d2Pdv2.set(A);d2Pdv2.mult(2);d2Pdv2.addMult3f(-4,B);d2Pdv2.addMult3f(2, C);
		}
	}
	
	private void evalAll(float v){
		if(v!=lastV){
			evalCurve(v, getData().Cc, getData().Ccv, getData().dCcdv/*, d2Ccdv2*/);
			evalCurve(v, getData().Rc, getData().Rcv/*, dRcdv*/, new SFVertex3fData());
			getData().Vec1v.getVertex3f().set3f(getData().Rcv.getVertex3f());
			getData().Vec1v.getVertex3f().subtract3f(getData().Ccv.getVertex3f());
			
			getData().Vec2v.getVertex3f().set3f(getData().dCcdv.getVertex3f().cross(getData().Vec1v.getVertex3f()));
			getData().Vec2v.getVertex3f().normalize3f();
			getData().Vec2v.getVertex3f().mult((float)(Math.sqrt(getData().Vec1v.getVertex3f().dot3f(getData().Vec1v.getVertex3f()))));
						
			lastV=v;
		}
	}
	
	@Override
	public float getX(float u, float v) {
		evalAll(v);
		cos=(float)(Math.cos(2*Math.PI*u));
		sin=(float)(Math.sin(2*Math.PI*u));
		return getData().Ccv.getVertex3f().getX()+cos*getData().Vec1v.getVertex3f().getX()+sin*getData().Vec2v.getVertex3f().getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return getData().Ccv.getVertex3f().getY()+cos*getData().Vec1v.getVertex3f().getY()+sin*getData().Vec2v.getVertex3f().getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return getData().Ccv.getVertex3f().getZ()+cos*getData().Vec1v.getVertex3f().getZ()+sin*getData().Vec2v.getVertex3f().getZ();
	}

	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFCurvedTubeFunction();
	}

	public SFCurvedTubeData getData() {
		return (SFCurvedTubeData)(super.getSFDataObject());
	}
	
}