package shadow.math;


public class SFUnitVector3f extends SFVertex2f{

	public SFUnitVector3f() {
		super(0,0);
	}
	
	public SFUnitVector3f(SFUnitVector3f vector) {
		super(vector);
	}
	
	public void getVertex3f(SFVertex3f write){
		float cosa=(float)(Math.cos(v[0]));
		float sina=(float)(Math.sin(v[0]));
		float cosb=(float)(Math.cos(v[1]));
		float sinb=(float)(Math.sin(v[1]));
		write.set3f(cosa,sina*cosb,sina*sinb);
	}
	
	public void setVertex3f(SFVertex3f read){
		read.normalize3f();
		float cosa=read.getX();
		float sina=(float)(Math.sqrt(1-cosa*cosa));
		
		v[0]=(float)(Math.atan2(sina, cosa));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosa, sina);
		float sinaRec=1.0f/sina;
		float cosb=read.getY()*sinaRec;
		float sinb=read.getZ()*sinaRec;
		v[1]=(float)(Math.atan2(sinb, cosb));
	}
	
}
