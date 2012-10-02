package shadow.operational;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.expression.SFExpressionValuesList;
import shadow.system.SFArray;

public class SFPrimitiveArrayValueList implements SFExpressionValuesList{

	private int[] primitiveIndices;
	private SFPrimitiveArray array;
	private int position=0;
	private SFArray<SFValuenf> arrayData;
	
	public SFPrimitiveArrayValueList(int[] primitiveIndices, SFPrimitiveArray array) {
		super();
		this.primitiveIndices = primitiveIndices;
		this.array = array;
	}
	
	public void setGridIndex(int gridIndex){
		position=array.getPrimitive().getIndicesPositions()[gridIndex];
		arrayData=array.getPrimitiveData(gridIndex);
	}
	
	@Override
	public SFValuenf generateValue() {
		return arrayData.generateSample();
	}
	
	public int getDataIndex(int index){
		return primitiveIndices[position+index];
	}
	
	@Override
	public SFValuenf getValue(int index) {
		SFValuenf value=arrayData.generateSample();
		arrayData.getElement(getDataIndex(index), value);
		return value;
	}
	
}
