package shadow.system;

public class SFIndicesBuffer {

	private short[] data;
	private int vertexSize;
	
	private boolean locked;
	
	public void getParameterValue(int index,float[] element){
		int position=vertexSize*index;
		for (int i = 0; i < element.length; i++) {
			element[i]=data[position+i];
		}
	}
	
	public void setData(short[] data) {
		this.data = data;
	}
	
	public SFIndicesBuffer() {
		super();
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
	
	public short[] getData() {
		return data;
	}
	
	public int getVertexSize() {
		return vertexSize;
	}
}
