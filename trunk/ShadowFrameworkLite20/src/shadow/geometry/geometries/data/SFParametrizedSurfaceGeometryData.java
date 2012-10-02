package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFParametrizedSurfaceGeometryData  extends SFDataAsset<SFGeometry>{

	private SFLibraryReference<SFSurfaceFunction> surfaceFunction=new SFLibraryReference<SFSurfaceFunction>();
	private SFLibraryReference<SFSurfaceFunction> texCoordFunction=new SFLibraryReference<SFSurfaceFunction>();
	private SFLibraryReference<SFMeshGeometry> uvGeometry=new SFLibraryReference<SFMeshGeometry>();
	private SFString primitive=new SFString();
		
	private SFParametrizedGeometry surfaceGeometry;
	
	public SFParametrizedSurfaceGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("uvGeometry", uvGeometry);
		parameters.addObject("surfaceFunction", surfaceFunction);
		parameters.addObject("texCoordFunction", texCoordFunction);
		parameters.addObject("primitive", primitive);
		setData(parameters);
		setReferences(surfaceFunction,texCoordFunction);
	}
	
	public class FunctionListener implements SFDataCenterListener<SFDataAsset<SFSurfaceFunction>>{
		@Override
		public void onDatasetAvailable(String name,SFDataAsset<SFSurfaceFunction> dataset) {
			surfaceGeometry.setMainGeometryFunction(dataset.getResource());
		}
	}
	
	public class TexCoordFunctionuvListener implements SFDataCenterListener<SFDataAsset<SFSurfaceFunction>>{
		@Override
		public void onDatasetAvailable(String name,SFDataAsset<SFSurfaceFunction> dataset) {
			if(dataset!=null)
				surfaceGeometry.setTexCoordGeometry(dataset.getResource());
		}
	}

	@Override
	protected SFParametrizedGeometry buildResource() {
		
		surfaceGeometry=new SFParametrizedGeometry();
		
		uvGeometry.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFMeshGeometry>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFMeshGeometry> dataset) {
				surfaceGeometry.addMeshGeometry(dataset.getResource());
			}
		});
		
		SFPrimitive primitive=SFPipeline.getPrimitive(this.primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		
		surfaceFunction.retrieveDataset(new FunctionListener());
		texCoordFunction.retrieveDataset(new TexCoordFunctionuvListener());
		
		return surfaceGeometry;
	}
}
