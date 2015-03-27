package shadow.integration.data;

import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFVertex3fData;

public class Transform3fData extends SFDataAsset<SFTransform3f>{

	public Transform3fData(){
		setName("Transform3f");
		SFVertex3fData position=new SFVertex3fData();
		SFVertex3fData orientation=new SFVertex3fData();
		SFFloat scale=new SFFloat(1);
		addObject("position", position);
		addObject("orientation", orientation);
		addObject("scale", scale);
	}
	
	@Override
	public SFTransform3f createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		SFTransform3f resource=new SFTransform3f();
		SFVertex3fData position=sfDataObject.getDataObject(0);
		SFVertex3fData orientation=sfDataObject.getDataObject(1);
		SFFloat scale=sfDataObject.getDataObject(2);
		SFMatrix3f matrix=new SFMatrix3f();
		SFEulerAngles3f angles=new SFEulerAngles3f();
		angles.set(orientation.getVertex3f());
		angles.getMatrix(matrix);
		matrix.mult(scale.getFloatValue());
		resource.setMatrix(matrix);
		resource.setPosition(position.getVertex3f());
		return resource;
	}
}
