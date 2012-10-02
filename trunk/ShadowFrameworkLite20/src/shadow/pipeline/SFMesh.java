package shadow.pipeline;


public class SFMesh {
	
	private SFPrimitiveArray array;
	private int firstElement;
	private int lastElement;
	private SFIndexRange[] ranges;

	public SFMesh(int firstElement, int lastElement) {
		this.setFirstElement(firstElement);
		this.setLastElement(lastElement);
	}

	public SFPrimitiveArray getArray() {
		return array;
	}
	
	public SFPrimitive getPrimitive() {
		return array.getPrimitive();
	}

	public void setArray(SFPrimitiveArray array) {
		this.array = array;
		this.ranges=new SFIndexRange[array.getPrimitive().getBlocks().length];
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
	
	public int getSize(){
		return lastElement-firstElement;
	}

	public SFIndexRange[] getPrimitiveDataRanges() {
		return ranges;
	}

	public void setPrimitiveDataRanges(SFIndexRange[] positions) {
		this.ranges = positions;
	}
	
	public void evaluateRanges(){
		
		SFPrimitive primitive=array.getPrimitive();
		
		this.ranges=new SFIndexRange[primitive.getGridsCount()];
		
		for (int gridIndex = 0; gridIndex < this.ranges.length; gridIndex++) {
			this.ranges[gridIndex]=new SFIndexRange(-1, -1);
		}
		
		SFPrimitiveIndices indices=array.generateSample();
		int[] positions=primitive.getIndicesPositions();
		int[] sizes=primitive.getIndicesSizes();
		
		for (int i = firstElement; i < lastElement; i++) {
			array.getElement(i, indices);
			
			for (int gridIndex = 0; gridIndex < this.ranges.length; gridIndex++) {
				for (int j = 0; j < sizes[gridIndex]; j++) {
					int index=indices.getPrimitiveIndices()[j+positions[gridIndex]];
					this.ranges[gridIndex].insertIndex(index);
				}
			}
		}
		
	}
	
}