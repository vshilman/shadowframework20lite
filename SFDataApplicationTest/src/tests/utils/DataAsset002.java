package tests.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFString;

public class DataAsset002 extends SFDataAsset<DataAsset002> {
	
	private float tireDimension;
	private String tireType;

	public DataAsset002() {
		addObject("dimension", new SFFloat(0));
		addObject("type", new SFString());
	}
	
	public DataAsset002(float tireDimension, String tireType) {
		super();
		this.tireDimension = tireDimension;
		this.tireType = tireType;
	}

	@Override
	public DataAsset002 createResource(SFNamedParametersObject sfDataObject) {
		SFFloat Dimension=sfDataObject.getDataObject(0);
		SFString Mark=sfDataObject.getDataObject(1);
		
		this.tireDimension=Dimension.getFloatValue();
		this.tireType=Mark.getString();
		
		return new DataAsset002(tireDimension, tireType);
	}
	
	public float getTireDimension() {
		return tireDimension;
	}
	
	 public String getTireType() {
		return tireType;
	}
	
	 @Override
	public String toString() {
		return "["+tireDimension+","+tireType+"]";
	}

}
