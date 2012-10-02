package shadow.math;

public class SFValuenf {

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

	public static SFValuenf middle(SFValuenf A,SFValuenf B) throws ArrayIndexOutOfBoundsException{
		SFValuenf value=A.cloneValue();
		for (int i = 0; i < A.v.length; i++) {
			value.v[i]=(A.v[i]+B.v[i])*0.5f;
		}
		return value;
	}
	
	public void setArray(float[] data) {
		this.v=data;
	}
	
	public void set(float... data) {
		for (int i = 0; i < data.length; i++) {
			v[i]=data[i];
		}
	}
	
	public float dot(SFValuenf value) {
		float dot=0;
		for (int i = 0; i < v.length; i++) {
			dot+=v[i]*value.v[i];
		}
		return dot;
	}

	public void subtract(SFValuenf value) {
		for (int i = 0; i < v.length; i++) {
			v[i]-=value.v[i];
		}
	}
	
	public SFValuenf cloneValue(){
		SFValuenf v=new SFValuenf(this.v.length);
		v.set(this);
		return v;
	}

	public void mult(float m) {
		for (int i = 0; i < v.length; i++) {
			v[i]*=m;
		}
	}
	
	public void mult(SFValuenf value) throws ArrayIndexOutOfBoundsException{
		for (int i = 0; i < v.length; i++) {
			v[i]*=value.v[i];
		}
	}
	
	public void add(SFValuenf value) throws ArrayIndexOutOfBoundsException{
		for (int i = 0; i < v.length; i++) {
			v[i]+=value.v[i];
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
	
	public void set(int index,float value) throws ArrayIndexOutOfBoundsException{
		v[index]=value;
	}
	
	@Override
	public String toString() {
		String s="[";
		for (int i = 0; i < v.length-1; i++) {
			s+=v[i]+", ";
		}
		s+=v[v.length-1]+"]";
		return s;
	}
}