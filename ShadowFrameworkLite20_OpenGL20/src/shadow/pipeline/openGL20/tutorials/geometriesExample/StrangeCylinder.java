package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.editing.SFConcreteTriangleExtractor;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.pipeline.SFPrimitive;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;

public class StrangeCylinder {
	
	public SFQuadsSurfaceGeometry generateGeometry(SFPrimitive primitive) {
		CylinderFunction function=new CylinderFunction();
		
		SFConcreteTriangleExtractor trianglesExtractor=new SFConcreteTriangleExtractor();
		SFQuadsSurfaceGeometry quadsSurfaceGeometry=new SFQuadsSurfaceGeometry(primitive,
				function, new TexCoordFunction(), trianglesExtractor, 3, 2);
		
		quadsSurfaceGeometry.compile();
		return quadsSurfaceGeometry;
	}
	
	
	public static class CylinderFunction extends SFSurfaceFunction{
		
		@Override
		public float getX(float u, float v) {
			// TODO Auto-generated method stub
			//return u*0.6f;
			return 0.5f*(float)(Math.cos(2*Math.PI*u)*(0.5f+0.5f*(v*u)));
		}
		
		@Override
		public float getY(float u, float v) {
			// TODO Auto-generated method stub
			//return v*0.6f;
			return 0.5f*(float)(Math.sin(2*Math.PI*u)*(0.5f+0.5f*(v*u)));
		}
		
		@Override
		public float getZ(float u, float v) {
			// TODO Auto-generated method stub
			return 0.5f*(float)((v));
		}
		
		@Override
		public SFDataset generateNewDatasetInstance() {
			return null;
		}
		
		@Override
		public SFDataObject getSFDataObject() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
