package shadow.geometry.functions.data;

import shadow.geometry.functions.SFNormalBasedObjPlaneTexCoordGeometry;
import shadow.geometry.geometries.SFDerivedTexCoordFunctionuv;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;

public class SFNormalBasedObjPlaneTexCoordGeometryData extends SFDataAsset<SFDerivedTexCoordFunctionuv>{

	private SFFloat a=new SFFloat(1);
	private SFFloat du=new SFFloat(0);
	private SFFloat av=new SFFloat(0);
	private SFFloat bv=new SFFloat(1);
	private SFFloat cv=new SFFloat(0);
	private SFFloat dv=new SFFloat(0);
	
	public SFNormalBasedObjPlaneTexCoordGeometryData(){
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("a", a);
		parametersObject.addObject("du", du);
		parametersObject.addObject("av", av);
		parametersObject.addObject("bv", bv);
		parametersObject.addObject("cv", cv);
		parametersObject.addObject("dv", dv);
		setData(parametersObject);
	}
	
	@Override
	public SFNormalBasedObjPlaneTexCoordGeometry buildResource() {
		return new SFNormalBasedObjPlaneTexCoordGeometry(a.getFloatValue(), du.getFloatValue(),
				av.getFloatValue(), bv.getFloatValue(), cv.getFloatValue(), dv.getFloatValue());
	}


}
