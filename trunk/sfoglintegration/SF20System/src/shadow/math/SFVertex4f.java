package shadow.math;

/**
 * @template Data_Structure
 * 
 * @author Alessandro Martinelli
 */
public class SFVertex4f extends SFValue{

	/**
	 * Generate the middle Point between two poits A and B
	 * 
	 * @param A
	 *            the first Point
	 * @param B
	 *            the second Point
	 * @return the middle Point
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static SFVertex4f middle(SFVertex4f A,SFVertex4f B){
		return new SFVertex4f(
				(A.getV()[0]+B.getV()[0])*0.5f,(A.getV()[1]+B.getV()[1])*0.5f,(A.getV()[2]+B.getV()[2])*0.5f,(A.getV()[3]+B.getV()[3])*0.5f
		);
	}
	
	/**
	 * Create a new 4f value, assigning it (0,0,0,0)
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public SFVertex4f() {
		this(0,0,0,0);
	}

	
	public SFVertex4f(SFVertex3f vertex) {
		set(vertex);
	}
	
	public SFVertex4f(float[] values) {
	
	}

	/**
	 * Create a new 4f value, assigning it
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public SFVertex4f(float x, float y, float z, float w) {
		this.set4f(x,y,z,w);
	}
	
	public static SFVertex4f linearVertex(float t1,SFVertex4f v1,SFVertex4f v2){
		SFVertex4f v=new SFVertex4f();
		v.addMult4f(1-t1,v1);
		v.addMult4f(t1,v2);
		return v;
	}

	float[] v=new float[4];

	@Override
	public int getSize() {
		return 4;
	}
	
	@Override
	public float[] getV() {
		return v;
	}
	/**
	 * Specific 4f version of the add method.
	 * @param vx
	 *            the constant to be added
	 */
	public void add4f(SFVertex4f q){
		getV()[0]+=q.getV()[0];
		getV()[1]+=q.getV()[1];
		getV()[2]+=q.getV()[2];
		getV()[3]+=q.getV()[3];
	}
	
	/**
	 * Specific 4f version of the addMult method.
	 * @param a
	 * @param vx
	 */
	public void addMult4f(float a,SFVertex4f vx){
		getV()[0]+=vx.getV()[0]*a;
		getV()[1]+=vx.getV()[1]*a;
		getV()[2]+=vx.getV()[2]*a;
		getV()[3]+=vx.getV()[3]*a;
	}
	
	public SFVertex4f cloneValue() {
		return new SFVertex4f(getV()[0],getV()[1],getV()[2],getV()[3]);
	}
	
	/**
	 * Specific 4f version of the dot method.
	 * @param vx
	 * @return
	 */
	public double dot4f(SFVertex4f vx){
		return vx.getV()[0]*getV()[0]+vx.getV()[1]*getV()[1]+vx.getV()[2]*getV()[2]+vx.getV()[3]*getV()[3];
	}
	
	/**
	 * @return the w value
	 */
	public float getW(){
		return getV()[3];
	}
	
	/**
	 * @return the x value
	 */
	public float getX(){
		return this.getV()[0];
	}
	
	/**
	 * @return the y value
	 */
	public float getY(){
		return this.getV()[1];
	}
	
	/**
	 * @return the z value
	 */
	public float getZ(){
		return this.getV()[2];
	}
	
	/**
	 * Specific 4f version of the mult method.
	 * @param m
	 */
	public void mult4f(float a){
		getV()[0]*=a;
		getV()[1]*=a;
		getV()[2]*=a;
		getV()[3]*=a;
	}
	
	/**
	 * Set this vector-vertex to be a unit vector with the same direction
	 * @param m
	 */	
	public void normalize4f(){
		float lengthRec=1/(float)(Math.sqrt(getV()[0]*getV()[0]+getV()[1]*getV()[1]+getV()[2]*getV()[2]+getV()[3]*getV()[3]));
		getV()[0]*=lengthRec;
		getV()[1]*=lengthRec;
		getV()[2]*=lengthRec;
		getV()[3]*=lengthRec;
	}
	

	/**
	 * Scale this vector
	 * This.x=This.x*sx
	 * This.y=This.y*sy
	 * This.z=This.z*sz
	 * This.w=This.w*sw
	 * 
	 * @param sx
	 * @param sy
	 * @param sz
	 * @param sw
	 */
	public void scale4f(float sx,float sy,float sz,float sw){
		this.getV()[0]*=sx;
		this.getV()[1]*=sy;
		this.getV()[2]*=sz;
		this.getV()[3]*=sw;
	}

	/**
	 * Set the elements (x,y,z,w)
	 */
	public void set4f(float x,float y,float z,float w){
		this.getV()[0]=x;
		this.getV()[1]=y;
		this.getV()[2]=z;
		this.getV()[3]=w;
	}

	public void setW(float w){
		getV()[3]=w;
	}
	
	public void setX(float x){
		this.getV()[0]=x;
	}
	
	
	public void setY(float y){
		this.getV()[1]=y;
	}
	
	public void setZ(float z){
		this.getV()[2]=z;
	}
	
	/**
	 * Specific 4f version of the subtract method.
	 * @param vx
	 */
	public void subtract4f(SFVertex4f q){
		getV()[0]-=q.getV()[0];
		getV()[1]-=q.getV()[1];
		getV()[2]-=q.getV()[2];
		getV()[3]-=q.getV()[3];
	}
}
