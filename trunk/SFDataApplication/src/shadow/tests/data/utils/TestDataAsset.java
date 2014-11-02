package shadow.tests.data.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class TestDataAsset extends SFDataAsset<TestDataAsset>{
	
	private int value1;
	private String value2;

	public TestDataAsset() {
		addObject("number", new SFInt(0));
		addObject("string", new SFString());
	}
	
	public TestDataAsset(int value1, String value2) {
		super();
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public TestDataAsset createResource(SFNamedParametersObject sfDataObject) {
		SFInt number=sfDataObject.getDataObject(0);
		SFString string=sfDataObject.getDataObject(1);
		
		this.value1=number.getIntValue();
		this.value2=string.getString();
		
		return new TestDataAsset(value1, value2);
	}
	
	public int getValue1() {
		return value1;
	}
	
	 public String getValue2() {
		return value2;
	}
	
	 @Override
	public String toString() {
		return "["+value1+","+value2+"]";
	}
	
}
