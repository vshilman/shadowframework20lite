package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.geometries.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;

public class TexCoordFunction implements SFSurfaceGeometryTexCoordFunctionuv{

	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(u, v);
	}
}
