


function SFTriangularGrid(n){
	this.n=n;
	var grids=(n-1)/3;
	this.circles=new Array();
	for (var i = 0; i < grids+1; i++) {
		this.circles[i]=new SFGridCircle(n-3*i,3);
	}
}

inherit(SFTriangularGrid,SFRegularGeometricGrid);

SFTriangularGrid.prototype["sameGrid"]=function(){
			var grid=new SFTriangularGrid(this.n);
			return grid;
		};

SFTriangularGrid.prototype["setValue"]=function(i,j,value){
			var k=this.n-1-i-j;
			this.setValue3(i, j, k, value);
		};

SFTriangularGrid.prototype["setValue3"]=function(i,j,k,value){
			var indices=[i,j,k];
			var circle=SFRegularGeometricGrid_getCircleIndex(indices);
			this.circles[circle.circleIndex].setValue(circle.edgeIndex,circle.inEdgeIndex,value);
		};

SFTriangularGrid.prototype["getValue"]=function(i,j){
			var k=this.n-1-i-j;
			return this.getValue3(i, j, k);
		};

SFTriangularGrid.prototype["getValue3"]=function(i,j,k){
			var indices=[i,j,k];
			var circle=SFRegularGeometricGrid_getCircleIndex(indices);
			return (this.circles[circle.circleIndex].getValue(circle.edgeIndex,circle.inEdgeIndex));
		};

