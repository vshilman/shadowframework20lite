package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.SFQuadsGridGeometry;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShortByteField;
import shadow.system.data.objects.SFString;

public class SFQuadsSurfaceGeometryData extends SFDataAsset<SFGeometry>{

	private SFShortByteField NuNv=new SFShortByteField((short)0);
	private SFLibraryReference<SFSurfaceFunction> surfaceFunction=new SFLibraryReference<SFSurfaceFunction>();
	private SFLibraryReference<SFSurfaceFunction> texCoordFunction=new SFLibraryReference<SFSurfaceFunction>();
	private SFString primitive=new SFString();
		
	private SFParametrizedGeometry surfaceGeometry;
	
	public SFQuadsSurfaceGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("NuNv", NuNv);
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
		
		SFQuadsGridGeometry gridGeometry=new SFQuadsGridGeometry();
		
		gridGeometry.getQuadsGrid().setNu( NuNv.getByte(0));
	
		gridGeometry.getQuadsGrid().setNv( NuNv.getByte(1));
		
		surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
		SFPrimitive primitive=SFPipeline.getPrimitive(this.primitive.getString());
		surfaceGeometry.setPrimitive(primitive);
		gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
		
		surfaceFunction.retrieveDataset(new FunctionListener());
		texCoordFunction.retrieveDataset(new TexCoordFunctionuvListener());
		
		return surfaceGeometry;
	}

	public void setNu(int nu){
		NuNv.setByte(1, nu);
	}
	
	public void setNv(int nv){
		NuNv.setByte(0, nv);
	}
	
	public int getNu() {
		return NuNv.getByte(1);
	}

	public int getNv() {
		return NuNv.getByte(0);
	}
	
	public void setNuNv(int nu,int nv){
		setNu(nu);
		setNv(nv);
	}

	public SFLibraryReference<SFSurfaceFunction> getSurfaceFunction() {
		return surfaceFunction;
	}

	public SFLibraryReference<SFSurfaceFunction> getTexCoordFunction() {
		return texCoordFunction;
	}
	
	public SFString getPrimitive() {
		return primitive;
	}


	public void setup(String function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceFunction> textCoordData, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setString(primitive);
	}

	public void setup(SFDataAsset<SFSurfaceFunction> function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceFunction> textCoordData, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setDataset(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setString(primitive);
	}
	

	public void setup(String function, int Nu, int Nv, String texCoordFunction,String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getTexCoordFunction().setReference(texCoordFunction);
		getPrimitive().setString(primitive);
	}
	
	public void setup(String function, int Nu, int Nv, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getPrimitive().setString(primitive);
	}
	
	public void setup(SFDataAsset<SFSurfaceFunction> function, int Nu, int Nv, String primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setDataset(function);
		getPrimitive().setString(primitive);
	}
}
