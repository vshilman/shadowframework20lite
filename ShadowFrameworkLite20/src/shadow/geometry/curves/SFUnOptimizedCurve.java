package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;

public abstract class SFUnOptimizedCurve<T extends SFValuenf> implements SFCurve{
	

	public void get(SFValuenf[] vertices,SFValuenf[] derivatives,int n){
		get(generateTs(n), vertices, derivatives);
	}
	
	public void get(SFValuenf[] vertices,SFValuenf[] derivatives,SFValuenf[] derivatives2,int n){
		get(generateTs(n), vertices, derivatives, derivatives2);
	}

	public void get(float ts[],SFValuenf[] vertices,SFValuenf[] derivatives){
		getVertices(ts, vertices);
		getDevDt(ts, derivatives);
	}
	
	public void get(float ts[],SFValuenf[] vertices,SFValuenf[] derivatives,SFValuenf[] derivatives2){
		getVertices(ts, vertices);
		getDevDt(ts, derivatives);
		getDev2Dt(ts, derivatives2);
	}
	
	private float[] generateTs(int n){
		float[] ts=new float[n];
		float dt=1.0f/(n-1);
		for (int i = 0; i < ts.length; i++) {
			ts[i]=dt*i;
		}
		return ts;
	}
	
	public void getVertices(SFValuenf[] read,int n){
		getVertices(generateTs(n), read);
	}
	
	public void getDevDt(SFValuenf[] read,int n){
		getDevDt(generateTs(n), read);
	}
	
	public void getDev2Dt(SFValuenf[] read,int n){
		getDev2Dt(generateTs(n), read);
	}
	
	public void getVertices(float ts[],SFValuenf[] read){
		for (int i = 0; i < read.length; i++) {
			getVertex(ts[i], read[i]);
		}
	}
	
	public void getDevDt(float ts[],SFValuenf[] read){
		for (int i = 0; i < read.length; i++) {
			getDevDt(ts[i], read[i]);
		}
	}
	
	public void getDev2Dt(float ts[],SFValuenf[] read){
		for (int i = 0; i < read.length; i++) {
			getDev2Dt(ts[i], read[i]);
		}
	}
	
}
