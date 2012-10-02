package shadow.geometry.geometries;

import shadow.math.SFVertex2f;
import shadow.system.SFInitiable;

public interface SFDerivedTexCoordFunctionuv extends SFInitiable{
	public abstract SFVertex2f getTexCoord(float x,float y,float z,float nx,float ny,float nz);
}
