package shadow.pipeline;

import shadow.math.SFValuenf;
import shadow.system.SFArray;

public class SFMeshData {

	private SFPrimitiveArray array;
	private int firstElement;
	private int lastElement;
	//NOTE : to be removed from here; not to be used on viewer; see references to 'this.indices'
	private short[] indices;
	private int gridIndex;
	
	public SFMeshData(SFPrimitiveArray array) {
		super();
		this.array = array;
	}
	
	public SFMeshData(SFPrimitiveArray array, int firstElement, int lastElement,
			int dataIndex) {
		super();
		this.array = array;
		this.firstElement = firstElement;
		this.lastElement = lastElement;
		this.gridIndex = dataIndex;
	}

	//NOTE : to be removed from here; not to be used on viewer; see references to 'this.indices'
	public SFMeshData(SFPrimitiveArray array, short[] indices, int gridIndex) {
		super();
		this.array = array;
		this.indices = indices;
		this.gridIndex = gridIndex;
	}
	
	public int getGridN(){
		return array.getPrimitive().getGrid(gridIndex).getN();
	}
	
	public SFPrimitive getPrimitive(){
		return getArray().getPrimitive();
	}
	
	public SFValuenf generateSample(){
		return array.getPrimitiveData(gridIndex).generateSample();
	}
	
	//NOTE : to be removed from here; not to be used on viewer; see references to 'this.indices' 
	public int getIndex(int index){
		if(indices==null)
			return index+firstElement;
		else
			return indices[index];
	}
	//NOTE : to be removed from here; not to be used on viewer; see references to 'this.indices'
	public void setElement(int index,SFValuenf value){
		array.getPrimitiveData(gridIndex).setElement(getIndex(index), value);
	}
	//NOTE : to be removed from here; not to be used on viewer; see references to 'this.indices'
	public void getElement(int index,SFValuenf value){
		array.getPrimitiveData(gridIndex).getElement(getIndex(index), value);
	}
	
	public SFArray<SFValuenf> getArrayData(){
		return array.getPrimitiveData(gridIndex);
	}
	
	public SFPrimitiveArray getArray() {
		return array;
	}
	public void setArray(SFPrimitiveArray array) {
		this.array = array;
	}
	public int getFirstElement() {
		return firstElement;
	}
	public void setFirstElement(int firstElement) {
		this.firstElement = firstElement;
	}
	public int getLastElement() {
		return lastElement;
	}
	public void setLastElement(int lastElement) {
		this.lastElement = lastElement;
	}

	public int getGridIndex() {
		return gridIndex;
	}

	public void setGridIndex(int gridIndex) {
		this.gridIndex = gridIndex;
	}

	public int getSize(){
		return lastElement-firstElement;
	}
	
}
