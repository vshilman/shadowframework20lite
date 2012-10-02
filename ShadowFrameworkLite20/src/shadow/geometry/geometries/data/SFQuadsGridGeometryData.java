package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloatArray;
import shadow.system.data.objects.SFShortByteField;
import shadow.system.data.objects.SFString;

public class SFQuadsGridGeometryData extends SFDataAsset<SFGeometry>{

	private SFFloatArray u_splits=new SFFloatArray(0);
	private SFFloatArray v_splits=new SFFloatArray(0);
	private SFShortByteField NuNv=new SFShortByteField((short)0);
	private SFString primitive=new SFString();
		
	private SFParametrizedGeometry surfaceGeometry;
	
	public SFQuadsGridGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("u_splits", u_splits);
		parameters.addObject("v_splits", v_splits);
		parameters.addObject("NuNv", NuNv);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}

	@Override
	protected SFParametrizedGeometry buildResource() {
		
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry();
		
		if(u_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits( u_splits.getFloatValues());
		}else{
			gridGeometry.getQuadsGrid().setNu( NuNv.getByte(0));
		}
		if(v_splits.getFloatValues().length!=0){
			gridGeometry.getQuadsGrid().setU_splits( v_splits.getFloatValues());
		}else{
			gridGeometry.getQuadsGrid().setNv( NuNv.getByte(1));
		}
		
		surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
		SFPrimitive primitive=SFPipeline.getPrimitive(this.primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
		
		return surfaceGeometry;
	}

	public SFFloatArray getU_splits() {
		return u_splits;
	}

	public SFFloatArray getV_splits() {
		return v_splits;
	}

	public void setNu(int nu){
		NuNv.setByte(1, nu);
	}
	
	public void setNv(int nv){
		NuNv.setByte(0, nv);
	}
	
	public int getNu() {
		return NuNv.getByte(1);
	}

	public int getNv() {
		return NuNv.getByte(0);
	}
	
	public void setNuNv(int nu,int nv){
		setNu(nu);
		setNv(nv);
	}

	public SFString getPrimitive() {
		return primitive;
	}


}
