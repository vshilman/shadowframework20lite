package shadow.geometry;

import shadow.math.SFValuenf;
import shadow.system.SFInitiable;

public interface SFCurve<T extends SFValuenf> extends SFInitiable{
	
	public float getTMin();
	
	public float getTMax();
	
	public void getVertex(float t,SFValuenf read);
	
	public void getDevDt(float t,SFValuenf read);
	
	public void getDev2Dt(float t,SFValuenf read);
	
	public SFValuenf generateValue();
}
