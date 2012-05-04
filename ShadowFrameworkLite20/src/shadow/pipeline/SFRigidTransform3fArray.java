package shadow.pipeline;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.system.SFArray;

public interface SFRigidTransform3fArray extends SFArray<SFTransform3f>{
	
	public void apply(int index);
	
	public void attach(SFRigidTransform3fArray sonArray,int sonIndex,int fatherIndex);
	
	public void setElementPosition(int index,SFVertex3f vertex);
	
	public void setElementOrientation(int index,SFMatrix3f matrix);

	public void getElementPosition(int index,SFVertex3f vertex);
	
	public void getElementOrientation(int index,SFMatrix3f matrix);
}
