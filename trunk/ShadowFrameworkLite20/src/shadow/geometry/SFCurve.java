package shadow.geometry;

import shadow.math.SFValuenf;
import shadow.system.SFInitiable;

public interface SFCurve extends SFInitiable{
	
	public float getTMin();
	
	public float getTMax();

	public void get(float ts[],SFValuenf[] vertices,SFValuenf[] derivatives);
	
	public void get(float ts[],SFValuenf[] vertices,SFValuenf[] derivatives,SFValuenf[] derivatives2);
	
	public void getVertices(float ts[],SFValuenf[] read);
	
	public void getDevDt(float ts[],SFValuenf[] read);
	
	public void getDev2Dt(float ts[],SFValuenf[] read);
	
	public void getVertex(float t,SFValuenf read);
	
	public void getDevDt(float t,SFValuenf read);
	
	public void getDev2Dt(float ts,SFValuenf read);
	
	public SFValuenf generateValue();
}
