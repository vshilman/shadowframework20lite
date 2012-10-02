package shadow.operational.grid;

public class SFQuadrilateralGrid<T> extends SFRegularGeometricGrid<T> implements SFQuadGrid<T>{

	@SuppressWarnings("unchecked")
	public SFQuadrilateralGrid(int n){
		super(n);
		int grids=(n+1)>>1;
		/* examples :
		 * 7 has 4
		 * 6,5 has 3  
		 * 4,3 has 2
		 * 2,1 has 1
		 */
		circles=(SFGridCircle<T>[])(new SFGridCircle[grids]);
		for (int i = 0; i < circles.length; i++) {
			circles[i]=new SFGridCircle<T>(n-2*i,4);
		}
	}
	
	public <S> SFQuadrilateralGrid<S> sameGrid(){
		SFQuadrilateralGrid<S> grid=new SFQuadrilateralGrid<S>(this.n);
		return grid;
	}
	
	@Override
	public int getHeight() {
		return getN();
	}
	
	@Override
	public int getWidth() {
		return getN();
	}
	
	public SFCircleIndex getCircle(int i,int j){
		int k=n-1-i;
		int l=n-1-j;
		return getCircleIndex(i, j, k, l);
	}

	public void setValue(int i, int j, T value) {
		SFCircleIndex circle=getCircle(i, j);
		this.circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
	}

	public T getValue(int i, int j) {
		SFCircleIndex circle=getCircle(i, j);
		return (this.circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex));
	}
	
	private static void stampCircleIndex(SFCircleIndex index){
		System.out.println(" "+index.circleIndex+" "+index.edgeIndex+" "+index.inEdgeIndex);
	}
	
	public static void main(String[] args) {
		int n=4;
		SFQuadrilateralGrid<Integer> grid=new SFQuadrilateralGrid<Integer>(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("\t in "+i+" "+j);
				SFCircleIndex index=grid.getCircle(i, j);
				stampCircleIndex(index);
			} 	
		}
		
	}
}
