package shadow.geometry.data;

import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.geometry.geometries.SFSimpleObjPlaneTexCoordGeometry;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFSimpleObjPlaneTexCoordGeometryData extends SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>{

	private SFFloat au=new SFFloat(1);
	private SFFloat bu=new SFFloat(0);
	private SFFloat cu=new SFFloat(0);
	private SFFloat av=new SFFloat(0);
	private SFFloat bv=new SFFloat(1);
	private SFFloat cv=new SFFloat(0);
	
	public SFSimpleObjPlaneTexCoordGeometryData(){
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(au,bu,cu,av,bv,cv));
	}
	
	@Override
	public SFSimpleObjPlaneTexCoordGeometry buildResource() {
		return new SFSimpleObjPlaneTexCoordGeometry(au.getFloatValue(), bu.getFloatValue(), cu.getFloatValue(),
				av.getFloatValue(), bv.getFloatValue(), cv.getFloatValue());
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
