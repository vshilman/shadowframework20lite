package shadow.math;

public class SFRigidTransform2f extends SFValuenf{

	public SFRigidTransform2f() {
		super(6);
	}

	@Override
	public SFValuenf cloneValue() {
		SFRigidTransform2f transform=new SFRigidTransform2f();
		transform.set(this);
		return transform;
	}
	
	public void setPosition(float x,float y){
		v[4]=x;
		v[5]=y;
	}
	
	public void setMatrix(float a,float b,float c,float d){
		v[0]=a;
		v[1]=b;
		v[2]=c;
		v[3]=d;
	}
	
	public void setMatrix(SFMatrix2f matrix){
		for (int i = 0; i < matrix.v.length; i++) {
			this.v[i]=matrix.v[i];
		}
	}
	
	public void setPosition(SFVertex2f position){
		for (int i = 0; i < position.v.length; i++) {
			this.v[i+4]=position.v[i];
		}
	}
	
	public void getMatrix(SFMatrix2f matrix){
		for (int i = 0; i < matrix.v.length; i++) {
			matrix.v[i]=this.v[i];
		}
	}
	
	public void getPosition(SFVertex2f position){
		for (int i = 0; i < position.v.length; i++) {
			position.v[i]=this.v[i+4];
		}
	}
	
}
