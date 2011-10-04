package shadow.system.data;

public interface SFInputStream {

	public abstract short readShort();

	public abstract long readLong();

	public abstract int readInt();

	public abstract int[] readInts(int n);

	public abstract byte[] readBytes(int n);

	public abstract float readFloat();

	public abstract float[] readFloats(int n);

	public abstract String readString();

}