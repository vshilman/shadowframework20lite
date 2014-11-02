package shadow.tests.data.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBooleanEnum;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class TestDataAsset2 extends SFDataAsset<TestDataAsset2>{
	
	private int value1;
	private String value2;
	private boolean value3;
	private float value4;
	
	public TestDataAsset2() {
		addObject("number", new SFInt(0));
		addObject("string", new SFString());
		addObject("boolean", new SFBooleanEnum());
		addObject("float", new SFFloat(0));
	}
	
	public TestDataAsset2(int value1, String value2, boolean value3, float value4) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
	}

	@Override
	public TestDataAsset2 createResource(SFNamedParametersObject sfDataObject) {
		SFInt number=sfDataObject.getDataObject(0);
		SFString string=sfDataObject.getDataObject(1);
		SFBooleanEnum bool=sfDataObject.getDataObject(2);
		SFFloat float_=sfDataObject.getDataObject(3);
		
		this.value1=number.getIntValue();
		this.value2=string.getString();
		this.value3=bool.getElement();
		this.value4=float_.getFloatValue();
		
		return new TestDataAsset2(value1, value2, value3, value4);
	}
	
	public int getValue1() {
		return value1;
	}
	
	public String getValue2() {
		return value2;
	}
	
	public boolean isValue3() {
		return value3;
	}
	
	public float getValue4() {
		return value4;
	}
	
	 @Override
	public String toString() {
		return "["+value1+","+value2+","+value3+","+value4+"]";
	}
	
}
