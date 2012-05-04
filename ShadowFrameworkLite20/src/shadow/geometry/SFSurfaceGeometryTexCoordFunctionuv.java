package shadow.geometry;

import shadow.math.SFVertex2f;
import shadow.system.SFInitiable;

public interface SFSurfaceGeometryTexCoordFunctionuv extends SFInitiable{
	public abstract SFVertex2f getTexCoord(float u,float v,float x,float y,float z);
}
