package shadow.image.bitmaps.data;

import shadow.image.SFBitmap;
import shadow.image.bitmaps.SFSimplePerlinNoise;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;

public class SFSimplePerlinNoiseData extends SFDataAsset<SFBitmap>{

	private class SFSimplePerlinNoiseDataObject extends SFCompositeDataArray{
		private SFDataList<SFFloat> weights;
		private SFInt width,height,rgb;
		
		@Override
		public void generateData() {
			weights=new SFDataList<SFFloat>(new SFFloat(0));
			width=new SFInt(0);
			height=new SFInt(0);
			rgb=new SFInt(0);
			addDataObject(width);
			addDataObject(height);
			addDataObject(rgb);
			addDataObject(weights);
		}
		
		@Override
		public SFCompositeDataArray clone() {
			return new SFSimplePerlinNoiseDataObject();
		}
	} 
	
	
	private SFSimplePerlinNoiseDataObject data=new SFSimplePerlinNoiseDataObject();
	
	public SFSimplePerlinNoiseData() {
		super();
		setData(data);
	}

	public SFDataList<SFFloat> getWeights(){
		return data.weights;
	} 
	
	public SFInt getWidth(){
		return data.width;
	} 
	
	public SFInt getHeight(){
		return data.height;
	} 
	
	public SFInt getRGB(){
		return data.height;
	} 
	
	public void setRGB(boolean rgb){
		if(rgb)
			data.rgb.setIntValue(1);
		else
			data.rgb.setIntValue(0);
	}
	
	public void setHeight(int height){
		data.height.setIntValue(height);
	}
	
	public void setWidth(int height){
		data.width.setIntValue(height);
	}
	
	public void setWeights(float... weights){
		data.weights.clear();
		for (float f : weights) {
			data.weights.add(new SFFloat(f));
		}
	}

	@Override
	public SFSimplePerlinNoiseData generateNewDatasetInstance() {
		return new SFSimplePerlinNoiseData();
	}
	
	
	@Override
	protected SFBitmap buildResource() {
		
		float weights[]=new float[data.weights.size()];
		for (int i = 0; i < weights.length; i++) {
			weights[i]=data.weights.get(i).getFloatValue();
		}
		SFSimplePerlinNoise perlinNoise=new SFSimplePerlinNoise(data.width.getIntValue(),data.height.getIntValue(),weights,
				data.rgb.getIntValue()==1);
		return perlinNoise;
	}
	
}
