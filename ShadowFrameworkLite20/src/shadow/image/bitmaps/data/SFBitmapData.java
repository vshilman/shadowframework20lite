package shadow.image.bitmaps.data;

import shadow.image.SFBitmap;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFShort;

public abstract class SFBitmapData extends SFDataAsset<SFBitmap> {

	protected SFShort width=new SFShort((short)0);
	protected SFShort height=new SFShort((short)0);
	
	public SFBitmapData() {
		super();
	}

	public SFShort getWidth(){
		return width;
	} 
	
	public SFShort getHeight(){
		return height;
	} 
	
	public void setHeight(int height){
		this.height.setShortValue((short)height);
	}
	
	public void setWidth(int height){
		width.setShortValue((short)height);
	}
}