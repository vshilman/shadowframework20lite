package shadow.geometry.curves;

import shadow.math.SFVertex3f;

public class SFCurves {
	
	private static float rec3=1.0f/3.0f;

	public static SFBezier3<SFVertex3f> generateBezier33f(float ax,float ay,float az,
			  float dx,float dy,float dz) {
		SFBezier3<SFVertex3f> bezier=new SFBezier3<SFVertex3f>();
		bezier.getVertices()[0].set(ax,ay,az);
		bezier.getVertices()[1].set(rec3*(ax*2+dx),rec3*(ay*2+dy),rec3*(az*2+dz));
		bezier.getVertices()[2].set(rec3*(ax+dx*2),rec3*(ay+dy*2),rec3*(az+dz*2));
		bezier.getVertices()[3].set(dx,dy,dz);
		
		return bezier;
	}
	
	public static SFBezier3<SFVertex3f> generateBezier33f(float ax,float ay,float az,
			  float mx,float my,float mz,
			  float dx,float dy,float dz) {
		SFBezier3<SFVertex3f> bezier=new SFBezier3<SFVertex3f>();
		bezier.getVertices()[0].set(ax,ay,az);
		bezier.getVertices()[1].set(rec3*(mx*2+ax),rec3*(my*2+ay),rec3*(mz*2+az));
		bezier.getVertices()[2].set(rec3*(dx+mx*2),rec3*(dy+my*2),rec3*(dz+mz*2));
		bezier.getVertices()[3].set(dx,dy,dz);
		return bezier;
	}
	
	
}
