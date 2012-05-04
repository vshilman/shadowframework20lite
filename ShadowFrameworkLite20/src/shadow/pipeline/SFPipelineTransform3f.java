package shadow.pipeline;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class SFPipelineTransform3f {

	private SFRigidTransform3fArray array;
	private int index;
	
	public SFPipelineTransform3f(SFRigidTransform3fArray array, int index) {
		super();
		this.array = array;
		this.index = index;
	}
	
	public void attachOn(SFPipelineTransform3f transform){
		transform.array.attach(array, index, transform.index);
	}
	
	public void setPosition(SFVertex3f vertex){
		array.setElementPosition(index, vertex);
	}
	
	public void setOrientation(SFMatrix3f matrix){
		array.setElementOrientation(index, matrix);
	}

	public void getPosition(SFVertex3f vertex){
		array.getElementPosition(index, vertex);
	}
	
	public void getOrientation(SFMatrix3f matrix){
		array.getElementOrientation(index, matrix);
	}
	
	public void apply(){
		array.apply(index);
	}
}
