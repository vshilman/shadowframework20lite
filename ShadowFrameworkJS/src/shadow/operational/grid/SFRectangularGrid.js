

function SFRectangularGrid(width, height){
	this.width = width;
	this.height = height;
	
	this.grid=new Array();
	for (var i = 0; i < height; i++) {
		this.grid[i]=new Array();
	}
}


SFRectangularGrid.prototype["getValue"]=function(i,j){
			return this.grid[i][j];
		};

SFRectangularGrid.prototype["setValue"]=function(i,j,value){
			this.grid[i][j]=value;
		};

SFRectangularGrid.prototype["getWidth"]=function(){
			return this.width;
		};

SFRectangularGrid.prototype["setWidth"]=function(width){
			this.width=width;
		};

SFRectangularGrid.prototype["getHeight"]=function(){
			return this.height;
		};

SFRectangularGrid.prototype["setHeight"]=function(height){
			this.height=height;
		};
