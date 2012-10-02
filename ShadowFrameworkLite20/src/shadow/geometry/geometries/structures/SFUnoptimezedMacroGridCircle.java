package shadow.geometry.geometries.structures;

import shadow.math.SFValuenf;
import shadow.operational.grid.macroGrid.SFMacroGridCircle;
import shadow.system.SFArray;

public class SFUnoptimezedMacroGridCircle  implements SFMacroGridCircle<SFValuenf>{
	
	private int edges;
	private int n;
	private int[][] indices;
	private SFArray<SFValuenf> array;
	
	public SFUnoptimezedMacroGridCircle(int edges,int n,SFArray<SFValuenf> array){
		this.edges=edges;
		this.n=n;
		this.array=array;
		if(n>1){
			indices=new int[edges][];
			for (int i = 0; i < indices.length; i++) {
				indices[i]=new int[n-1];
			}
		}else if(n==1){
			indices=new int[1][1];
		}
	}
	
	@Override
	public int getEdges() {
		return edges;
	}
	
	@Override
	public int getN() {
		return n;
	}
	
	public int getIndicesCount(){
		if(n==1)
			return 1;
		else
			return edges*(n-1);
	}
	
	
	
	public SFArray<SFValuenf> getArray() {
		return array;
	}

	public void setupIndicesFromPosition(int firstPosition){
		if(n>1){
			for (int i = 0; i < indices.length; i++) {
				for (int j = 0; j < n-1; j++) {
					indices[i][j]=firstPosition;
					firstPosition++;
				}
			}
		}else if(n==1){
			indices[0][0]=firstPosition;
		}
	}

	public void setValueIndex(int edgeIndex, int index,int value) {
		indices[edgeIndex][index]=value;
	}
	
	public int getValueIndex(int edgeIndex, int index) {
		if(index==n-1){
			index=0;
			edgeIndex++;
		}
		if(edgeIndex>=indices.length){
			edgeIndex-=indices.length;
		}
		if(edgeIndex<0){
			edgeIndex+=indices.length;
		}
		int valueIndex=indices[edgeIndex][index];
		return valueIndex;
	}

	@Override
	public void getValue(int edgeIndex, int index, SFValuenf value) {
		array.getElement(getValueIndex(edgeIndex, index), value);
	}
	
	@Override
	public void setValue(int edgeIndex, int index, SFValuenf value) {
		array.setElement(getValueIndex(edgeIndex, index), value);
	}
}
