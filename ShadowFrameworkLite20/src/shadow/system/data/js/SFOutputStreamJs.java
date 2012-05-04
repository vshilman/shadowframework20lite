package shadow.system.data.js;

import java.io.IOException;
import java.io.Writer;

import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFIOExceptionKeeper;

public class SFOutputStreamJs implements SFOutputStream {
	
	private static final String specialChar="##";
	
	Writer stream;
	SFIOExceptionKeeper keeper;
	
	public SFOutputStreamJs(Writer stream, SFIOExceptionKeeper keeper) {
		super();
		this.stream = stream;
		this.keeper = keeper;
	}
	
	@Override
	public void writeString(String s) {
		try {
			stream.write(s);
			stream.write(specialChar);
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	@Override
	public void writeBinaryData(int[] values, int bitSize) {
		try {
			for (int i = 0; i < values.length; i++) {
				stream.write(values[i]+"");
				stream.write(specialChar);	
			}
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	@Override
	public void writeBinaryData(int value, int bitSize) {
		try {
			stream.write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void writeShorts(short[] values) {
		try {
			for (int i = 0; i < values.length; i++) {
				stream.write(values[i]+"");
				stream.write(specialChar);	
			}
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeFloat(float)
	 */
	@Override
	public void writeFloat(float value){
		writeString(value+"");
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeFloats(float[])
	 */
	@Override
	public void writeFloats(float[] values){
		
		try {
			for (int i = 0; i < values.length; i++) {
				stream.write(values[i]+"");
				stream.write(specialChar);
			}
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeShort(short)
	 */
	@Override
	public void writeShort(short value){
		writeString(value+"");
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeInt(int)
	 */
	@Override
	public void writeInt(int value){
		writeString(value+"");
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeLong(long)
	 */
	public void writeLong(long value){
		writeString(value+"");
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeInts(int[])
	 */
	@Override
	public void writeInts(int[] values){
		try {
			for (int i = 0; i < values.length; i++) {
				stream.write(values[i]+"");
				stream.write(specialChar);
			}
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.system.SFIOutputStram#writeBytes(byte[])
	 */
	public void writeBytes(byte values[]){
		try {
			for (int i = 0; i < values.length; i++) {
				stream.write(values[i]+"");
				stream.write(specialChar);
			}
		} catch (IOException e) {
			keeper.launch(e);
		}
	}
	
}
