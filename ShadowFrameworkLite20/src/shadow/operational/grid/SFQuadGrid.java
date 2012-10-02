package shadow.operational.grid;

public interface SFQuadGrid<T> {

	public abstract T getValue(int i, int j);

	public abstract void setValue(int i, int j, T value);

	public abstract int getWidth();
	
	public abstract int getHeight();
}