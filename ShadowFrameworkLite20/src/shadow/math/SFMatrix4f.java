package shadow.math;

public class SFMatrix4f extends SFValuenf{

	@Override
	public SFValuenf cloneValue() {
		return new SFMatrix4f(
				v[0],v[1],v[2],v[3],
				v[4],v[5],v[6],v[7],
				v[8],v[9],v[10],v[11],
				v[12],v[13],v[14],v[15]);
	}
	
	
	public SFMatrix4f(float a,float b,float c,float d,
					float e,float f,float g,float h,
					float i,float l,float m,float n,
					float o,float p,float q,float r) {
		super(16);
		v[0]=a;v[1]=b;v[2]=c;v[3]=d;
		v[4]=e;v[5]=f;v[6]=g;v[7]=h;
		v[8]=i;v[9]=l;v[10]=m;v[11]=n;
		v[12]=o;v[13]=p;v[14]=q;v[15]=r;
	}
	
	public static SFMatrix4f getIdentity(){
		SFMatrix4f n=new SFMatrix4f(
				1,0,0,0,
				0,1,0,0,
				0,0,1,0,
				0,0,0,1
		);

		return n;
	}
	
	//TODO: this class need to be finished	
}
