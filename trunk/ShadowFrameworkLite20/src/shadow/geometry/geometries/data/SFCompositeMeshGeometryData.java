package shadow.geometry.geometries.data;

import shadow.geometry.geometries.SFCompositeMeshGeometry;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.pipeline.SFPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFLibraryReferenceList;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFCompositeMeshGeometryData extends SFDataAsset<SFMeshGeometry>{

	private SFLibraryReferenceList<SFMeshGeometry> geometries=
		new SFLibraryReferenceList<SFMeshGeometry>(new SFLibraryReference<SFMeshGeometry>());
	private SFString primitive=new SFString();
	
	public SFCompositeMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("geometries", geometries);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}
	
	public void setPrimitive(String primitive) {
		this.primitive.setString(primitive);
	}
	
	public void addGeometry(String geometry) {
		this.geometries.add(geometry);
	}
	
	@Override
	protected SFMeshGeometry buildResource() {
		final SFCompositeMeshGeometry compositeMeshGeometry=new SFCompositeMeshGeometry();
		compositeMeshGeometry.setPrimitive(SFPipeline.getPrimitive(this.primitive.getString()));
		for (int i = 0; i < geometries.size(); i++) {
			geometries.get(i).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFMeshGeometry>>() {
				@Override
				public void onDatasetAvailable(String name, SFDataAsset<SFMeshGeometry> dataset) {
					compositeMeshGeometry.addGeometry(dataset.getResource());
				}
			});
		}
	
		return compositeMeshGeometry;
	}
}
