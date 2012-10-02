package shadow.geometry.geometries;

import java.util.ArrayList;

import shadow.geometry.SFGeometry;
import shadow.pipeline.SFMesh;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;

public class SFGroupMeshGeometry extends SFGeometry{

	private ArrayList<SFMeshGeometry> geometries=new ArrayList<SFMeshGeometry>();
	private SFMesh mesh = new SFMesh(-1, -1);
	
	public void addGeometry(SFMeshGeometry geometry){
		this.geometries.add(geometry);
		geometry.setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArrayView(getArray(), geometry.getPrimitive()));
	}
	
	@Override
	public void compile() {
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).compile();
		}
	}
	
	@Override
	public void decompile() {
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).decompile();
		}
	}
	
	@Override
	public void destroy() {
		//nothing to do
	}
	
	@Override
	public void drawGeometry(int lod) {
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).drawGeometry(lod);
		}
	}
	

	public SFPrimitiveArray getArray() {
		return mesh.getArray();
	}
	
	public void setArray(SFPrimitiveArray array) {
		this.mesh.setArray(array);
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		//Ok, this is not the best
		return this.geometries.get(0).getPrimitive();
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
