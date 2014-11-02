package tests.utils;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public class DataAsset003 extends SFDataAsset<DataAsset003> {

	private int tireAmount;
	private float tireDimension;
	private String tireMark;
	private String tireType;
	
	public DataAsset003() {
		addObject("T1", new SFLibraryReference<DataAsset001>());
		addObject("T2", new SFLibraryReference<DataAsset002>());
	}
	
	public DataAsset003(int amount, float dimension, String mark, String type) {
		super();
		this.tireAmount = amount;
		this.tireDimension = dimension;
		this.tireMark = mark;
		this.tireType = type;
	}

	@Override
	public DataAsset003 createResource(SFNamedParametersObject sfDataObject2) {
		SFLibraryReference<DataAsset001> t1 = sfDataObject2.getDataObject(0);
		SFLibraryReference<DataAsset002> t2 = sfDataObject2.getDataObject(1);
		
		DataAsset001 td1 = t1.getResource();
		DataAsset002 td2 = t2.getResource();
		
		return new DataAsset003(td1.getTireAmount(), td2.getTireDimension(), td1.getTireMark(), td2.getTireType());
	}
	
	 @Override
		public String toString() {
			return "["+tireAmount+","+tireDimension+","+tireMark+","+tireType+"]";
		}
}
