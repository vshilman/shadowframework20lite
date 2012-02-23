package shadow.system.data;



public interface SFOutputStream {

	public abstract void writeShort(short value);
	
	public abstract void writeShorts(short[] values);
	
	public abstract void writeFloat(float value);

	public abstract void writeFloats(float[] values);
	
	public abstract void writeInt(int value);

	public abstract void writeInts(int[] values);

	public abstract void writeString(String s);

	public abstract void writeBinaryData(int[] values,int bitSize);

}