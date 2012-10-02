package shadow.image.bitmaps.data;

import shadow.system.data.objects.SFBinaryFloatArrayObject;

public abstract class SFPerlineNoiseData extends SFBitmapData{

	protected SFBinaryFloatArrayObject weights=new SFBinaryFloatArrayObject(1,250);

	public SFBinaryFloatArrayObject getWeights(){
		return weights;
	}

	public void setWeights(float... weights){
		this.weights.setValues(weights);
	}

}
