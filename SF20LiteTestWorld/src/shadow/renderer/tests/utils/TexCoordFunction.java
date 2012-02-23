package shadow.renderer.tests.utils;

import shadow.geometry.geometries.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.math.SFVertex2f;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFVoidData;

public class TexCoordFunction implements SFSurfaceGeometryTexCoordFunctionuv{

	@Override
	public SFVertex2f getTexCoord(float u, float v, float x, float y, float z) {
		return new SFVertex2f(u, v);
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new TexCoordFunction();
	}
	
	@Override
	public SFDataObject getSFDataObject() {
		return SFVoidData.getData();
	}
	
	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
