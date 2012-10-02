package shadow.operational.grid.macroGrid;

import shadow.operational.grid.SFGridCircle;
import shadow.operational.grid.SFTriangularGrid;


public class SFPolygonalGrid<T> {
	
	protected int n;
	protected int edgeSize;
	protected SFGridCircle<T> circles[];

	@SuppressWarnings("unchecked")
	public SFPolygonalGrid(int n,int edgeSize) {
		
		this.n=n;
		this.edgeSize=edgeSize;
		this.circles=(SFGridCircle<T>[])(new SFGridCircle<?>[n]);
		
		for (int i = 0; i < circles.length; i++) {
			circles[i]=new SFGridCircle<T>(n-i,edgeSize);
		}
	}
	
	public void setValue(int circleIndex, int edgeIndex, int inEdgeIndex, T value) {
		this.circles[circleIndex].setValue(edgeIndex,inEdgeIndex,value);
	}

	public T getValue(int circleIndex, int edgeIndex, int inEdgeIndex){
		return (this.circles[circleIndex].getValue(edgeIndex,inEdgeIndex));
		
	}
	
	public int getInternalsSize(){
		int size=0;
		for (int i = 1; i < circles.length; i++) {
			int n=circles[i].getN();
			if(n==1){
				size+=1;
			}else{
				size+=(n-1)*edgeSize;
			}
		}
		return size;
	}
	
	

	public SFGridCircle<T>[] getCircles() {
		return circles;
	}

	@SuppressWarnings("unchecked")
	public SFTriangularGrid<T>[] generateTriangularGrids(){
		
		SFTriangularGrid<T>[] grids=(SFTriangularGrid<T>[])(new SFTriangularGrid<?>[this.edgeSize]);
		
		for (int k = 0; k < grids.length; k++) {
			grids[k]=new SFTriangularGrid<T>(n);
			for(int i=0;i<n;i++){
				if(i==n-1){
					grids[k].setValue(i, 0, getValue(i,0,0));
				}else{
					for(int j=0;j<n-i;j++){
						if(j==n-i-1){
							grids[k].setValue(i, j, getValue(i,SFGridCircle.round(k+1,edgeSize),0));
						}else{
							grids[k].setValue(i, j, getValue(i,k,j));
						}
					}
				}
			}
		}
		
		return grids;
	} 
	
}
