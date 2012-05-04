package shadow.geometry.curves.data;

import shadow.geometry.data.SFVertexDataContainer;
import shadow.math.SFVertex2f;
import shadow.system.data.objects.SFBinaryValue;

public class SFPoint2DData extends SFBinaryValue implements SFVertexDataContainer<SFVertex2f>{

	private static final float rap = 0.001f;
	private static final float recRap = 1000f;
	private static final int XYMASK = (0x3ff<<10) + 0x3ff;
	private static final int YMASK = 0x3ff;
	private static final int YOFFSET = 10;
	
	public SFPoint2DData(SFVertex2f vertex){
		setVertex(vertex);
	}
	
	public SFPoint2DData(float x, float y){
		setVertex(x,y);
	}
	
	@Override
	protected int getBitSize() {
		return 24;
	}
	
	@Override
	public SFBinaryValue clone() {
		return new SFPoint2DData(getX(),getY());
	}
	
	public void getVertex(SFVertex2f write){
		write.set2f(getX(),getY());
	}
	
	public void setVertex(SFVertex2f read){
		float x=read.getX();
		float y=read.getY();
		setVertex(x, y);
	}

	private void setVertex(float x, float y) {
		int value = getValue();
		value -= (value & XYMASK);
		int xInt = (int)(x*recRap) << YOFFSET;
		int yInt = (int)(y*recRap) ;
		setValue((value) + xInt + yInt);
	}
	
	public float getX(){
		return rap * ( (value & XYMASK) >> YOFFSET);
	}
	
	public float getY(){
		return rap * ( value & YMASK);
	}
}
