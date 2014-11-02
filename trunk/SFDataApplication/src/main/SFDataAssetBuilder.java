package main;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class SFDataAssetBuilder extends SFDataAsset<SFDataAssetBuilder> {
	
	private String tireType;
	private String tireMark;
	private int tireAmount;
	private float tireDimension;
	
	SFDataAssetBuilder(SFObjectsBuilder objectBuilder){
		
		addObject("type", objectBuilder.getTireType());
		addObject("mark", objectBuilder.getTireMark());
		addObject("amount", objectBuilder.getTireAmount());
		addObject("dimension", objectBuilder.getTireDimension());
	}	
	
	public SFDataAssetBuilder(String type, String mark, int amount, float dimension) {
		super();
		this.tireType = type;
		this.tireMark = mark;
		this.tireAmount = amount;
		this.tireDimension = dimension;
	}
	public SFDataAssetBuilder() {
		addObject("type", new SFString());
		addObject("mark", new SFString());
		addObject("amount", new SFInt(0));
		addObject("dimension", new SFFloat());
	}
	
	@Override
	public SFDataAssetBuilder createResource(SFNamedParametersObject sfDataObject) {
		SFString type = sfDataObject.getDataObject(0);
		SFString mark = sfDataObject.getDataObject(1);
		SFInt amount = sfDataObject.getDataObject(2);
		SFFloat dimension = sfDataObject.getDataObject(3);
		
		this.tireType = type.getString();
		this.tireMark = mark.getString();
		this.tireAmount = amount.getIntValue();
		this.tireDimension = dimension.getFloatValue();
		
		return new SFDataAssetBuilder(tireType, tireMark, tireAmount, tireDimension);
	}

	public String getTireType() {
		return tireType;
	}

	public String getTireMark() {
		return tireMark;
	}

	public int getTireAmount() {
		return tireAmount;
	}

	public float getTireDimension() {
		return tireDimension;
	}

	public void setTireType(String tireType) {
		this.tireType = tireType;
	}

	public void setTireMark(String tireMark) {
		this.tireMark = tireMark;
	}

	public void setTireAmount(int tireAmount) {
		this.tireAmount = tireAmount;
	}

	public void setTireDimension(float tireDimension) {
		this.tireDimension = tireDimension;
	}
	 @Override
		public String toString() {
		 return "["+tireType+","+tireMark+","+tireAmount+","+tireDimension+"]";
		}
}
