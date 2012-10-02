package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.SFGroupMeshGeometry;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetList;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFGroupMeshGeometryData extends SFDataAsset<SFGeometry>{

	protected SFDataAssetList<SFMeshGeometry> geometries = new SFDataAssetList<SFMeshGeometry>();
	private SFString primitive=new SFString();
	
	public SFGroupMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("geometries", geometries);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}
	
	@Override
	protected SFGeometry buildResource() {
		final SFGroupMeshGeometry geometry=new SFGroupMeshGeometry();
		SFPrimitive primitive=SFPipeline.getPrimitive(this.primitive.getString());
		geometry.setArray(SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive));
		for (int i = 0; i < geometries.size(); i++) {
			geometry.addGeometry(geometries.get(i).getResource());
		}
		return geometry;
	}
}
