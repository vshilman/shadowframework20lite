package shadow.renderer.compiler;

import java.util.ArrayList;

import shadow.system.data.SFOutputStream;

public class SFCompilerOutputStream implements SFOutputStream{

	private SFOutputStream output;

	private ArrayList<String> stringNames=new ArrayList<String>();
	
	public SFCompilerOutputStream(SFOutputStream output) {
		super();
		this.output = output;
	}

	@Override
	public void writeBinaryData(int value, int bitSize) {
		output.writeBinaryData(value, bitSize);
	}
	
	@Override
	public void writeBinaryData(int[] values, int bitSize) {
		output.writeBinaryData(values, bitSize);
	}
	
	@Override
	public void writeByte(int value) {
		output.writeByte(value);
	}
	
	@Override
	public void writeFloat(float value) {
		output.writeFloat(value);
	}
	
	@Override
	public void writeFloats(float[] values) {
		output.writeFloats(values);
	}
	
	@Override
	public void writeInt(int value) {
		output.writeInt(value);
	}
	
	@Override
	public void writeInts(int[] values) {
		output.writeInts(values);
	}
	
	@Override
	public void writeShort(short value) {
		output.writeShort(value);
	}
	
	@Override
	public void writeName(String s) {
		writeString(s);
	}
	
	@Override
	public void writeShorts(short[] values) {
		output.writeShorts(values);
	}
	
	@Override
	public void writeString(String s) {
		short index=0;
		if(!stringNames.contains(s)){
			stringNames.add(s);
		}
		index=(short)(stringNames.indexOf(s));
		index++;
		output.writeShort(index);
	}
	
	
}
