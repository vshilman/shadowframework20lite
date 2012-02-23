package shadow.geometry.geometries;

import shadow.math.SFVertex2f;
import shadow.system.data.SFDataset;

public interface SFSurfaceGeometryTexCoordFunctionuv  extends SFDataset{
	public abstract SFVertex2f getTexCoord(float u,float v,float x,float y,float z);
}
