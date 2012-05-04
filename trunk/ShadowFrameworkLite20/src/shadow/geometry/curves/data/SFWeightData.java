package shadow.geometry.curves.data;

import shadow.geometry.data.SFVertexDataContainer;
import shadow.math.SFValue1f;
import shadow.system.data.objects.SFBinaryValue;

public class SFWeightData extends SFBinaryValue implements SFVertexDataContainer<SFValue1f>{
	
	private static final float rap = 0.04f;
	private static final float recRap = 250f;
	
	public SFWeightData(float weight){
		setWeight(weight);
	}
	
	@Override
	protected int getBitSize() {
		return 8;
	}
	
	@Override
	public SFBinaryValue clone() {
		return new SFWeightData(getWeight());
	}
	
	public float getWeight(){
		return value * rap;
	}
	
	
	public float setWeight(float weight){
		value  = (int)(weight*recRap);
		return value * rap;
	}
	
	@Override
	public void getVertex(SFValue1f write) {
		write.setX(getWeight());
	}
	
	@Override
	public void setVertex(SFValue1f read) {
		setWeight(read.getX());
	}
}
