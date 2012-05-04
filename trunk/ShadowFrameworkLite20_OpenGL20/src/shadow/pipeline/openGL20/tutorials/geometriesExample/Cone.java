package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.pipeline.SFPrimitive;

public class Cone {
	
	public SFQuadsSurfaceGeometry generateGeometry(SFPrimitive primitive) {
		ConeFunction function=new ConeFunction();
		
		SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
				function, new SFSimpleTexCoordGeometryuv(1,1), 5, 2);
		
		quadsSurfaceGeometry.compile();
		return quadsSurfaceGeometry;
	}
	
	public static class ConeFunction extends SFSurfaceFunction{
		
		@Override
		public float getX(float u, float v) {
			// TODO Auto-generated method stub
			//return u*0.6f;
			return 0.5f*(float)(Math.cos(2*Math.PI*u)*(0.5f+0.5f*(v)));
		}
		
		@Override
		public float getY(float u, float v) {
			// TODO Auto-generated method stub
			//return v*0.6f;
			return 0.5f*(float)(Math.sin(2*Math.PI*u)*(0.5f+0.5f*(v)));
		}
		
		@Override
		public float getZ(float u, float v) {
			// TODO Auto-generated method stub
			return 0.5f*(float)((v));
		}
		
		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
	}

}
