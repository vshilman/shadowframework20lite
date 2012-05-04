package shadow.image;

/** 
 * Describe a buffer
 * 
 * @author Alessandro
 */
public abstract class SFBufferData {

	private int width,height;
	private SFImageFormat format;
	
	public SFBufferData(int width, int height, SFImageFormat format) {
		super();
		this.width = width;
		this.height = height;
		this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public SFImageFormat getFormat() {
		return format;
	}
	
}
