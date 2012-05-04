package shadow.geometry.data;

import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFSimpleTexCoordGeometryuvData extends SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>{

	private SFFloat du=new SFFloat(1);
	private SFFloat dv=new SFFloat(1);
	
	public SFSimpleTexCoordGeometryuvData(){
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(du,dv));
	}
	
	public SFSimpleTexCoordGeometryuvData(float du,float dv){
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(this.du,this.dv));
		setDu(du);
		setDv(dv);
	}
	
	@Override
	protected SFSimpleTexCoordGeometryuv buildResource() {
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
