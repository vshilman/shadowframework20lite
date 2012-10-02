package shadow.math;

/**
 * Binary representation of a 3x3 rotation matrix, based on the ZXY Left-handed positive sign convention
 * 
 * Related Rotation Matrix will be:
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
public class SFEulerAngles3f extends SFVertex3f{

	public SFEulerAngles3f() {
		super();
	}

	public SFEulerAngles3f(double z, double x, double y) {
		super(x, y, z);
	}

	public SFEulerAngles3f(float z, float x, float y) {
		super(x, y, z);
	}

	public SFEulerAngles3f(SFEulerAngles3f vx) {
		super(vx);
	}

	public SFEulerAngles3f(SFMatrix3f matrix) {
		super();
		setMatrix(matrix);
	}
	
	public void getMatrix(SFMatrix3f matrix){

		float c1=(float)(Math.cos(v[0]));
		float s1=(float)(Math.sin(v[0]));
		float c2=(float)(Math.cos(v[1]));
		float s2=(float)(Math.sin(v[1]));
		float c3=(float)(Math.cos(v[2]));
		float s3=(float)(Math.sin(v[2]));

		matrix.setA(c1*c3-s1*s2*s3);
		matrix.setB(-c2*s1);
		matrix.setC(c1*s3+c3*s1*s2);
		matrix.setD(c3*s1+c1*s2*s3);
		matrix.setE(c1*c2);
		matrix.setF(s1*s3-c1*c3*s2);
		matrix.setG(-c2*s3);
		matrix.setH(s2);
		matrix.setI(c2*c3);

	}
	
	public void setMatrix(SFMatrix3f matrix){

		//Retrieve s2 from H and evaluate c2
		float s2=matrix.getH();
		float c2=(float)(Math.sqrt(1-s2*s2));
		float c1=0,s1=0,c3=0,s3=0;

		v[1]=(float)(Math.atan2(s2, c2));//SFStaticAnglesSet.getAngleslq().getIndexByTrigonometricValues(c2, s2);
		
		if(c2==0){
			
			c3=1;
			s3=0;
			c1=matrix.getA();
			s1=matrix.getD();
		}else{
			float recC2=1.0f/c2;
			//get (c3,s3) from G,I
			c3=matrix.getI()*recC2;
			s3=-matrix.getG()*recC2;
			//get (c1,s1) from E,B
			c1=matrix.getE()*recC2;
			s1=-matrix.getB()*recC2;
		}
		
		v[0]=(float)(Math.atan2(s1, c1));
		v[2]=(float)(Math.atan2(s3, c3));	
	}
	
	
}
