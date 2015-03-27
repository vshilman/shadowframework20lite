package shadow.system.data.objects;

import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

/**
 * @deprecated
 * 
 * @author Alessandro
 */
public class SFBinaryFloatArrayObject implements SFCharsetObject,SFDataObject{

	private float[] values;
	private int size;
	private int dim;
	
	public SFBinaryFloatArrayObject(int size,int dim) {
		super();
		this.size = size;
		this.dim = dim;
	}

	@Override
	public SFBinaryFloatArrayObject copyDataObject() {
		return new SFBinaryFloatArrayObject(size,dim);
	}
	
	public float[] getValues() {
		return values;
	}
	
	public void setValues(float[] values) {
		this.values = values;
	}

	@Override
	public void readFromStream(SFInputStream stream) {
		short length=stream.readShort();
		int[] values=stream.readBinaryData(length, size);
		this.values=new float[values.length];
		float recDim=1.0f/dim;
		for (int i = 0; i < values.length; i++) {
			this.values[i]=values[i]*recDim;
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)values.length);
		int[] values=new int[this.values.length];
		for (int i = 0; i < values.length; i++) {
			values[i]=(int)(this.values[i]*dim);
		}
		stream.writeBinaryData(values, size);
	}
	
	@Override
	public void setStringValue(String value) {
		this.values=SFCharsetObjectUtils.readFloats( value, SFBinaryArrayObject.class.getSimpleName());
	}
	
	@Override
	public String toStringValue() {
		return SFCharsetObjectUtils.writeFloats(values);
	}
}
