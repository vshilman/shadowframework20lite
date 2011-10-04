package shadow.system.data.java;

import java.io.IOException;
import java.io.InputStream;

import shadow.system.data.SFInputStream;

public class SFInputStreamJava implements SFInputStream {

	InputStream stream;
	SFIOExceptionKeeper keeper;

	public SFInputStreamJava(InputStream stream, SFIOExceptionKeeper keeper) {
		super();
		this.stream = stream;
		this.keeper = keeper;
	}

	@Override
	public String readString() {
		int n=readInt();
		byte[] data=readBytes(n);
		return new String(data);
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readShort()
	 */
	@Override
	public short readShort() {
		short fs=0;
		byte[] bytes=new byte[2];
		try {
			stream.read(bytes, 0, 2);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs=readShort(bytes, 0);
		return fs;
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readLong()
	 */
	@Override
	public long readLong() {
		int fs1=readInt();
		int fs2=readInt();
		return ((long)fs1)+(((long)fs2)<<32L);
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readInt()
	 */
	@Override
	public int readInt() {
		int fs=0;
		byte[] bytes=new byte[4];
		try {
			stream.read(bytes, 0, 4);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs=readInt(bytes, 0);
		return fs;
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readInts(int)
	 */
	@Override
	public int[] readInts(int n) {
		int[] fs=new int[n];
		byte[] bytes=new byte[4*n];
		try {
			stream.read(bytes, 0, 4*n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		for(int i=0;i<n;i++){
			fs[i]=readInt(bytes, i*4);
		}
		return fs;
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readBytes(int)
	 */
	@Override
	public byte[] readBytes(int n) {
		byte[] bytes=new byte[n];
		try {
			stream.read(bytes, 0, n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		return bytes;
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readFloat()
	 */
	@Override
	public float readFloat() {
		float fs=0;
		byte[] bytes=new byte[4];
		try {
			stream.read(bytes, 0, 4);
		} catch (IOException e) {
			keeper.launch(e);
		}
		fs=readFloat(bytes, 0);
		return fs;
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIInputStream#readFloats(int)
	 */
	@Override
	public float[] readFloats(int n) {
		float[] fs=new float[n];
		byte[] bytes=new byte[4*n];
		try {
			stream.read(bytes, 0, 4*n);
		} catch (IOException e) {
			keeper.launch(e);
		}
		for(int i=0;i<n;i++){
			fs[i]=readFloat(bytes, i*4);
		}
		return fs;
	}
	
	private float readFloat(byte[] data,int index){
		int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+
				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+
				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+
				  (data[index]>=0?data[index]:256+data[index])*0x1000000;

		return Float.intBitsToFloat(Value);
	}
	
	private short readShort(byte[] data,int index){
		
		short Value=(short)((data[index+1]>=0?data[index+1]:256+data[index+1])+
				(data[index]>=0?data[index]:256+data[index])*0x100);
		
		
		return Value;
	}
	
	private int readInt(byte[] data,int index){
		int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+
				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+
				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+
				  (data[index]>=0?data[index]:256+data[index])*0x1000000;

		return Value;
	}
}
