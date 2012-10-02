package shadow.operational.grid;

public abstract class SFRegularGeometricGrid<T>{

	protected int n;
	protected SFGridCircle<T> circles[];

	protected SFRegularGeometricGrid(int n) {
		super();
		this.n=n;
	}
	
	public abstract <S> SFRegularGeometricGrid<S> sameGrid();

	public SFGridCircle<T>[] getCircles() {
		return circles;
	}

	public int getN() {
		return n;
	}
	

	protected static SFCircleIndex getCircleIndex(int... i) {
		SFCircleIndex circleIndex=new SFCircleIndex();
		int min=i[0];
		circleIndex.edgeIndex=0;
		circleIndex.inEdgeIndex=i[1]-i[0];
		for (int index = 1; index < i.length; index++) {
			if(i[index]<=min && i[index]!=i[index-1]){
				min=i[index];
				circleIndex.edgeIndex=i.length-index;
				circleIndex.inEdgeIndex=i[SFGridCircle.round(index+1, i.length)]-i[index];
			}
		}
		circleIndex.circleIndex=min;
		return circleIndex;
	}
}