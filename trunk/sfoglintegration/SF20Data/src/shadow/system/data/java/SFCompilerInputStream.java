package shadow.system.data.java;

import java.util.ArrayList;

import shadow.system.data.SFInputStream;

public class SFCompilerInputStream implements SFInputStream{

	private SFInputStream stream;

	private ArrayList<String> stringNames=new ArrayList<String>();

	public SFCompilerInputStream(SFInputStream stream) {
		super();
		this.stream = stream;
	}
	
	@Override
	public int readBinaryData(int bitSize) {
		return stream.readBinaryData(bitSize);
	}
	
	@Override
	public int[] readBinaryData(int n, int bitSize) {
		return stream.readBinaryData(n, bitSize);
	}
	
	@Override
	public byte readByte() {
		return stream.readByte();
	}
	
	@Override
	public float readFloat() {
		return stream.readFloat();
	}
	
	@Override
	public float[] readFloats(int n) {
		return stream.readFloats(n);
	}
	
	@Override
	public int readInt() {
		return stream.readInt();
	}
	
	@Override
	public int[] readInts(int n) {
		return stream.readInts(n);
	}
	
	@Override
	public short readShort() {
		return stream.readShort();
	}
	
	@Override
	public short[] readShorts(int n) {
		return stream.readShorts(n);
	}
	
	@Override
	public byte[] readBytes(int n) {
		return stream.readBytes(n);
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
	public String readString() {
		short value=stream.readShort();
		if(value==-1){
			String string=stream.readString();
			stringNames.add(string);
			return string;
		}else{
			return stringNames.get(value);
		}
	}
	
	@Override
	public String readName() {
		return readString();
	}
}
