package main;

import android.widget.EditText;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class SFObjectsBuilder {
	
	private SFString tireMark;
	private SFString tireType;
	private SFInt tireAmount;
	private SFFloat tireDimension;
	
	SFObjectsBuilder(){
		
		tireAmount = new SFInt(0);
		tireDimension = new SFFloat(0);
		tireType = new SFString("0");
		tireMark = new SFString("0");
	}
	
	SFObjectsBuilder(EditText text1,EditText text2,EditText text3,EditText text4){
		
		float dimension = Float.parseFloat(text1.getText().toString().trim());
		int amount = Integer.parseInt(text2.getText().toString().trim());
		String mark = text4.getText().toString().trim();
		String type = text3.getText().toString().trim();
		
		tireAmount = new SFInt(amount);
		tireDimension = new SFFloat(dimension);
		tireType = new SFString(type);
		tireMark = new SFString(mark);
		
	}

	public SFString getTireMark() {
		return tireMark;
	}

	public void setTireMark(SFString tireMark) {
		this.tireMark = tireMark;
	}

	public SFString getTireType() {
		return tireType;
	}

	public void setTireType(SFString tireType) {
		this.tireType = tireType;
	}

	public SFInt getTireAmount() {
		return tireAmount;
	}

	public void setTireAmount(SFInt tireAmount) {
		this.tireAmount = tireAmount;
	}

	public SFFloat getTireDimension() {
		return tireDimension;
	}

	public void setTireDimension(SFFloat tireDimension) {
		this.tireDimension = tireDimension;
	}
	
}
