package shadow.image.bitmaps.data;


import shadow.image.SFBitmap;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryArrayObject;

public class SFBitmapArrayData extends SFBitmapData{

	private SFBinaryArrayObject bitmap=new SFBinaryArrayObject(1);
	
	public SFBitmapArrayData(){
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("width", width);
		parameters.addObject("height", height);
		parameters.addObject("bitmap", bitmap);
		setData(parameters);
	}
	
	@Override
	protected SFBitmap buildResource() {
		SFBitmap bitmap=new SFBitmap();
		int width=this.width.getShortValue();
		int height=this.height.getShortValue();
		int[] values=this.bitmap.getValues();

		bitmap.generateBitmap(width, height, values, false);
		
		return bitmap;
	}
}
