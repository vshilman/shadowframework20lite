package shadow.geometry.functions.data;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFFixedFloat;
import shadow.geometry.functions.SFRectangle2DFunction;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFBinaryDataList;

public class SFRectangle2DFunctionData extends SFDataAsset<SFSurfaceFunction>{

	private SFBinaryDataList<SFFixedFloat> data=new SFBinaryDataList<SFFixedFloat>(new SFFixedFloat(0));
	
	
	public SFRectangle2DFunctionData() {
		setData(data);
		data.getDataObject().add(new SFFixedFloat(0));
		data.getDataObject().add(new SFFixedFloat(0));
		data.getDataObject().add(new SFFixedFloat(0));
		data.getDataObject().add(new SFFixedFloat(0));
	}
	
	
	public SFRectangle2DFunctionData(float x,float y,float w,float h) {
		setData(data);
		data.getDataObject().add(new SFFixedFloat(x));
		data.getDataObject().add(new SFFixedFloat(y));
		data.getDataObject().add(new SFFixedFloat(w));
		data.getDataObject().add(new SFFixedFloat(h));
	}
	
	@Override
	protected SFRectangle2DFunction buildResource() {
		SFRectangle2DFunction function=new SFRectangle2DFunction(
				data.getDataObject().get(0).getFloat(),
				data.getDataObject().get(1).getFloat(),
				data.getDataObject().get(2).getFloat(),
				data.getDataObject().get(3).getFloat()
		);
		return function;
	}
	
	public void setX(float x){
		data.getDataObject().get(0).setFloat(x);
	}
	
	public void setY(float y){
		data.getDataObject().get(1).setFloat(y);
	}
	
	public void setW(float w){
		data.getDataObject().get(2).setFloat(w);
	}
	
	public void setH(float h){
		data.getDataObject().get(3).setFloat(h);
	}
	
	public float getX(){
		return data.getDataObject().get(0).getFloat();
	}

	public float getY(){
		return data.getDataObject().get(1).getFloat();
	}

	public float getW(){
		return data.getDataObject().get(2).getFloat();
	}

	public float getH(){
		return data.getDataObject().get(3).getFloat();
	}
}
