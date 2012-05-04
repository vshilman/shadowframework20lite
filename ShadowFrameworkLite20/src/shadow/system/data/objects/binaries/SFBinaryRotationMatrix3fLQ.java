package shadow.system.data.objects.binaries;

import shadow.math.SFMatrix3f;
import shadow.system.data.objects.SFBinaryValue;

/**
 * Binary representation of a 3x3 rotation matrix, base on the ZXY Left-handed positive sign convention
 * 
 * Matrix will be:
 * [ A:c1c3-s1s2s3  B:-c2s1  C:c1s3+c3s1s2
 *   D:c3s1+c1s2s3  E:c1c2  F:s1s3-c1c3s2
 *   G:-c2s3   H:s2   I:c2c3]
 *   <br/>
 *   where:
 *   <ul>
 *      <li>c1=cos(zangle),s1=sin(zangle)</li>
 *      <li>c2=cos(xangle),s2=sin(xangle)</li>
 *      <li>c3=cos(yangle),s3=sin(yangle)</li>
 *   </ul>
 * 
 * @author Alessandro
 */
public class SFBinaryRotationMatrix3fLQ extends SFBinaryValue{
	
	@Override
	public SFBinaryValue clone() {
		return new SFBinaryRotationMatrix3fLQ();
	}
	
	@Override
	public int getBitSize() {
		return 24;
	}

	public void getMatrix3f(SFMatrix3f write){

		int indexA=getValue()>>16;
		int indexB=(getValue()>>8) & 0xff;
		int indexC=getValue() & 0xff;

		float c1=SFStaticAnglesSet.getAngleslq().getCos(indexA);
		float s1=SFStaticAnglesSet.getAngleslq().getSin(indexA);
		float c2=SFStaticAnglesSet.getAngleslq().getCos(indexB);
		float s2=SFStaticAnglesSet.getAngleslq().getSin(indexB);
		float c3=SFStaticAnglesSet.getAngleslq().getCos(indexC);
		float s3=SFStaticAnglesSet.getAngleslq().getSin(indexC);
		
		write.setA(c1*c3-s1*s2*s3);
		write.setB(-c2*s1);
		write.setC(c1*s3+c3*s1*s2);
		write.setD(c3*s1+c1*s2*s3);
		write.setE(c1*c2);
		write.setF(s1*s3-c1*c3*s2);
		write.setG(-c2*s3);
		write.setH(s2);
		write.setI(c2*c3);
		
		for (int i = 0; i < 9; i++) {
			if(SFStaticAnglesSet.getAngleslq().equal(0, write.get()[0]))
				write.get()[i]=0;	
		}
	}
	
	public void setMatrix3f(SFMatrix3f read){

		int indexA=0;
		int indexB=0;
		int indexC=0;
		
		//Retrieve s2 from H and evaluate c2
		float s2=read.getH();
		float c2=(float)(Math.sqrt(1-s2*s2));
		float c1=0,s1=0,c3=0,s3=0;

		indexB=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c2, s2);
		
		if(SFStaticAnglesSet.getAngleslq().equal(c2, 0)){//c2==0
			
			//when c2=0, its a degenerate matrix.
			//			B=E=G=I=0, H=s2=+1,-1
			//			A=c1*c3-+s1*s3;
			//			C=c1*s3+-c3*s1;
			//			D=c3*s1+-c1*s3;
			//			F=s1*s3-+c1*c3;
			//			That's: A=-F, C=-D,  A,C,D,F is a planar rotational matrix, (c1,s1) and (c3,s3) stays for 2 rotation onto the same plane.
			//Then, there are infinite solutions, and without loss of generality, we can assume c3=1, s3=0;
			c3=1;
			s3=0;
			//and now we have
			//		A=c1,D=s1
			c1=read.getA();
			s1=read.getD();
			
		}else{//c3 is not 0
			float recC2=1.0f/c2;
			//get (c3,s3) from G,I
			c3=read.getI()*recC2;
			s3=-read.getG()*recC2;
			//get (c1,s1) from E,B
			c1=read.getE()*recC2;
			s1=-read.getB()*recC2;
//			 * [ A:c1c3-s1s2s3  B:-c2s1  C:c1s3+c3s1s2
//			 *   D:c3s1+c1s2s3  E:c1c2  F:s1s3-c1c3s2
//			 *   G:-c2s3   H:s2   I:c2c3]
		}

		indexA=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c1, s1);
		indexC=SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c3, s3);
		this.value=(indexA<<16)+(indexB<<8)+indexC;
	}
}

