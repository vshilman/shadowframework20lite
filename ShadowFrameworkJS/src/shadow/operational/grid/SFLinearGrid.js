

function SFLinearGrid(n){
	this.setN(n);
	this.values=new Array();
	for(var i=0;i<n;i++){
		this.values[i]=0;
	}
}

SFLinearGrid.prototype["setValue"]=function(index,value){
			this.values[index]=value;
		};

SFLinearGrid.prototype["sameGrid"]=function(){
			var grid=new SFLinearGrid(this.n);
			return grid;
		};

SFLinearGrid.prototype["getValue"]=function(index){
			return this.values[index];
		};

SFLinearGrid.prototype["getN"]=function(){
			return this.n;
		};
	
SFLinearGrid.prototype["setN"]=function(n){
			this.n=n;
		};