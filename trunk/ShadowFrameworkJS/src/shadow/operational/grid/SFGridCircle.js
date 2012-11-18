

function SFGridCircle(n,edges){
	this.setN(n);
	this.setEdges(edges);
	
	if(n==1){//1 point grid...
		this.values=[[0]];
	}else{
		this.values=new Array();
		for(var i=0;i<edges;i++){
			this.values[i]=new Array();
			for(var j=0;j<n;j++){
				this.values[i][j]=0;
			}
		}
	}
}

SFGridCircle.prototype["getValuesSize"]=function(){
			return this.values.length;
		};
		
SFGridCircle.prototype["getValue"]=function(edgeIndex,index){
			return (this.values[edgeIndex][index]);
		};
		
SFGridCircle.prototype["setValue"]=function(edgeIndex,index,value){
			this.values[edgeIndex][index]=value;
		};
		
function SFGridCircle_round(index,n){
		while(index>=n)
			index-=n;
		while(index<0)
			index+=n;
		return index;
}
		
SFGridCircle.prototype["round"]=function(index){
			var n=getEdges();
			return round(index, n);
		};

SFGridCircle.prototype["setN"]=function(n){
			this.n=n;
		};

SFGridCircle.prototype["getN"]=function(){
			return this.n;
		};

SFGridCircle.prototype["setEdges"]=function(edges){
			this.edges=edges;
		};

SFGridCircle.prototype["getEdges"]=function(){
			return edges;
		};
