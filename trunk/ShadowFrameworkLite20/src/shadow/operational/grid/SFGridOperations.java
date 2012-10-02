package shadow.operational.grid;

import shadow.math.SFValuenf;

public class SFGridOperations {

	private static SFValuenf evaluateEdge(SFGridCircle<SFValuenf> circle,int edgeIndex,float t){
		int n=circle.getN();
		float T=t*(n-1);
		int index1=(int)(T);
		T-=index1;
		int index2=index1+1;
		SFValuenf value=circle.getValue(edgeIndex, index1).cloneValue();
		value.mult(1-T);
		if(index2<n-1){
			value.addMult(T, circle.getValue(edgeIndex, index2));
		}else{
			value.addMult(T, circle.getValue(circle.round(edgeIndex+1), 0));
		}
		return value;
	}
	
	//used by SFCurvesMeshGeometry, should be extended to any grid
	public static void updateCircle2(SFTriangularGrid<SFValuenf> sfTriangularGrid,
			int index) {
	
		SFGridCircle<SFValuenf> innerCircle=sfTriangularGrid.circles[index];
		SFGridCircle<SFValuenf> outerCircle=sfTriangularGrid.circles[index-1];
		
		if(innerCircle.getN()==1){

			int n=outerCircle.getN();
			SFValuenf value=new SFValuenf(outerCircle.getValue(0, 0).get().length);
			value.mult(0);
			int count=0;
			for (int edge = 0; edge < outerCircle.getEdges(); edge++) {

				SFValuenf A=outerCircle.getValue(edge, 0).cloneValue();
				SFValuenf AB=outerCircle.getValue(edge, 1).cloneValue();
				SFValuenf AC=outerCircle.getValue(outerCircle.round(edge-1), n-2).cloneValue();
				
				value.addMult(0.75f,AB);
				value.addMult(0.75f,AC);
				value.addMult(-0.666f,A);
				count++;
			}
			value.mult(1.0f/count);
			innerCircle.setValue(0, 0, value);
			
		}else{
			
			int n=outerCircle.getN();
			float outerStep=1.0f/(n-1);
			int n1=innerCircle.getN();
			
			for (int edge = 0; edge < outerCircle.getEdges(); edge++) {

				SFValuenf A=outerCircle.getValue(edge, 0).cloneValue();
				SFValuenf AB=outerCircle.getValue(edge, 1).cloneValue();
				SFValuenf AC=outerCircle.getValue(outerCircle.round(edge-1), n-2).cloneValue();
				SFValuenf AB2=outerCircle.getValue(edge, 2).cloneValue();
				SFValuenf AC2=outerCircle.getValue(outerCircle.round(edge-1), n-3).cloneValue();
				
				
				SFValuenf Ainner=new SFValuenf(AB);
				Ainner.mult(0.5f);
				Ainner.addMult(0.5f, AC);
				Ainner.addMult(0.25f, AB2);
				Ainner.addMult(0.25f, AC2);
				Ainner.addMult(-0.5f, A);
				innerCircle.setValue(edge, 0, Ainner);
			}
			
			for (int edge = 0; edge < outerCircle.getEdges(); edge++) {
				
				SFValuenf firstDirection=new SFValuenf(innerCircle.getValue(edge, 0));
				firstDirection.subtract(outerCircle.getValue(edge, 1));
				SFValuenf secondDirection=new SFValuenf(innerCircle.getValue(innerCircle.round(edge+1), 0));
				secondDirection.subtract(outerCircle.getValue(edge, n-2));

				for (int inEdgeIndex = 1; inEdgeIndex < n1-1; inEdgeIndex++) {
					
					float T=(inEdgeIndex)/(n1-1.0f);
					SFValuenf borderInnerValue=new SFValuenf(firstDirection);
					borderInnerValue.mult(1-T);
					borderInnerValue.addMult(T, secondDirection);
					
					float t=(1.5f+inEdgeIndex)*outerStep;
					borderInnerValue.add(evaluateEdge(outerCircle, edge, t));
					
					innerCircle.setValue(edge, inEdgeIndex, borderInnerValue);
				}
			}	
		}	

	}

}
