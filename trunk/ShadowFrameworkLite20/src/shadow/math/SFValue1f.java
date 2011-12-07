package shadow.math;

public class SFValue1f extends SFValuenf{
	
	/*@note package visibility*/
	SFValue1f(int n) {
		super(n);
	}
	
	public SFValue1f(float x) {
		super(1);
		this.v[0] = x;
	}
	
	public SFValue1f(double x) {
		super(1);
		this.v[0] = (float)x;
	}
	
	public void storeOn1f(float []f){
		f[0]=v[0];
	}
	
	public float getX(){
		return v[0];
	}
	
	public void setX(float x){
		v[0]=x;
	}

	public void mult1f(float m){
		v[0]*=m;
	}
	
	public void add1f(float dX){
		v[0]+=dX;
	}
	
	public void subtract1f(float dX){
		v[0]+=dX;
	}
	
	public void setByIndex(int index,float val){
		if(index==0)
			v[0]=val;
	}

	@Override
	public SFValuenf cloneValue() {
		return new SFValue1f(this.v[0]);
	}
	
}
