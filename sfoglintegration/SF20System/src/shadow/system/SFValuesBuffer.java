package shadow.system;

public class SFValuesBuffer {

	private float[] data;
	private int vertexSize;
	
	private boolean locked;
	
	public void getElementValue(int index,float[] element){
		int position=vertexSize*index;
		for (int i = 0; i < element.length; i++) {
			element[i]=data[position+i];
		}
	}

	public void setElementValue(int index,float[] element){
		locked=true;
		int position=vertexSize*index;
		for (int i = 0; i < element.length; i++) {
			data[position+i]=element[i];
		}
		locked=false;
	}
	public SFValuesBuffer(int vertexSize) {
		super();
		this.vertexSize = vertexSize;
	}
	
	public void setData(float[] data) {
		this.data = data;
	}

	private boolean changed=true;
	
	public synchronized boolean isLocked(){
		return locked;
	}
	
	public synchronized void setLocked(boolean locked){
		this.locked=locked;
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public float[] getData() {
		return data;
	}
	
	public int valuesSize(){
		return data.length/vertexSize;
	}
	
	public int getVertexSize() {
		return vertexSize;
	}
	
	public int elementsSize(){
		return data.length/vertexSize;
	}
}
