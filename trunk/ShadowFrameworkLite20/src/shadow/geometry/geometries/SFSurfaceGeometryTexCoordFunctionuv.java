package shadow.geometry.geometries;

import shadow.math.SFVertex2f;

public interface SFSurfaceGeometryTexCoordFunctionuv {
	public abstract SFVertex2f getTexCoord(float u,float v,float x,float y,float z);
}
