



function SFQuadrilateralGrid(n){
	this.n=n;
	var grids=(n+1)>>1;
	
	this.circles=new Array();
	
	for (var i = 0; i < grids; i++) {
		this.circles[i]=new SFGridCircle(n-2*i,4);
	}
}

inherit(SFQuadrilateralGrid,SFRegularGeometricGrid);

SFQuadrilateralGrid.prototype["sameGrid"]=function(){
			var grid=new SFQuadrilateralGrid(this.n);
			return grid;
		};
		
SFQuadrilateralGrid.prototype["getHeight"]=function(){
			return this.getN();
			
		};
		
SFQuadrilateralGrid.prototype["getWidth"]=function(){
			return this.getN();
		};
	
SFQuadrilateralGrid.prototype["getCircle"]=function(i,j){
			var k=this.n-1-i;
			var l=this.n-1-j;
			var indices=[i,j,k,l];
			return SFRegularGeometricGrid_getCircleIndex(indices);
		};
	
SFQuadrilateralGrid.prototype["setValue"]=function(i,j,value){
			var circle=this.getCircle(i, j);
			this.circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
		};
	
SFQuadrilateralGrid.prototype["getValue"]=function(i,j){
			var circle=this.getCircle(i, j);
			return this.circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex);
		};
	