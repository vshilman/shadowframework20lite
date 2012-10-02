package shadow.image;

import java.nio.ByteBuffer;

import shadow.system.SFException;
import shadow.system.SFInitiable;


/**
 * SFBitmap requires some image generation process to define image data, which are stored as a ByteBuffer.
 * If data have not been defined, the 'data' field is null.
 * */
public class SFBitmap implements SFInitiable{
	
	public SFBitmap() {
		super();
	}
	
	/**
	 * Generate an image with the desidered with and height.
	 * Default format is RGB.
	 * @param width
	 * @param height
	 * @return the image being generated.
	 */
	public static SFBitmap generateRGBImage(int width,int height){
		SFBitmap ret=new SFBitmap();
		ret.setWidth(width);
		ret.setHeight(height);
		return ret;
	}
	
	/**
	 * Generate an image with the desidered with and height.
	 * @param width width of the image
	 * @param height height of the image
	 * @param format format of the image
	 * @return the image being generated.
	 */
	public static SFBitmap generateRGBImage(int width,int height,SFImageFormat format){
		SFBitmap ret=new SFBitmap();
		ret.setWidth(width);
		ret.setHeight(height);
		ret.setFormat(format);
		return ret;
	}

	/** width of the image in pixels*/
	private int width;
	/** height of the image in pixels*/
	private int height;
	/** format of the image*/
	private SFImageFormat format=SFImageFormat.ARGB8;
	
	/** data type depends on Image generation mode*/
	private ByteBuffer data;

	public int getWidth() {
		return width;
	}
	
	public int getGray(int x,int y){
		byte b=data.get(getSize()*(x+width*y));
		return (b>=0?b:b+256);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public SFImageFormat getFormat() {
		return format;
	}

	public void setFormat(SFImageFormat format) {
		this.format = format;
	}

	public ByteBuffer getData() {
		return data;
	}

	public void setData(ByteBuffer data) {
		this.data = data;
	}
	
	@Override
	public void init() {
		//TODO SFBitmap does not have an init implementation, is that generally correct?
	}
	
	@Override
	public void destroy() {
		//TODO SFBitmap does not have a destroy implementation, is that generally correct?
	}

	public int getSize() {
		int size=1;
		if(getFormat()==SFImageFormat.RGB8){
			size=3;
		}
		return size;
	}

	public void generateBitmap(int width, int height, int[] values, boolean rgb) {
		setWidth(width);
		setHeight(height);
		if(rgb){
			setFormat(SFImageFormat.RGB8);
		}else{
			setFormat(SFImageFormat.GRAY8);
		}
		
		if(width*height!=values.length)
			throw new SFException("An SFBitmapArrayData must have an array of "+(width*height)+" values, there are "+values.length+"values");
		
		int size=1;
		if(rgb){
			size=3;
		}
		ByteBuffer buffer=ByteBuffer.allocateDirect(width*height*size);
		
		for (int i = 0; i < values.length; i++) {
			buffer.put((byte)(values[i]));
		}
		
		setData(buffer);
		buffer.rewind();
	}
}
