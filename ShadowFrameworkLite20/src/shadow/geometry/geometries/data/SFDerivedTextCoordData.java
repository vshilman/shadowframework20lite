package shadow.geometry.geometries.data;

import shadow.geometry.geometries.SFDerivedTexCoord;
import shadow.geometry.geometries.SFDerivedTexCoordFunctionuv;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.pipeline.SFPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFDerivedTextCoordData extends SFDataAsset<SFMeshGeometry>{

	private SFLibraryReference<SFDerivedTexCoordFunctionuv> function=new SFLibraryReference<SFDerivedTexCoordFunctionuv>();
	private SFString primitive=new SFString();
	private SFLibraryReference<SFMeshGeometry> geometry=new SFLibraryReference<SFMeshGeometry>();
	
	public SFDerivedTextCoordData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("function", function);
		parameters.addObject("geometry", geometry);
		parameters.addObject("primitive", primitive);
		setData(parameters);
	}
	
	@Override
	protected SFMeshGeometry buildResource() {
		final SFDerivedTexCoord derivedTextCoord=new SFDerivedTexCoord();
		
		derivedTextCoord.setPrimitive(SFPipeline.getPrimitive(primitive.getString()));
		
		function.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFDerivedTexCoordFunctionuv>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFDerivedTexCoordFunctionuv> dataset) {
				derivedTextCoord.setDerivedTexCoordFunction(dataset.getResource());
			}
		});
		
		geometry.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFMeshGeometry>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFMeshGeometry> dataset) {
				derivedTextCoord.addMeshGeometry(dataset.getResource());
			}
		});
		
		return derivedTextCoord;
	}
}
