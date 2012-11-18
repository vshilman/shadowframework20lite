

function SFGridOperations_evaluateEdge(circle,edgeIndex,t){
		var n=circle.getN();
		var T=t*(n-1);
		var index1=(int)(T);
		T-=index1;
		var index2=index1+1;
		var value=circle.getValue(edgeIndex, index1).cloneValue();
		value.mult(1-T);
		if(index2<n-1){
			value.addMult(T, circle.getValue(edgeIndex, index2));
		}else{
			value.addMult(T, circle.getValue(circle.round(edgeIndex+1), 0));
		}
		return value;
	}
	
function SFGridOperations_updateCircle2(sfTriangularGrid,
			index) {
	
		var innerCircle=sfTriangularGrid.circles[index];
		var outerCircle=sfTriangularGrid.circles[index-1];
		
		if(innerCircle.getN()==1){

			var n=outerCircle.getN();
			var value=new SFValuenf(outerCircle.getValue(0, 0).get().length);
			value.mult(0);
			var count=0;
			for (var edge = 0; edge < outerCircle.getEdges(); edge++) {

				var A=outerCircle.getValue(edge, 0).cloneValue();
				var AB=outerCircle.getValue(edge, 1).cloneValue();
				var AC=outerCircle.getValue(outerCircle.round(edge-1), n-2).cloneValue();
				
				value.addMult(0.75,AB);
				value.addMult(0.75,AC);
				value.addMult(-0.666,A);
				count++;
			}
			value.mult(1.0/count);
			innerCircle.setValue(0, 0, value);
			
		}else{
			
			var n=outerCircle.getN();
			var outerStep=1.0/(n-1);
			var n1=innerCircle.getN();
			
			for (var edge = 0; edge < outerCircle.getEdges(); edge++) {

				var A=outerCircle.getValue(edge, 0).cloneValue();
				var AB=outerCircle.getValue(edge, 1).cloneValue();
				var AC=outerCircle.getValue(outerCircle.round(edge-1), n-2).cloneValue();
				var AB2=outerCircle.getValue(edge, 2).cloneValue();
				var AC2=outerCircle.getValue(outerCircle.round(edge-1), n-3).cloneValue();
				
				
				var Ainner=new SFValuenf(AB);
				Ainner.mult(0.5);
				Ainner.addMult(0.5, AC);
				Ainner.addMult(0.25, AB2);
				Ainner.addMult(0.25, AC2);
				Ainner.addMult(-0.5, A);
				innerCircle.setValue(edge, 0, Ainner);
			}
			
			for (var edge = 0; edge < outerCircle.getEdges(); edge++) {
				
				var firstDirection=new SFValuenf(innerCircle.getValue(edge, 0));
				firstDirection.subtract(outerCircle.getValue(edge, 1));
				var secondDirection=new SFValuenf(innerCircle.getValue(innerCircle.round(edge+1), 0));
				secondDirection.subtract(outerCircle.getValue(edge, n-2));

				for (var inEdgeIndex = 1; inEdgeIndex < n1-1; inEdgeIndex++) {
					
					var T=(inEdgeIndex)/(n1-1.0);
					var borderInnerValue=new SFValuenf(firstDirection);
					borderInnerValue.mult(1-T);
					borderInnerValue.addMult(T, secondDirection);
					
					var t=(1.5+inEdgeIndex)*outerStep;
					borderInnerValue.add(evaluateEdge(outerCircle, edge, t));
					
					innerCircle.setValue(edge, inEdgeIndex, borderInnerValue);
				}
			}	
		}	

	}
