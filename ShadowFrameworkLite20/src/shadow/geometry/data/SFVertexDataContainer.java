package shadow.geometry.data;

import shadow.math.SFValuenf;

public interface SFVertexDataContainer<T extends SFValuenf>{

	public void getVertex(T write);
	
	public void setVertex(T read);
}
