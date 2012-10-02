package shadow.operational.grid;

public class SFLinearGrid<T> {

	private int n;
	private Object[] values;
	
	/**
	 * Create a grid circle
	 * @param n
	 * @param edges
	 */
	public SFLinearGrid(int n) {
		super();
		this.setN(n);

		this.values=new Object[n];
	}

	public void setValue(int index,T value){
		values[index]=value;
	}
	
	public <S> SFLinearGrid<S> sameGrid(){
		SFLinearGrid<S> grid=new SFLinearGrid<S>(this.n);
		return grid;
	}

	@SuppressWarnings("unchecked")	
	public T getValue(int index){
		return (T)values[index];
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}
