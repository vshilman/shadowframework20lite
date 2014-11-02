package shadow.system.data.objects;

import shadow.math.SFMatrix3f;

public class SFMatrix3fData extends SFVectorData{
	
	private SFMatrix3f matrix;
	
	public SFMatrix3fData(SFMatrix3f matrix) {
		super(9);
		this.matrix=new SFMatrix3f();
		this.matrix.setArray(getFloatValues());
		this.matrix.set(matrix);
	}
	
	public SFMatrix3fData() {
		super(9);
		matrix=new SFMatrix3f(getFloatValues());
		this.matrix.set(SFMatrix3f.getIdentity());
	}

	public SFMatrix3f getMatrix3f(){
		return matrix;
	}
	
	@Override
	public SFVectorData copyDataObject() {
		return new SFMatrix3fData();
	}
}
