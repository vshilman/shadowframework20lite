package shadow.math;

public abstract class SFValuenf {

	protected float[] v;
	
	public SFValuenf(int n) {
		super();
		this.v = new float[n];
	}

	public SFValuenf(SFValuenf value) {
		super();
		this.v = new float[value.v.length];
		set(value.v);
	}
	
	public int getSize(){
		return v.length;
	}

	public float[] get() {
		return v;
	}
	
	public void setArray(float[] data) {
		this.v=data;
	}

	public void set(float[] data) {
		for (int i = 0; i < data.length; i++) {
			v[i]=data[i];
		}
	}
	
	public abstract SFValuenf cloneValue();
	

	public void mult(float m) {
		for (int i = 0; i < v.length; i++) {
			v[i]*=m;
		}
	}

	public void addMult(float m,SFValuenf value) throws ArrayIndexOutOfBoundsException{
		for (int i = 0; i < v.length; i++) {
			v[i]+=m*value.v[i];
		}
	}
	
	public void set(SFValuenf value) throws ArrayIndexOutOfBoundsException{
		for (int i = 0; i < v.length; i++) {
			v[i]=value.v[i];
		}
	}
}