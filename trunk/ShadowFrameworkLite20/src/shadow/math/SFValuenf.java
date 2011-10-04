package shadow.math;

public abstract class SFValuenf {

	protected float[] v;

	public SFValuenf(int n) {
		super();
		this.v = new float[n];
	}
	
	public int getSize(){
		return v.length;
	}

	public float[] get() {
		return v;
	}

	public void set(float[] data) {
		for (int i = 0; i < data.length; i++) {
			v[i]=data[i];
		}
	}
	
	public abstract SFValuenf cloneValue();
}