package shadow.operational.grid;

public class SFTriangularGrid<T> extends SFRegularGeometricGrid<T> {

	@SuppressWarnings("unchecked")
	public SFTriangularGrid(int n){
		super(n);
		int grids=(n-1)/3;
		circles=(SFGridCircle<T>[])(new SFGridCircle[grids+1]);
		for (int i = 0; i < circles.length; i++) {
			circles[i]=new SFGridCircle<T>(n-3*i,3);
		}
	}

	public <S> SFTriangularGrid<S> sameGrid(){
		SFTriangularGrid<S> grid=new SFTriangularGrid<S>(this.n);
		return grid;
	}
	
	public void setValue(int i,int j,T value){
		int k=n-1-i-j;
		setValue(i, j, k, value);
	}

	public void setValue(int i, int j, int k, T value) {
		SFCircleIndex circle=getCircleIndex(i, j, k);
		this.circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
	}

	public T getValue(int i,int j){
		int k=n-1-i-j;
		return getValue(i, j, k);
	}

	public T getValue(int i, int j, int k) {
		SFCircleIndex circle=getCircleIndex(i, j, k);
		return (this.circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex));
	}
	
}
