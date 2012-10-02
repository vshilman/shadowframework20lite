package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

/**
 * Important 
 * B.getTmin()==C.getTmin();
 * B.getTmax()==C.getTmax();
 */
public class SFGuidedSurface  extends SFUnoptimizedSurfaceFunction{

	private SFCurve A;
	private SFCurve B;
	private SFCurve C;
	
	private float maxTA;
	private float maxTB;
	
	public SFGuidedSurface(SFCurve a, SFCurve b, SFCurve c) {
		super();
		this.A = a;
		this.B = b;
		this.C = c;
		maxTA=A.getTMax();
		maxTB=B.getTMax();
		
		evalTransform(startingTransform, 0);
		
		getTransformInverse(startingTransform);

	}

	
	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
	
	private float[] startingTransform=new float[16];
	private float[] actualTransform=new float[16];
	private float x,y,z;
	
	private void evalTransform(float transform[],float t){
		
		SFVertex3f pos=new SFVertex3f();
		B.getVertex(t, pos);
		SFVertex3f CB=new SFVertex3f();
		C.getVertex(t, pos);
		CB.subtract(pos);
		SFVertex3f T=new SFVertex3f();
		B.getDevDt(t, pos);
		T.normalize3f();
		SFVertex3f B=CB.cross(T);
		B.normalize3f();
		
		
		transform[0]=T.getX();transform[1]=CB.getX();transform[2]=B.getX();transform[3]=pos.getX();
		transform[4]=T.getY();transform[5]=CB.getY();transform[6]=B.getY();transform[7]=pos.getY();
		transform[8]=T.getZ();transform[9]=CB.getZ();transform[10]=B.getZ();transform[11]=pos.getZ();
		transform[12]=0;transform[13]=0;transform[14]=0;transform[15]=1;
	}
	
	private void getTransformInverse(float transform[]){

		
		SFMatrix3f mat=new SFMatrix3f();
		mat.setA(transform[0]);
		mat.setB(transform[1]);
		mat.setC(transform[2]);
		mat.setD(transform[4]);
		mat.setE(transform[5]);
		mat.setF(transform[6]);
		mat.setG(transform[8]);
		mat.setH(transform[9]);
		mat.setI(transform[10]);
		mat=SFMatrix3f.getInverse(mat);
		transform[0]=mat.getA();
		transform[1]=mat.getB();
		transform[2]=mat.getC();
		transform[4]=mat.getD();
		transform[5]=mat.getE();
		transform[6]=mat.getF();
		transform[8]=mat.getG();
		transform[9]=mat.getH();
		transform[10]=mat.getI();
		
		float x=-(transform[0]*transform[3]+transform[1]*transform[7]+transform[2]*transform[11]);
		float y=-(transform[4]*transform[3]+transform[5]*transform[7]+transform[6]*transform[11]);
		float z=-(transform[8]*transform[3]+transform[9]*transform[7]+transform[10]*transform[11]);
		
		transform[3]=x;
		transform[7]=y;
		transform[11]=z;

	}
	
	@Override
	public float getX(float u, float v) {
		
		u*=maxTA;
		v*=maxTB;
		
		evalTransform(actualTransform, v);
		SFVertex3f vertex=new SFVertex3f();
		A.getVertex(u, vertex);
		float x=vertex.getX();
		float y=vertex.getY();
		float z=vertex.getZ();
		
		this.x=startingTransform[0]*x+startingTransform[1]*y+startingTransform[2]*z+startingTransform[3];
		this.y=startingTransform[4]*x+startingTransform[5]*y+startingTransform[6]*z+startingTransform[7];
		this.z=startingTransform[8]*x+startingTransform[9]*y+startingTransform[10]*z+startingTransform[11];
		
		return actualTransform[0]*this.x+actualTransform[1]*this.y+
				actualTransform[2]*this.z+actualTransform[3];
	}
	
	@Override
	public float getY(float u, float v) {

		return actualTransform[4]*this.x+actualTransform[5]*this.y+
				actualTransform[6]*this.z+actualTransform[7];
	}
	
	@Override
	public float getZ(float u, float v) {

		return actualTransform[8]*this.x+actualTransform[9]*this.y+
				actualTransform[10]*this.z+actualTransform[11];
	}
	
	
}
