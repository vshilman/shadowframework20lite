package shadow.image;

import java.nio.ByteBuffer;


/**
 * SFBitmap requires some image generation process to define image data, which are stored as a ByteBuffer.
 * If data have not been defined, the 'data' field is null.
 * */
public class SFBitmap {
	
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
	public static SFBitmap generateRGBImage(int width,int height,SFFormat format){
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
	private SFFormat format=SFFormat.ARGB8;
	
	/** data type depends on Image generation mode*/
	private ByteBuffer data;

	public int getWidth() {
		return width;
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

	public SFFormat getFormat() {
		return format;
	}

	public void setFormat(SFFormat format) {
		this.format = format;
	}

	public ByteBuffer getData() {
		return data;
	}

	public void setData(ByteBuffer data) {
		this.data = data;
	}
}
