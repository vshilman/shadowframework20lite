package tests.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class DataAsset001 extends SFDataAsset<DataAsset001> {
	
	private int tireAmount;
	private String tireMark;

	public DataAsset001() {
		addObject("amount", new SFInt(0));
		addObject("mark", new SFString());
	}
	
	public DataAsset001(int tireDimension, String tireMark) {
		super();
		this.tireAmount = tireDimension;
		this.tireMark = tireMark;
	}

	@Override
	public DataAsset001 createResource(SFNamedParametersObject sfDataObject) {
		SFInt Dimension=sfDataObject.getDataObject(0);
		SFString Mark=sfDataObject.getDataObject(1);
		
		this.tireAmount=Dimension.getIntValue();
		this.tireMark=Mark.getString();
		
		return new DataAsset001(tireAmount, tireMark);
	}
	
	public int getTireAmount() {
		return tireAmount;
	}
	
	 public String getTireMark() {
		return tireMark;
	}
	
	 @Override
	public String toString() {
		return "["+tireAmount+","+tireMark+"]";
	}
}
