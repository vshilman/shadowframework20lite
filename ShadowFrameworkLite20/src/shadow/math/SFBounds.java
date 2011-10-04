package shadow.math;

public class SFBounds<T> {

	private T basePoint;
	private T extension;
	
	public SFBounds(T basePoint, T extension) {
		super();
		this.basePoint = basePoint;
		this.extension = extension;
	}

	public T getBasePoint() {
		return basePoint;
	}

	public void setBasePoint(T basePoint) {
		this.basePoint = basePoint;
	}

	public T getExtension() {
		return extension;
	}

	public void setExtension(T extension) {
		this.extension = extension;
	}	
}