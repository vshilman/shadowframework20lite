package shadow.system.data.java;

import java.io.StringWriter;

import shadow.system.data.SFOutputStream;

public class SFStringWriterStream implements SFOutputStream{

	private StringWriter returnString=new StringWriter();
	
	@Override
	public void writeBinaryData(int value, int bitSize) {
		writeInt(value);
	}
	
	public void writeBinaryData(int[] values, int bitSize){
		for (int i = 0; i < values.length; i++) {
			writeInt(values[i]);
		}
	}
	
	@Override
	public void writeName(String s) {
		writeString(s);
	}
	
	public void writeFloat(float value) {
		returnString.write(value+":");
	}
	
	@Override
	public void writeFloats(float[] values) {
		for (int i = 0; i < values.length; i++) {
			writeFloat(values[i]);
		}
	}
	
	@Override
	public void writeInt(int value) {
		returnString.write(value+":");
	}
	
	@Override
	public void writeInts(int[] values) {
		for (int i = 0; i < values.length; i++) {
			writeInt(values[i]);
		}
	}
	
	@Override
	public void writeBytes(byte[] values) {
		for (int i = 0; i < values.length; i++) {
			writeInt(values[i]);
		}
	}
	
	
	public void writeShort(short value) {
		writeInt(value);
	}
	
	
	public void writeShorts(short[] values) {
		for (int i = 0; i < values.length; i++) {
			writeInt(values[i]);
		}
	}
	
	
	public void writeString(String s) {
		returnString.write(s+":");
	}
	
	public String getString(){
		return returnString.toString();
	}
	
	@Override
	public void writeByte(int value) {
		writeInt(value);
	}
}
