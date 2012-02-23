package shadow.renderer;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class SFCamera {

	private SFVertex3f F=new SFVertex3f();
	private SFVertex3f Dir=new SFVertex3f();
	private SFVertex3f Up=new SFVertex3f();
	private SFVertex3f Left=new SFVertex3f();
	
	private float leftL=0.3f;
	private float upL=0.3f;
	
	private float distance;
	private float delta;
	
	private boolean isChanged=false;
	
	private boolean isPerspective=false;
	
	public SFCamera(SFVertex3f focus, SFVertex3f dir, SFVertex3f left, SFVertex3f up, float leftL,
			float upL,float distance,float delta) {
		super();
		getF().set(focus);
		getDir().set(dir);
		getLeft().set(left);
		getUp().set(up);
		this.setLeftL(leftL);
		this.setUpL(upL);
		this.setDistance(distance);
		update();
	}
	
	/*public void draw(GL2 gl){
		
		float val=1.01f;
		
		gl.glBegin(GL2.GL_LINES);
			gl.glVertex3f(F.x,F.y,F.z);
			gl.glVertex3f(F.x+Dir.x*val,F.y+Dir.y*val,F.z+Dir.z*val);
		gl.glEnd();
		gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glVertex3f(F.x+Dir.x*val+Left.x+Up.x,F.y+Dir.y*val+Left.y+Up.y,F.z+Dir.z*val+Left.z+Up.z);
			gl.glVertex3f(F.x+Dir.x*val+Left.x-Up.x,F.y+Dir.y*val+Left.y-Up.y,F.z+Dir.z*val+Left.z-Up.z);
			gl.glVertex3f(F.x+Dir.x*val-Left.x-Up.x,F.y+Dir.y*val-Left.y-Up.y,F.z+Dir.z*val-Left.z-Up.z);
			gl.glVertex3f(F.x+Dir.x*val-Left.x+Up.x,F.y+Dir.y*val-Left.y+Up.y,F.z+Dir.z*val-Left.z+Up.z);
		gl.glEnd();
	}*/

	public SFVertex3f getDir() {
		return Dir;
	}

	public void setDir(SFVertex3f dir) {
		Dir = dir;
		setDelta((float)Math.sqrt(dir.dot3f(dir)));
	}
	
//	public void setDirLength(float dirLength){
//		Dir.normalize();
//		Dir.mult(dirLength);
//	}
	
	public void update(){	
		//Left.set3f(Dir.getZ(),0,-Dir.getX());
		getLeft().normalize3f();
		//Up=Dir.cross(Left);
		getUp().normalize3f();
		getLeft().mult(getLeftL());
		getUp().mult(getUpL());
		getDir().normalize3f();
		//getDir().mult(1/getDistance());
		setChanged(true);
	}
	
//	public void setupCamera(GL2 gl){v
//		
//		extractTransform(matrix);
//		
//		gl.glMatrixMode(GL2.GL_PROJECTION);
//		gl.glMultMatrixf(matrix, 0);
//	}

	public float[] extractTransform() {		
		
		float matrix[]=new float[16];
		
		SFMatrix3f mat=new SFMatrix3f(
				getLeft().getX(),getUp().getX(),getDir().getX(),
				getLeft().getY(),getUp().getY(),getDir().getY(),
				getLeft().getZ(),getUp().getZ(),getDir().getZ()
		);

		setDelta((float)Math.sqrt(getDir().dot3f(getDir())));
		mat=SFMatrix3f.getInverse(mat);
		
		matrix[0]=mat.getA();
		matrix[1]=mat.getD();
		matrix[2]=mat.getG()*getDelta();
		matrix[3]=0;
		
		matrix[4]=mat.getB();
		matrix[5]=mat.getE();
		matrix[6]=mat.getH()*getDelta();
		matrix[7]=0;
		
		matrix[8]=mat.getC();
		matrix[9]=mat.getF();
		matrix[10]=mat.getI()*getDelta();
		matrix[11]=0;
		
		matrix[12]=-(matrix[0]*getF().getX()+matrix[4]*getF().getY()+matrix[8]*getF().getZ());
		matrix[13]=-(matrix[1]*getF().getX()+matrix[5]*getF().getY()+matrix[9]*getF().getZ());
		matrix[14]=-(matrix[2]*getF().getX()+matrix[6]*getF().getY()+matrix[10]*getF().getZ());
		matrix[15]=1;
		
		if(isPerspective()){
			
			float al=(getDistance()+getDelta())/(getDistance()-getDelta());
			float bl=(-2*getDistance()*getDelta())/(getDistance()-getDelta());
		
			matrix[0]=getDelta()*matrix[0];
			matrix[1]=getDelta()*matrix[1];
			matrix[3]=matrix[2];
			matrix[2]=al*matrix[2];
			
			matrix[4]=getDelta()*matrix[4];
			matrix[5]=getDelta()*matrix[5];
			matrix[7]=matrix[6];
			matrix[6]=al*matrix[6];
	
			matrix[8]=getDelta()*matrix[8];
			matrix[9]=getDelta()*matrix[9];
			matrix[11]=matrix[10];
			matrix[10]=al*matrix[10];
	
			matrix[12]=getDelta()*matrix[12];
			matrix[13]=getDelta()*matrix[13];
			matrix[15]=matrix[14];
			matrix[14]=al*matrix[14]+bl;

		}
		return matrix;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public boolean isPerspective() {
		return isPerspective;
	}

	public void setPerspective(boolean isPerspective) {
		this.isPerspective = isPerspective;
	}

	public SFVertex3f getWorldPosition(SFVertex3f cameraPosition){
		
		SFVertex3f position=new SFVertex3f(getF());
		position.addMult(getDistance()+cameraPosition.getZ(),getDir());
		position.addMult((float)(getLeftL()*cameraPosition.getX()),getLeft());
		position.addMult((float)(getUpL()*cameraPosition.getY()),getUp());
		
		return position;
	}
	
	public SFMatrix3f getWorldRotation(SFMatrix3f matrixOrientation){
		
		SFMatrix3f cameraMatrix=new SFMatrix3f(
					getLeft().getX(),getUp().getX(),getDir().getX(),
					getLeft().getY(),getUp().getY(),getDir().getY(),
					getLeft().getZ(),getUp().getZ(),getDir().getZ()
				);
		
		return SFMatrix3f.getTransposed(cameraMatrix).Mult(matrixOrientation.Mult(cameraMatrix));
	}

	public SFVertex3f getF() {
		return F;
	}

	public void setF(SFVertex3f f) {
		F = f;
	}

	public SFVertex3f getUp() {
		return Up;
	}

	public void setUp(SFVertex3f up) {
		Up = up;
	}

	public SFVertex3f getLeft() {
		return Left;
	}

	public void setLeft(SFVertex3f left) {
		Left = left;
	}

	public float getLeftL() {
		return leftL;
	}

	public void setLeftL(float leftL) {
		this.leftL = leftL;
	}

	public float getUpL() {
		return upL;
	}

	public void setUpL(float upL) {
		this.upL = upL;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}

}
