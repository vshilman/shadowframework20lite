package shadow.system.data.objects.binaries;

import shadow.math.SFVertex3f;
import shadow.system.data.objects.SFBinaryValue;

public class SFBinaryUnitVector3FLQ extends SFBinaryValue{
	
	@Override
	public SFBinaryValue clone() {
		return new SFBinaryUnitVector3FLQ();
	}
	
	@Override
	public int getBitSize() {
		return 16;
	}

	public void getVertex3f(SFVertex3f write){
		int indexA=getValue()>>8;
		int indexB=getValue() & 0xff;
		float cosa=SFStaticAnglesSet.getAngleslq().getCos(indexA);
		float sina=SFStaticAnglesSet.getAngleslq().getSin(indexA);
		float cosb=SFStaticAnglesSet.getAngleslq().getCos(indexB);
		float sinb=SFStaticAnglesSet.getAngleslq().getSin(indexB);
		write.set3f(cosa,sina*cosb,sina*sinb);
		if(SFStaticAnglesSet.getAngleslq().equal(0, write.getX()))
			write.setX(0);
		if(SFStaticAnglesSet.getAngleslq().equal(0, write.getY()))
			write.setY(0);
		if(SFStaticAnglesSet.getAngleslq().equal(0, write.getZ()))
			write.setZ(0);
	}
	
	public void setVertex3f(SFVertex3f read){
		float cosa=read.getX();
		float sina=(float)(Math.sqrt(1-cosa*cosa));
		if(SFStaticAnglesSet.getAngleslq().equal(sina, 0)){
			int index=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosa, 0);
			this.value=(index<<8);
		}else{
			int indexA=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosa, sina);
			float sinaRec=1.0f/sina;
			float cosb=read.getY()*sinaRec;
			float sinb=read.getZ()*sinaRec;
			int indexB=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(cosb, sinb);
			this.value=(indexA<<8)+indexB;
		}
		
	}
}
