package shadow.system.data.java;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import shadow.system.data.SFOutputStream;

public class SFOutputStreamJavaDebug implements SFOutputStream {
	
	OutputStream stream;
	SFIOExceptionKeeper keeper;
	
	public SFOutputStreamJavaDebug(OutputStream stream, SFIOExceptionKeeper keeper) {
		super();
		this.stream = stream;
		this.keeper = keeper;
	}
	
	@Override
	public void writeName(String s) {
		writeString(s);
	}
	
	@Override
	public void writeString(String s) {
		System.err.println("writeString "+s);
		byte[] length={(byte)s.length()};
		writeBytes(length);
		writeBytes(s.getBytes());
	}
	
	@Override
	public void writeBinaryData(int[] values, int bitSize) {
		System.err.println("writeBinaryData "+Arrays.toString(values)+" "+values.length);

		int byteSize = ((bitSize - 1) >> 3) + 1;
		
		byte[] bytes = new byte[values.length * byteSize];
		
		for (int i = 0; i < values.length; i++) {
			int value = values[i];
			for (int j = 0; j < byteSize; j++) {
				byte byteValue=(byte)((value & (0xff<<(8*j)))>>(8*j));
				bytes[i * byteSize + j] = byteValue;
			}
		}
		
		writeBytes(bytes);
	}
	
	@Override
	public void writeBinaryData(int value, int bitSize) {
		System.err.println("writeBinaryData "+value);
		int byteSize = ((bitSize - 1) >> 3) + 1;
		
		byte[] bytes = new byte[byteSize];
		
		for (int j = 0; j < byteSize; j++) {
			byte byteValue=(byte)((value & (0xff<<(8*j)))>>(8*j));
			bytes[j] = byteValue;
		}
		
		writeBytes(bytes);
	}
	

	@Override
	public void writeShorts(short[] values) {
		System.err.println("writeShorts "+Arrays.toString(values));
		byte[] data=new byte[2*values.length];
		for (int i = 0; i < values.length; i++) {
			writeShort(data,i*2,values[i]);
		}
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeFloat(float)
	 */
	@Override
	public void writeFloat(float value){
		System.err.println("writeFloat "+value);
		byte[] data=new byte[4];
		writeFloat(data,0,value);
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeFloats(float[])
	 */
	@Override
	public void writeFloats(float[] values){
		System.err.println("writeFloats "+Arrays.toString(values));
		byte[] data=new byte[4*values.length];
		for (int i = 0; i < values.length; i++) {
			writeFloat(data,i*4,values[i]);
		}
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeShort(short)
	 */
	@Override
	public void writeShort(short value){
		System.err.println("writeShort "+value);
		byte[] data=new byte[2];
		writeShort(data,0,value);
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeInt(int)
	 */
	@Override
	public void writeInt(int value){
		System.err.println("writeInt "+value);
		byte[] data=new byte[4];
		writeInt(data,0,value);
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
//	/* (non-Javadoc)
//	 * @see shadow.system.SFIOutputStram#writeLong(long)
//	 */
//	public void writeLong(long value){
//		int a=(int)(value & 0xffffffffL);
//		int b=(int)((value & 0xffffffff00000000L)>>32L);
//		writeInt( a);
//		writeInt( b);
//	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeInts(int[])
	 */
	@Override
	public void writeInts(int[] values){
		System.err.println("writeInts "+Arrays.toString(values));
		byte[] data=new byte[4*values.length];
		for (int i = 0; i < values.length; i++) {
			writeInt(data,i*4,values[i]);
		}
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeBytes(byte[])
	 */
	public void writeBytes(byte value[]){
		System.err.println("writeBytes "+Arrays.toString(value));
		try {
			stream.write(value);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	@Override
	public void writeByte(int value) {
		System.err.println("writeByte "+value);
		byte[] bs={(byte)value};
		writeBytes(bs);
	}
	
	private void writeFloat(byte[] data,int index,float value){
		int intValue=Float.floatToIntBits(value);
		data[index+0]=(byte)((intValue & 0xff000000)>>24);
		data[index+1]=(byte)((intValue & 0xff0000)>>16);
		data[index+2]=(byte)((intValue & 0xff00)>>8);
		data[index+3]=(byte)((intValue & 0xff));
	}
	
	private void writeShort(byte[] data,int index,short value){
		data[index+0]=(byte)((value & 0xff00)>>8);
		data[index+1]=(byte)((value & 0xff));
	}
	
	private void writeInt(byte[] data,int index,int value){
		data[index+0]=(byte)((value & 0xff000000)>>24);
		data[index+1]=(byte)((value & 0xff0000)>>16);
		data[index+2]=(byte)((value & 0xff00)>>8);
		data[index+3]=(byte)((value & 0xff));
	}
}
