package shadow.system.data.java;

import java.util.StringTokenizer;

import shadow.system.data.SFInputStream;

public class SFStringTokenizerInputStream implements SFInputStream{

	private StringTokenizer tok;
	
	public SFStringTokenizerInputStream(String data) {
		super();
		this.tok = new StringTokenizer(data,":",false);
	}
	
	@Override
	
	public String readName() {
		return readString();
	}

	@Override
	public int readBinaryData(int bitSize) {
		return readInt();
	}
	
	@Override
	public int[] readBinaryData(int n, int bitSize) {
		return readInts(n);
	}
	
	@Override
	public float readFloat() {
		return new Float(tok.nextToken());
	}
	
	@Override
	public float[] readFloats(int n) {
		float[] data=new float[n];
		for (int i = 0; i < data.length; i++) {
			data[i]=readFloat();
		}
		return data;
	}
	
	public int readInt() {
		return new Integer(tok.nextToken());
	}
	
	public int[] readInts(int n)  {
		int[] data=new int[n];
		for (int i = 0; i < data.length; i++) {
			data[i]=readInt();
		}
		return data;
	}
	
	
	public short readShort() {
		return new Short(tok.nextToken());
	}
	
	public short[] readShorts(int n) {
		short[] data=new short[n];
		for (int i = 0; i < data.length; i++) {
			data[i]=readShort();
		}
		return data;
	}
	
	
	public String readString() {
		return tok.nextToken();
	}
	
	@Override
	public byte readByte() {
		return (byte)(readShort());
	}
	
}
