package testPackage;

/**
 * an house class
 * @author Alessandro
 */
public class House {

	private int roofHeight;
	private int baseWidth;
	private int baseHeight;
	
	public House(int roofHeight, int baseWidth, int baseHeight) {
		super();
		this.roofHeight = roofHeight;
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
	}

	public int getRoofHeight() {
		return roofHeight;
	}

	public void setRoofHeight(int roofHeight) {
		this.roofHeight = roofHeight;
	}

	public int getBaseWidth() {
		return baseWidth;
	}

	public void setBaseWidth(int baseWidth) {
		this.baseWidth = baseWidth;
	}

	public int getBaseHeight() {
		return baseHeight;
	}

	public void setBaseHeight(int baseHeight) {
		this.baseHeight = baseHeight;
	}

}
