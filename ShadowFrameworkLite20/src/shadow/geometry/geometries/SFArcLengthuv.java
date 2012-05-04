package shadow.geometry.geometries;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;

public class SFArcLengthuv implements SFSurfaceGeometryTexCoordFunctionuv{

	private boolean v=false;
	private float[] length;
	private float factor;

	public SFArcLengthuv(SFCurve<SFVertex3f> curve,float factor,int N) {
		super();
		
		this.factor=factor;
		this.length=new float[N+1];
		length[0]=0;
		float step=1.0f/N;
		
		SFVertex3f v1=new SFVertex3f();
		SFVertex3f v2=new SFVertex3f();
		for (int i = 1; i < length.length; i++) {
			curve.getVertex(i*step, v1);
			curve.getVertex(i*step+step, v2);
			v2.subtract3f(v1);
			float distance=(float)(Math.sqrt(v2.dot3f(v2)));
			length[i]=length[i-1]+distance;
			System.err.println("length[i ] "+" "+distance+" ");
		}
		
	}
	
	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		
		if(this.v){
			float value = getValue(v);
			return new SFVertex2f(0,value);	
		}else{
			float value = getValue(u);
			return new SFVertex2f(value,0);
		}
	}

	private float getValue(float u) {
		float t=u*(length.length-1);
		int index=(int)t;
	
		if(index>=length.length-1)
			index=length.length-2;

		float T=t-index;
		float value=length[index]*(1-T)+length[index+1]*T;
		return value*factor;
	}
	
	@Override
	public void init() {
		
	}
}
