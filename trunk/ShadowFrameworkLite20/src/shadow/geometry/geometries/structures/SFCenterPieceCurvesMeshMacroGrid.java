package shadow.geometry.geometries.structures;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.operational.grid.SFGridEngine;
import shadow.operational.grid.SFLinearGrid;
import shadow.operational.grid.SFTriangularGrid;
import shadow.operational.grid.macroGrid.SFMacroGrid;
import shadow.pipeline.SFPrimitiveArray;
import shadow.system.SFArray;

public class SFCenterPieceCurvesMeshMacroGrid implements SFMacroGrid{
	
	private SFPrimitiveArray array;
	
	private SFUnoptimezedMacroGridCircle[][] circles;

	public SFCenterPieceCurvesMeshMacroGrid(SFPrimitiveArray array,int edges, SFLinearGrid<Integer>[][] edgeGrids) {
		super();
		
		this.array = array;
		
		int totalEdges=0;
		
		int firstGridSize=array.getPrimitive().getGrid(0).getN();
		for (int i = 0; i < edgeGrids.length; i++) {
			int count = (edgeGrids[i][0].getN()-1)/(firstGridSize-1);
			totalEdges +=count;
		}
		
		circles=new SFUnoptimezedMacroGridCircle[array.getPrimitive().getGridsCount()][];
		
		int primitivePositions=array.generateElements(totalEdges);
		
		for (int gridIndex = 0; gridIndex < array.getPrimitive().getGridsCount(); gridIndex++) {
			int n=array.getPrimitive().getGrid(gridIndex).getN();
			circles[gridIndex]=new SFUnoptimezedMacroGridCircle[n];
			SFArray<SFValuenf> primitiveArray=array.getPrimitiveData(gridIndex);

			for (int i = 0; i < circles[gridIndex].length; i++) {
				circles[gridIndex][i]=new SFUnoptimezedMacroGridCircle(totalEdges,n-i,primitiveArray);
			}
			
			//External Circle
			int edgeGridsIndex=0;
			for (int i = 0; i < totalEdges;) {
				int n1=array.getPrimitive().getGrid(0).getN()-1;
				int count = (edgeGrids[edgeGridsIndex][gridIndex].getN()-1)/(n1);
				for (int j = 0; j < count; j++) {
					for (int k = 0; k < n1; k++) {
						circles[gridIndex][0].setValueIndex(i, k, edgeGrids[edgeGridsIndex][gridIndex].getValue(j*n1+k));//che cosa sarebbe questo?
					}
					i++;
				}
				edgeGridsIndex++;
			}
			
			for (int i = 1; i < circles[gridIndex].length; i++) {
				int position=primitiveArray.generateElements(circles[gridIndex][i].getIndicesCount());
				circles[gridIndex][i].setupIndicesFromPosition(position);
			}
			
			SFTriangularGrid<Integer>[] triangles=generateTriangularGrids(circles[gridIndex]);

			SFGridEngine.loadPrimitiveIndices(array, primitivePositions, gridIndex, triangles);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public SFTriangularGrid<Integer>[] generateTriangularGrids(SFUnoptimezedMacroGridCircle[] circles){
		
		int edgeSize=circles[0].getEdges();
		SFTriangularGrid<Integer>[] grids=(SFTriangularGrid<Integer>[])(new SFTriangularGrid<?>[edgeSize]);
		
		int n=circles[0].getN();
		for (int edgeIndex = 0; edgeIndex < grids.length; edgeIndex++) {
			
			grids[edgeIndex]=new SFTriangularGrid<Integer>(n);
			for(int circleIndex=0;circleIndex<n;circleIndex++){
				if(circleIndex==n-1){
					grids[edgeIndex].setValue(circleIndex, 0, circles[circleIndex].getValueIndex(0, 0));
				}else{
					for(int inEdgeIndex=0;inEdgeIndex<n-circleIndex;inEdgeIndex++){
						grids[edgeIndex].setValue(circleIndex, inEdgeIndex,  circles[circleIndex].getValueIndex(edgeIndex, inEdgeIndex)/*getValue(circleIndex,k,j)*/);
					}
				}
			}
		}
		
		return grids;
	} 

	@Override
	public void updateInternalCircles() {
		
		for (int gridIndex = 0; gridIndex < array.getPrimitive().getGridsCount(); gridIndex++) {
		
			//Adesso voglio rivalutare il CENTERPIECE..
			
			SFUnoptimezedMacroGridCircle centerpiece=this.circles[gridIndex][this.circles[gridIndex].length-1];
			SFUnoptimezedMacroGridCircle border=this.circles[gridIndex][0];
			
			SFValuenf centerPieceNormal=evaluateCenterPiece(centerpiece, border);
			evaluateInnerCorners(gridIndex,centerPieceNormal);
			
			for (int i = 1; i < this.circles[gridIndex].length-1; i++) {
				evaluateInnerBorders(this.circles[gridIndex][i-1], this.circles[gridIndex][i]);
			}
		}
	}
	
	public void evaluateInnerBorders(SFUnoptimezedMacroGridCircle outer,SFUnoptimezedMacroGridCircle inner){
		
		//TODO: still need a bit of reworking, this is not how its done...
		
		int edgeSize1=outer.getN();
		int edgeSize2=edgeSize1-1;
		
		//System.err.println("edgeSize "+edgeSize2+" "+edgeSize1);
		for (int edgeIndex = 0; edgeIndex < outer.getEdges(); edgeIndex++) {
			SFValuenf value=outer.getArray().generateSample();
			outer.getValue(edgeIndex, 0, value);
			SFValuenf value2=value.cloneValue();
			SFValuenf value3=value.cloneValue();
			for (int i = 1; i < edgeSize2-1; i++) {
				outer.getValue(edgeIndex, i, value2);
				value2.subtract(value);
				outer.getValue(edgeIndex, 0, value3);
				value3.add(value2);
				inner.setValue(edgeIndex, i, value3);
			}
		}
		
	}
	
	public void evaluateInnerCorners(int gridIndex,SFValuenf centerPieceNormal){
		
		//normalize...
		centerPieceNormal.mult(1.0f/(float)Math.sqrt(centerPieceNormal.dot(centerPieceNormal)));

		SFUnoptimezedMacroGridCircle centerpiece=this.circles[gridIndex][this.circles[gridIndex].length-1];
		SFUnoptimezedMacroGridCircle border=this.circles[gridIndex][0];
		
		
		SFValuenf A=centerpiece.getArray().generateSample();
		SFValuenf B=border.getArray().generateSample();
		
		centerpiece.getValue(0, 0, B);
		
		for (int edgeIndex = 0; edgeIndex < border.getEdges(); edgeIndex++) {
			border.getValue(edgeIndex, 0, A);
			
			SFValuenf AB=new SFValuenf(A);
			AB.mult(0.6666f);
			AB.addMult(0.333f, B);
			SFValuenf BA=new SFValuenf(B);
			BA.mult(0.6666f);
			BA.addMult(0.333f, A);

			SFValuenf normalA=evaluateBorderNormal(gridIndex, border, edgeIndex);
			
			SFValuenf ABtemp=new SFValuenf(A);
			ABtemp.subtract(AB);
			float kAB=normalA.dot(ABtemp);
			AB.addMult(kAB, normalA);
			
			ABtemp.set(AB);
			ABtemp.subtract(A);
			
			SFValuenf BAtemp=new SFValuenf(B);
			BAtemp.subtract(BA);
			float kBA=centerPieceNormal.dot(BAtemp);
			BA.addMult(kBA, centerPieceNormal);
			
			BAtemp.set(BA);
			BAtemp.subtract(B);
			
			for (int i = 1; i < this.circles[gridIndex].length-1; i++) {
				float t=i/(1.0f*this.circles[gridIndex].length-1);
				
				SFValuenf value=new SFValuenf(A);
				value.mult((1-t)*(1-t)*(1-t));
				value.addMult(3*(1-t)*(1-t)*t, AB);
				value.addMult(3*(1-t)*t*t, BA);
				value.addMult(t*t*t, B);

				this.circles[gridIndex][i].setValue(edgeIndex, 0, value);
				
			}
		}
		
	}
	
	private static SFValuenf evaluateBorderNormal(int gridIndex,SFUnoptimezedMacroGridCircle border,int edgeIndex){
	
		SFValuenf temp=border.getArray().generateSample();
		border.getValue(edgeIndex, 0, temp);
		SFValuenf A=new SFValuenf(temp);
		border.getValue(edgeIndex, 1, temp);
		SFValuenf D=new SFValuenf(temp);
		border.getValue(edgeIndex, 2, temp);
		SFValuenf B=new SFValuenf(temp);
		
		border.getValue(edgeIndex-1, border.getN()-2, temp);
		SFValuenf E=new SFValuenf(temp);
		border.getValue(edgeIndex-1, border.getN()-3, temp);
		SFValuenf C=new SFValuenf(temp);
		
		D.mult(2);
		D.addMult(-1.5f, A);
		D.addMult(-0.5f, B);
		
		E.mult(2);
		E.addMult(-1.5f, A);
		E.addMult(-0.5f, C);
		
		SFVertex3f dn=new SFVertex3f();
		dn.set(D);
		SFVertex3f en=new SFVertex3f();
		en.set(E);
		
		SFVertex3f normal=dn.cross(en);
		normal.normalize3f();
		
		return normal;
	}
	
	private static SFValuenf evaluateEdge(SFUnoptimezedMacroGridCircle circle,int edgeIndex,float t){
		int n=circle.getN();
		float T=t*(n-1);
		int index1=(int)(T);
		T-=index1;
		int index2=index1+1;
		SFValuenf value=circle.getArray().generateSample();
		circle.getValue(edgeIndex, index1,value);
		value.mult(1-T);
		if(index2<n-1){
			SFValuenf value2=circle.getArray().generateSample();
			circle.getValue(edgeIndex, index2,value2);
			value.addMult(T, value2);
		}else{
			SFValuenf value3=circle.getArray().generateSample();
			circle.getValue(edgeIndex+1, 0,value3);
			value.addMult(T, value3);
		}
		return value;
	}
	
	
	public SFValuenf evaluateCenterPiece(SFUnoptimezedMacroGridCircle centerpiece,SFUnoptimezedMacroGridCircle border){
		
		float delta=0.88888f;
		float cornerWeigth=(-delta)/(border.getEdges());
		float edgeWeigth=(1+delta)/(border.getEdges());
		
		SFValuenf centerPieceValue=centerpiece.getArray().generateSample();
		
		SFValuenf centerPieceNormal=new SFValuenf(centerPieceValue);
		
		SFValuenf temp=centerpiece.getArray().generateSample();
		for (int i = 0; i < border.getEdges(); i++) {
			border.getValue(i, 0, temp);
			centerPieceValue.addMult(cornerWeigth,temp);
			centerPieceValue.addMult(edgeWeigth, evalueEdgeCenter(border,i));
			centerPieceNormal.add(temp);
		}
		
		centerpiece.setValue(0, 0, centerPieceValue);
		centerPieceNormal.mult(-1.0f/border.getEdges());
		centerPieceNormal.add(centerPieceValue);
		
		return centerPieceNormal;
	}
	
	public SFValuenf evalueEdgeCenter(SFUnoptimezedMacroGridCircle border,int index){
		if(border.getN()%2==1){
		
			int position=border.getN()>>1;
			SFValuenf temp=border.getArray().generateSample();
			border.getValue(index, position, temp);
			return temp;
			
		}else{
			
			int position=border.getN()>>1;
			SFValuenf temp1=border.getArray().generateSample();
			SFValuenf temp2=new SFValuenf(temp1);
			border.getValue(index, position, temp1);
			border.getValue(index, position+1, temp2);
			temp1.add(temp2);
			temp1.mult(0.5f);
			return temp1;
		}
	}
	
}
