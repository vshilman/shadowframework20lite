package shadow.system.data.java;

import java.io.IOException;
import java.io.OutputStream;

import shadow.system.data.SFOutputStream;

public class SFOutputStreamJava implements SFOutputStream {
	
	OutputStream stream;
	SFIOExceptionKeeper keeper;
	
	public SFOutputStreamJava(OutputStream stream, SFIOExceptionKeeper keeper) {
		super();
		this.stream = stream;
		this.keeper = keeper;
	}
	
	@Override
	public void writeString(String s) {
		byte[] length={(byte)s.length()};
		writeBytes(length);
		writeBytes(s.getBytes());
	}
	
	@Override
	public void writeBinaryData(int[] values, int bitSize) {

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
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeDataObjectHeader(short, int)
	 */
	public void writeDataObjectHeader(short code,int N){
		writeShort(code);
		writeShort((short)N);
	}

	@Override
	public void writeShorts(short[] values) {
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
		byte[] data=new byte[4];
		writeInt(data,0,value);
		try {
			stream.write(data);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeLong(long)
	 */
	public void writeLong(long value){
		int a=(int)(value & 0xffffffffL);
		int b=(int)((value & 0xffffffff00000000L)>>32L);
		writeInt( a);
		writeInt( b);
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeInts(int[])
	 */
	@Override
	public void writeInts(int[] values){
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
		try {
			stream.write(value);
		} catch (IOException e) {
			keeper.launch(e);
		}
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
