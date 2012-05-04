package shadow.geometry.geometries;

import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;

public class SFCompositeGeometryuv implements SFSurfaceGeometryTexCoordFunctionuv{

	private SFSurfaceGeometryTexCoordFunctionuv uGeometry;
	private SFSurfaceGeometryTexCoordFunctionuv vGeometry;
	
	public SFCompositeGeometryuv(SFSurfaceGeometryTexCoordFunctionuv uGeometry,
			SFSurfaceGeometryTexCoordFunctionuv vGeometry) {
		super();
		this.uGeometry = uGeometry;
		this.vGeometry = vGeometry;
	}
	
	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(uGeometry.getTexCoord(u, v, x, y, z).getX(),
				vGeometry.getTexCoord(u, v, x, y, z).getY());
	}
	
	@Override
	public void init() {
		
	}
}
