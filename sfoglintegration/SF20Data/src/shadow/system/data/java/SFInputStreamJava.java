package shadow.system.data.java;

import java.io.IOException;
import java.io.InputStream;

import shadow.system.data.SFInputStream;

public class SFInputStreamJava implements SFInputStream {

	private InputStream stream;
	private SFIOExceptionKeeper keeper;

	public SFInputStreamJava(InputStream stream, SFIOExceptionKeeper keeper) {
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
//		int n=readInt();
//		return ""+n;
		int n = readBytes(1)[0];
		byte[] data = readBytes(n);
		
		//System.err.print(" [readString n "+n+"]");
		return new String(data);
	}

	@Override
	public int[] readBinaryData(int n, int bitSize) {

		return readBinaryDataTwosComplement(n, bitSize);
//		int byteSize = ((bitSize - 1) >> 3) + 1;
//
//		byte[] bytes = readBytes(n * byteSize);
//		int data[] = new int[n];
//		
//		for (int i = 0; i < n; i++) {
//			int value = 0;
//			for (int j = 0; j < byteSize; j++) {
//				int byteValue = bytes[i * byteSize + j];
//				value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
//			}
//			data[i] = value;
//		}
//
//		return data;
	}
	
	@Override
	public int[] readBinaryDataTwosComplement(int n, int bitSize) {

		int byteSize = ((bitSize - 1) >> 3) + 1;

		byte[] bytes = readBytes(n * byteSize);
		int data[] = new int[n];
		int positiveLimit = 1 << ((byteSize<<3)-1);

		//System.out.println("positiveLimit:"+positiveLimit+" bytSize:"+byteSize+" n:"+n+" bitSize:"+bitSize);
		
		for (int i = 0; i < n; i++) {
			int value = 0;
			for (int j = 0; j < byteSize; j++) {
				int byteValue = bytes[i * byteSize + j];
				value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
			}
			//System.out.println("value:"+value);
			if(value>=positiveLimit){
				value-=positiveLimit*2;
			}
			data[i] = value;
		}

		return data;
	}
	
	@Override
	public int readBinaryData(int bitSize) {
		int byteSize = ((bitSize - 1) >> 3) + 1;
		int positiveLimit = 1 << ((byteSize<<3)-1);

		byte[] bytes = readBytes(byteSize);
		int value = 0;
		for (int j = 0; j < byteSize; j++) {
			int byteValue = bytes[ j];
			value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
		}
		if(value>=positiveLimit){
			value-=positiveLimit*2;
		}

		return value;
	}
	
	@Override
	public int readBinaryDataTwosComplement(int bitSize) {
		int byteSize = ((bitSize - 1) >> 3) + 1;

		byte[] bytes = readBytes(byteSize);
		int value = 0;
		for (int j = 0; j < byteSize; j++) {
			int byteValue = bytes[ j];
			value += ((byteValue >= 0 ? byteValue : 256 + byteValue) << (8 * j));// is
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readShort()
	 */
	@Override
	public short readShort() {
		short fs = 0;
		byte[] bytes = new byte[2];
		try {
			stream.read(bytes, 0, 2);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs = readShort(bytes, 0);
		
		//System.err.println("short value = n = "+fs+" "+bytes[0]+" "+bytes[1]);
		return fs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readInts(int)
	 */
	@Override
	public short[] readShorts(int n) {
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
		return fs;
	}

	@Override
	public byte readByte() {
		return readBytes(1)[0];
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see shadow.system.SFIInputStream#readBytes(int)
	 */
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
