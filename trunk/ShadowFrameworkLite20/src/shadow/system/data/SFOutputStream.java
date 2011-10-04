package shadow.system.data;

import java.io.IOException;


public interface SFOutputStream {

	public abstract void writeHeader(SFDataset dataset);

	public abstract void writeDataObjectHeader(short code, int N)
			throws IOException;

	public abstract void writeFloat(float value);

	public abstract void writeFloats(float[] values);

	public abstract void writeShort(short value);

	public abstract void writeInt(int value);

	public abstract void writeLong(long value);

	public abstract void writeInts(int[] values);

	public abstract void writeBytes(byte value[]);

	public abstract void writeString(String s);
}