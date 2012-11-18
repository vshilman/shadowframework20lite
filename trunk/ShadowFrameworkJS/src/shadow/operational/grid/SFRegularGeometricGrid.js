

function SFRegularGeometricGrid(n){
	this.n=n;
}

SFRegularGeometricGrid.prototype["getCircles"]=function(){
			return this.circles;
		};
	
SFRegularGeometricGrid.prototype["getN"]=function(){
			return this.n;
		};
		
function SFRegularGeometricGrid_getCircleIndex(i) {
		var circleIndex=new SFCircleIndex();
		var min=i[0];
		circleIndex.edgeIndex=0;
		circleIndex.inEdgeIndex=i[1]-i[0];
		for (var index = 1; index < i.length; index++) {
			if(i[index]<=min && i[index]!=i[index-1]){
				min=i[index];
				circleIndex.edgeIndex=i.length-index;
				circleIndex.inEdgeIndex=i[SFGridCircle_round(index+1, i.length)]-i[index];
			}
		}
		circleIndex.circleIndex=min;
		return circleIndex;
	}