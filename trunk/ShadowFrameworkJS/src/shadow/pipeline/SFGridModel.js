
function SFGridModel(edges,corners) {
	this.edges = edges;
	this.corners=corners;
}

SFGridModel.prototype["getEdges"]=function(){
			return this.edges;
		};


SFGridModel.prototype["getCorners"]=function(){
			return this.corners;
		};

SFGridModel.prototype["getGridSize"]=function(n){
			if(this.edges==3){
				return ((n+1)*n)/2;
			}else if(this.edges==4){
				return n*n;
			}
			return n;
		};
		
var SFGridModel_Triangle=new SFGridModel(3, 3);
var SFGridModel_Quad=new SFGridModel(4, 4);
var SFGridModel_Line=new SFGridModel(1, 2);

function SFGridModel_valueOf(type){
			var model=SFGridModel_Triangle;
			if(type=="Quad"){
				model=SFGridModel_Quad;
			}
			if(type=="Line"){
				model=SFGridModel_Line;
			}
			return model;
		};
		