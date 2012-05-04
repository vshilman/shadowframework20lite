package shadow.pipeline;


/**
 * The Description of All the Indices Being part of a Primitive
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitiveIndices {

	private int primitiveIndices[];
	
	public SFPrimitiveIndices(){
		
	}
	
	public SFPrimitiveIndices(SFPrimitive primitive){
		this.primitiveIndices = new int[primitive.getIndicesCount()];	
	}
	
	public void set(SFPrimitiveIndices indices){
		for (int i = 0; i < primitiveIndices.length; i++) {
			primitiveIndices[i]=indices.primitiveIndices[i];	
		}
	}

	public int[] getPrimitiveIndices() {
		return primitiveIndices;
	}

	public void setPrimitiveIndices(int[] primitiveIndices) {
		this.primitiveIndices = primitiveIndices;
	}

	public void setData(SFPrimitiveIndices indices,int firstIndex,int size){
		for (int j = firstIndex; j < firstIndex+size; j++) {
			primitiveIndices[j]=indices.primitiveIndices[j];
		}
	}
	
	public SFPrimitiveIndices clone(){
		SFPrimitiveIndices indices=new SFPrimitiveIndices();
		indices.primitiveIndices=new int[primitiveIndices.length];
		return indices;
	}
}
