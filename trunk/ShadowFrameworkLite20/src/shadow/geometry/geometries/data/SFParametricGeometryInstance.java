package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataParametersSet;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public class SFParametricGeometryInstance extends SFDataAsset<SFGeometry>{

	private SFLibraryReference<SFGeometry> parametricGeometry=new SFLibraryReference<SFGeometry>();
	private SFDataParametersSet parameters=new SFDataParametersSet();

	public SFParametricGeometryInstance() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("parameters", this.parameters);
		parameters.addObject("geometry", parametricGeometry);
		setData(parameters);
	}

	private SFGeometry geometry;
	
	@Override
	protected SFGeometry buildResource() {

		parametricGeometry.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFGeometry>>() {
			
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFGeometry> dataset) {

				setUpdateMode(true);
				
				parameters.apply();
				
				geometry = parametricGeometry.getDataset().getResource();
				
				setUpdateMode(false);
			}
		});
		
		
		return geometry;
	}
}
