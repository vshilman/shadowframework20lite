package shadow.geometry.functions.data;

import shadow.geometry.functions.SFSimpleObjPlaneTexCoordGeometry;
import shadow.geometry.geometries.SFDerivedTexCoordFunctionuv;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;

public class SFSimpleObjPlaneTexCoordGeometryData extends SFDataAsset<SFDerivedTexCoordFunctionuv>{

	private SFFloat au=new SFFloat(1);
	private SFFloat bu=new SFFloat(0);
	private SFFloat cu=new SFFloat(0);
	private SFFloat du=new SFFloat(0);
	private SFFloat av=new SFFloat(0);
	private SFFloat bv=new SFFloat(1);
	private SFFloat cv=new SFFloat(0);
	private SFFloat dv=new SFFloat(0);
	
	public SFSimpleObjPlaneTexCoordGeometryData(){
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("au", au);
		parametersObject.addObject("bu", bu);
		parametersObject.addObject("cu", cu);
		parametersObject.addObject("du", du);
		parametersObject.addObject("av", av);
		parametersObject.addObject("bv", bv);
		parametersObject.addObject("cv", cv);
		parametersObject.addObject("dv", dv);
		setData(parametersObject);
	}
	
	@Override
	public SFSimpleObjPlaneTexCoordGeometry buildResource() {
		return new SFSimpleObjPlaneTexCoordGeometry(au.getFloatValue(), bu.getFloatValue(), cu.getFloatValue(), du.getFloatValue(),
				av.getFloatValue(), bv.getFloatValue(), cv.getFloatValue(), dv.getFloatValue());
	}

	public void setAu(float au) {
		this.au.setFloatValue(au);
	}
	
	public void setBu(float bu) {
		this.bu.setFloatValue(bu);
	}
	
	public void setCu(float cu) {
		this.cu.setFloatValue(cu);
	}

	public void setAv(float av) {
		this.av.setFloatValue(av);
	}
	
	public void setBv(float bv) {
		this.bv.setFloatValue(bv);
	}
	
	public void setCv(float cv) {
		this.cv.setFloatValue(cv);
	}

	
}
