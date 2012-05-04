package shadow.operational.grid;

import shadow.math.SFValuenf;

public class SFTriangularGrid {

	private class SFCircleIndex{
		private int circleIndex=0;
		private int edgeIndex=0;
		private int inEdgeIndex=0;
	}

	public class SFTriangularGridCircle {

		public int n;
		public int[][] indices;
		public SFValuenf[][] values;
		
		public SFTriangularGridCircle(int n) {
			super();
			this.n = n;
			
			if(n==1){//1 point grid...
				this.indices=new int[1][1];
				this.values=new SFValuenf[1][1];
			}else{
				this.indices=new int[3][n];
				this.values=new SFValuenf[3][n];
			}
			
			//System.err.println("Generating subgrid "+(n-1));
		}
		
		public int round(int index){
			while(index>3)
				index-=3;
			while(index<0)
				index+=3;
			return index;
		}
		
		public void updateValuesFromOuter(SFTriangularGridCircle outerCircle){
			
			//should be a greater grid...
			if(n==1){
				//do nothing for now, than you will think...
			}else{
				
				System.err.println("I'm doing that!! ");
				int size=values.length-2;
				int outerSize=size+3;
				
				//Not the ideal interpolation schema.. that's the linear one based on first positions.
				for (int i = 0; i < 3; i++) {
					values[i][0]=outerCircle.values[i][0].cloneValue();
					values[i][0].mult(-1);
					values[i][0].add(outerCircle.values[i][1]);
					values[i][0].add(outerCircle.values[round(i-1)][outerSize]);	
				}
				
				//missing edges..
			}
		}
		
	}
	
	private int n;
	private SFTriangularGridCircle circles[];
	
	public SFTriangularGrid(int n){
		this.n=n;
		int grids=(n-1)/3;
		
		//System.err.println("grids "+grids+" n "+n);
		circles=new SFTriangularGridCircle[grids+1];
		for (int i = 0; i < circles.length; i++) {
			circles[i]=new SFTriangularGridCircle(n-3*i);
		}
		
		testIndices();
	}
	
	
	public SFCircleIndex getCircle(int i,int j,int k){
		SFCircleIndex circleIndex=new SFCircleIndex();
		int min=i;
		circleIndex.edgeIndex=0;
		circleIndex.inEdgeIndex=j-i;
		if(j<=min && j!=i){
				min=j;
				circleIndex.edgeIndex=2;
				circleIndex.inEdgeIndex=k-j;
		}
		if(k<=min && k!=j){
				min=k;
				circleIndex.edgeIndex=1;
				circleIndex.inEdgeIndex=i-k;
		}
		circleIndex.circleIndex=min;
		return circleIndex;
	}
	
	public void setIndices(int i,int j,int index){
		int k=n-1-i-j;
		setIndices(i, j, k, index);
	}

	public SFTriangularGridCircle[] getCircles() {
		return circles;
	}

	public void setIndices(int i, int j, int k, int index) {
		SFCircleIndex circle=getCircle(i, j, k);
		//if(j==0)
		System.err.println("i "+i+" j "+j+" k "+k+" ["+circle.circleIndex+" "+circle.edgeIndex+" "+circle.inEdgeIndex+"]");
		this.circles[circle.circleIndex].indices[circle.edgeIndex][circle.inEdgeIndex]=index;
	}
	
	public void setValue(int i,int j,SFValuenf value){
		int k=n-1-i-j;
		setValue(i, j, k, value);
	}


	public void setValue(int i, int j, int k, SFValuenf value) {
		SFCircleIndex circle=getCircle(i, j, k);
		this.circles[circle.circleIndex].values[circle.edgeIndex][circle.inEdgeIndex]=value;
	}
	
	public int getIndex(int i,int j){
		int k=n-1-i-j;
		return getIndex(i, j, k);
	}

	
	public int getIndex(int i, int j, int k) {
		SFCircleIndex circle=getCircle(i, j, k);
		//System.err.println("i "+i+" "+j+" ");
		return this.circles[circle.circleIndex].indices[circle.edgeIndex][circle.inEdgeIndex];
	}
	
	public SFValuenf getValue(int i,int j){
		int k=n-1-i-j;
		return getValue(i, j, k);
	}


	public SFValuenf getValue(int i, int j, int k) {
		SFCircleIndex circle=getCircle(i, j, k);
		return this.circles[circle.circleIndex].values[circle.edgeIndex][circle.inEdgeIndex];
	}
	
	
	
	
	public int getN() {
		return n;
	}


	public void testIndices(){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n-i; j++) {
				setIndices(i, j, 0);
			}
		}
	}
	
	public static void main(String[] args) {
		//SFTriangularGrid grid=new SFTriangularGrid(2);
		//grid=new SFTriangularGrid(3);
		SFTriangularGrid grid=new SFTriangularGrid(5);
//		grid=new SFTriangularGrid(5);
//		grid=new SFTriangularGrid(6);
//		grid=new SFTriangularGrid(7);
//		grid=new SFTriangularGrid(8);
//		grid=new SFTriangularGrid(9);
//		grid=new SFTriangularGrid(10);
	}
}
