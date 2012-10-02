package shadow.operational.grid;

public class SFRectangularGrid<T> implements SFQuadGrid<T>{
	
	private int width,height;
	
	private Object[][] grid;

	public SFRectangularGrid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.grid = new Object[height][width];
	}

	/* (non-Javadoc)
	 * @see shadow.operational.grid.SFQuadGrid#getValue(int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T getValue(int i,int j){
		return (T)(grid[i][j]);
	}
	
	/* (non-Javadoc)
	 * @see shadow.operational.grid.SFQuadGrid#setValue(int, int, T)
	 */
	@Override
	public void setValue(int i,int j,T value){
		grid[i][j]=value;
	}
	
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
}
