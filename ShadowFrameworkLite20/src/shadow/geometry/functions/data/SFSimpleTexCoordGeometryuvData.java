package shadow.geometry.functions.data;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSimpleTexCoordGeometryuv;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;

public class SFSimpleTexCoordGeometryuvData extends SFDataAsset<SFSurfaceFunction>{

	private SFFloat du=new SFFloat(1);
	private SFFloat dv=new SFFloat(1);
	
	public SFSimpleTexCoordGeometryuvData(){
		prepare();
	}
	
	public SFSimpleTexCoordGeometryuvData(float du,float dv){
		prepare();
		setDu(du);
		setDv(dv);
	}

	private void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("du", du);
		parameters.addObject("dv", dv);
		setData(parameters);
	}
	
	@Override
	protected SFSurfaceFunction buildResource() {
		return new SFSimpleTexCoordGeometryuv(du.getFloatValue(), dv.getFloatValue());
	}

	public void set(float du,float dv) {
		this.du.setFloatValue(du);
		this.dv.setFloatValue(dv);
	}
	
	public void setDu(float du) {
		this.du.setFloatValue(du);
	}

	public void setDv(float dv) {
		this.dv.setFloatValue(dv);
	}
}
