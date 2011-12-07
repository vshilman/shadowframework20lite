package shadow.image;

import java.nio.ByteBuffer;


/**
 * A basic Image class. 3 format are supported:
 * <ul>
 * <li>FORMAT_HEIGHT: 1 byte - 1 value per pixel, for height maps, lights maps and so on</li>
 * <li>FORMAT_RGB: 3 byte - 3 values per pixel, red green and blue</li>
 * <li>FORMAT_RGBA: 4 byte - 4 values per pixel, red green blue and alpha</li>
 * </ul>
 * 
 * GLImage requires some image generation process to define image data, which are stored as a ByteBuffer.
 * If data have not been defined, the 'data' field is null.
 * */
public class SFBitmap {
	
	/**Height Maps, black and white images*/
	public static final short FORMAT_HEIGHT=0;
	/**RGB Images*/
	public static final short FORMAT_RGB=1;
	/**RGBA Images*/
	public static final short FORMAT_RGBA=2;
	
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
		ret.width=width;
		ret.height=height;
		ret.format=FORMAT_RGB;
		return ret;
	}
	
	/**
	 * Generate an image with the desidered with and height.
	 * @param width width of the image
	 * @param height height of the image
	 * @param format format of the image
	 * @return the image being generated.
	 */
	public static SFBitmap generateRGBImage(int width,int height,short format){
		SFBitmap ret=new SFBitmap();
		ret.width=width;
		ret.height=height;
		ret.format=format;
		return ret;
	}

	/** width of the image in pixels*/
	public int width;
	/** height of the image in pixels*/
	public int height;
	/** format of the image*/
	public short format=FORMAT_RGB;
	
	/** data type depends on Image generation mode*/
	public ByteBuffer data;

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

	public short getFormat() {
		return format;
	}

	public void setFormat(short format) {
		this.format = format;
	}

	public ByteBuffer getData() {
		return data;
	}

	public void setData(ByteBuffer data) {
		this.data = data;
	}
}
