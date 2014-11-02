package shadow.tests.data.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public class TestDataAsset3 extends SFDataAsset<TestDataAsset3>{

	private int value1b;
	private String value2b;
	private int value1;
	private String value2;
	private boolean value3;
	private float value4;
	
	public TestDataAsset3() {
		addObject("T1", new SFLibraryReference<TestDataAsset>());
		addObject("T2", new SFLibraryReference<TestDataAsset2>());
	}
	
	public TestDataAsset3(int value1, String value2, boolean value3, float value4, int value1b,String value2b) {
		super();
		this.value1b = value1b;
		this.value2b = value2b;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
	}

	@Override
	public TestDataAsset3 createResource(SFNamedParametersObject sfDataObject) {
		SFLibraryReference<TestDataAsset> t1=sfDataObject.getDataObject(0);
		SFLibraryReference<TestDataAsset2> t2=sfDataObject.getDataObject(1);
		
		TestDataAsset td1=t1.getResource();
		TestDataAsset2 td2=t2.getResource();
		
		return new TestDataAsset3(td2.getValue1(), td2.getValue2(), td2.isValue3(), td2.getValue4(), td1.getValue1(), td1.getValue2());
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
		return "["+value1+","+value2+","+value3+","+value4+","+value1b+","+value2b+"]";
	}
}
