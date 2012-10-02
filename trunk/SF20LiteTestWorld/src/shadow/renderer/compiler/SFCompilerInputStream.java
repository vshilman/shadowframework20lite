package shadow.renderer.compiler;

import shadow.system.data.SFInputStream;

public class SFCompilerInputStream implements SFInputStream{

	private SFInputStream stream;

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
	public String readString() {
		return stream.readShort()+"";
	}
	
	@Override
	public String readName() {
		return stream.readShort()+"";
	}
}
