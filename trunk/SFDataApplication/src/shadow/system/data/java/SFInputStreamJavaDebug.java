package shadow.system.data.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import shadow.system.data.SFInputStream;

public class SFInputStreamJavaDebug implements SFInputStream {

	InputStream stream;
	SFIOExceptionKeeper keeper;

	public SFInputStreamJavaDebug(InputStream stream, SFIOExceptionKeeper keeper) {
		super();
		this.stream = stream;
		this.keeper = keeper;
	}

	@Override
	public String readName() {
		return readString();
	}
	
	@Override
	public String readString() {
		int n = readBytes(1)[0];
		byte[] data = readBytes(n);
		String ret=new String(data);
		System.err.println("readString "+ret);
		return ret;
	}
	
	

	@Override
	public int[] readBinaryData(int n, int bitSize) {

		int byteSize = ((bitSize - 1) >> 3) + 1;

		byte[] bytes = readBytes(n * byteSize);
		int data[] = new int[n];

		for (int i = 0; i < n; i++) {
			int value = 0;
			for (int j = 0; j < byteSize; j++) {
				int byteValue = bytes[i * byteSize + j];
				value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
			}
			data[i] = value;
		}
		System.err.println("readBinaryData (bytes Read) "+Arrays.toString(bytes));
		//125,0,0,253,0,0,0,125,0,125,125,0,125,0,125,0,0,125,125,0,253,125,253,0,253,253,100,0,253,253,253,0,253,253,0,0,253,50,25,0,75,253,253,253,0,0,253,8,77,117,115
		System.err.println("readBinaryData "+Arrays.toString(data)+" n "+n);
		//125,0,0,253,0,0,0,125,0,125,125,0,125,0,125,0,0,125,125,0,253,125,253,0,253,253,100,0,253,253,253,0,253,253,0,0,253,50,25,0,75,253,253,253,0,0,253,8,77,117,115
		return data;
	}
	
	@Override
	public int readBinaryDataTwosComplement(int bitSize) {
		// TODO Auto-generated method stub
		return readBinaryData(bitSize);
	}
	
	@Override
	public int[] readBinaryDataTwosComplement(int n, int bitSize) {
		// TODO Auto-generated method stub
		return readBinaryData(n,bitSize);
	}
	
	@Override
	public int readBinaryData(int bitSize) {
		int byteSize = ((bitSize - 1) >> 3) + 1;

		byte[] bytes = readBytes(byteSize);
		int value = 0;
		for (int j = 0; j < byteSize; j++) {
			int byteValue = bytes[ j];
			value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
		}

		System.err.println("readBinaryData "+Arrays.toString(bytes)+" ,  value "+value);
		System.err.println();
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readShort()
	 */
	@Override
	public short readShort() {
		System.err.println("readShort");
		short fs = 0;
		byte[] bytes = new byte[2];
		try {
			stream.read(bytes, 0, 2);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs = readShort(bytes, 0);
		System.err.println("readShort "+fs);
		return fs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readInts(int)
	 */
	@Override
	public short[] readShorts(int n) {
		System.err.println("readShorts");
		short[] fs = new short[n];
		byte[] bytes = new byte[2 * n];
		try {
			stream.read(bytes, 0, 2 * n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		for (int i = 0; i < n; i++) {
			fs[i] = readShort(bytes, i * 2);
		}
		System.err.println("readShorts "+Arrays.toString(fs));
		return fs;
	}

	// /* (non-Javadoc)
	// * @see shadow.system.SFIInputStream#readLong()
	// */
	// @Override
	// public long readLong() {
	// int fs1=readInt();
	// int fs2=readInt();
	// return ((long)fs1)+(((long)fs2)<<32L);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readInt()
	 */
	@Override
	public int readInt() {
		int fs = 0;
		byte[] bytes = new byte[4];
		try {
			stream.read(bytes, 0, 4);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs = readInt(bytes, 0);
		System.err.println("readInt "+fs);
		return fs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readInts(int)
	 */
	@Override
	public int[] readInts(int n) {
		int[] fs = new int[n];
		byte[] bytes = new byte[4 * n];
		try {
			stream.read(bytes, 0, 4 * n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		for (int i = 0; i < n; i++) {
			fs[i] = readInt(bytes, i * 4);
		}
		System.err.println("readInts "+Arrays.toString(fs));
		return fs;
	}

	@Override
	public byte readByte() {
		byte b=readBytes(1)[0];
		System.err.println("readByte "+b);
		return b;
	}
	
	public byte[] readBytes(int n) {
		byte[] bytes = new byte[n];
		try {
			stream.read(bytes, 0, n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		return bytes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readFloat()
	 */
	@Override
	public float readFloat() {
		float fs = 0;
		byte[] bytes = new byte[4];
		try {
			stream.read(bytes, 0, 4);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs = readFloat(bytes, 0);
		System.err.println("readFloat "+fs);
		return fs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readFloats(int)
	 */
	@Override
	public float[] readFloats(int n) {
		float[] fs = new float[n];
		byte[] bytes = new byte[4 * n];
		try {
			stream.read(bytes, 0, 4 * n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		for (int i = 0; i < n; i++) {
			fs[i] = readFloat(bytes, i * 4);
		}
		System.err.println("readFloats "+Arrays.toString(fs));
		return fs;
	}

	private float readFloat(byte[] data, int index) {
		int Value = (data[index + 3] >= 0 ? data[index + 3]
				: 256 + data[index + 3])
				+ (data[index + 2] >= 0 ? data[index + 2]
						: 256 + data[index + 2])
				* 0x100
				+ (data[index + 1] >= 0 ? data[index + 1]
						: 256 + data[index + 1])
				* 0x10000
				+ (data[index] >= 0 ? data[index] : 256 + data[index])
				* 0x1000000;

		return Float.intBitsToFloat(Value);
	}

	private short readShort(byte[] data, int index) {
		short Value = (short) ((data[index + 1] >= 0 ? data[index + 1]
				: 256 + data[index + 1]) + (data[index] >= 0 ? data[index]
				: 256 + data[index]) * 0x100);

		return Value;
	}

	private int readInt(byte[] data, int index) {
		int Value = (data[index + 3] >= 0 ? data[index + 3]
				: 256 + data[index + 3])
				+ (data[index + 2] >= 0 ? data[index + 2]
						: 256 + data[index + 2])
				* 0x100
				+ (data[index + 1] >= 0 ? data[index + 1]
						: 256 + data[index + 1])
				* 0x10000
				+ (data[index] >= 0 ? data[index] : 256 + data[index])
				* 0x1000000;

		return Value;
	}
}
