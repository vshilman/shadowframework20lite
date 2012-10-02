package shadow.renderer;

/**
 * The description of a tiles set
 * @author Alessandro
 */
public class SFTilesSet {
	private int sizeX;
	private int sizeY;
	private float stepX;
	private float stepY;

	public SFTilesSet() {
	}

	void setSizeX(int sizeX) {
		this.sizeX = sizeX;
		stepX=1.0f/sizeX;
	}

	int getSizeX(SFTilesSpace sfTilesSpace) {
		return sizeX;
	}

	public void setSizeY( int sizeY) {
		this.sizeY = sizeY;
		stepY=1.0f/sizeY;
	}

	public int getSizeY(SFTilesSpace sfTilesSpace) {
		return sizeY;
	}

	public void setSizes(int sizeX, int sizeY) {
		setSizeX( sizeX);
		setSizeY( sizeY);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public float getStepX() {
		return stepX;
	}

	public float getStepY() {
		return stepY;
	}
}