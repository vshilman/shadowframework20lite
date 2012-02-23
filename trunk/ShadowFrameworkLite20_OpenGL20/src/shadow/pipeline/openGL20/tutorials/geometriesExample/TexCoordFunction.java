package shadow.pipeline.openGL20.tutorials.geometriesExample;

import shadow.geometry.geometries.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;

public class TexCoordFunction implements SFSurfaceGeometryTexCoordFunctionuv{

	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(u, v);
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		// TODO Auto-generated method stub
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
