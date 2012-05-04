package shadow.geometry.curves.data;

import shadow.geometry.data.SFVertexDataContainer;
import shadow.math.SFVertex3f;
import shadow.system.data.objects.SFBinaryValue;

public class SFPoint3DData extends SFBinaryValue implements SFVertexDataContainer<SFVertex3f>{

	private static final float rap = 0.001f;
	private static final float recRap = 1000f;
	private static final int XYMASK = (0x3ff<<10) + 0x3ff;
	private static final int XYOFFSET = 20;
	private static final int YMASK = 0x3ff;
	private static final int YOFFSET = 10;
	
	public SFPoint3DData(SFVertex3f vertex){
		setVertex(vertex);
	}
	
	public SFPoint3DData(float x, float y, float z){
		setVertex(x,y,z);
	}
	
	@Override
	protected int getBitSize() {
		return 32;
	}
	
	@Override
	public SFBinaryValue clone() {
		return new SFPoint3DData(getX(),getY(),getZ());
	}

	
	public void getVertex(SFVertex3f write){
		write.set3f(getX(),getY(),getZ());
	}
	
	public void setVertex(SFVertex3f read){
		float x=read.getX();
		float y=read.getY();
		float z=read.getZ();
		setVertex(x, y,z);
	}

	private void setVertex(float x, float y, float z) {
		int value = getValue();
		value -= (value & XYMASK);
		int zInt = (int)(z*recRap) << XYOFFSET;
		int xInt = (int)(x*recRap) << YOFFSET;
		int yInt = (int)(y*recRap) ;
		setValue(zInt + xInt + yInt);
	}
	
	public float getX(){
		return rap * ( (value & XYMASK) >> YOFFSET);
	}
	
	public float getY(){
		return rap * ( value & YMASK);
	}
	
	public float getZ(){
		return rap * ( (value) >> XYOFFSET);
	}
}
