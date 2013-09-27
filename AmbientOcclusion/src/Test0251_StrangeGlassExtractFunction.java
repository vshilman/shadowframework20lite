import java.util.ArrayList;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.objects.SFShortByteField;


public class Test0251_StrangeGlassExtractFunction extends SFAbstractTestAO{

	//private static final String FILENAME="test0250";
	private static final String FILENAME="test0251";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0251_StrangeGlassExtractFunction());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		//SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomSurface", new SFDataCenterListener<SFCurvedTubeFunctionData>() {
			SFDataCenter.getDataCenter().makeDatasetAvailable("StrangeGlassSurface", new SFDataCenterListener<SFCurvedTubeFunctionData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFCurvedTubeFunctionData dataset) {
				
				SFCurvedTubeFunction function=(SFCurvedTubeFunction)dataset.getResource();
				
				System.err.println(function);
				
				float u=0.5f;
				float v=0.5f;
				
				System.err.println("Position(0.5f,0.5f) " + function.getPosition(u,v));
				System.err.println("Normal(0.5f,0.5f) " + function.getNormal(u,v));
				System.err.println("Derivative_u(0.5f,0.5f) " + function.getDu(u,v));
				System.err.println("Derivative_v(0.5f,0.5f) " + function.getDv(u,v));
				
			}
				
		});

		//SFDataCenter.getDataCenter().makeDatasetAvailable("PlainMushroomModel", new SFDataCenterListener<SFQuadsSurfaceGeometryData>() {
			SFDataCenter.getDataCenter().makeDatasetAvailable("PlainStrangeGlassModel", new SFDataCenterListener<SFQuadsSurfaceGeometryData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFQuadsSurfaceGeometryData dataset) {
				
				SFShortByteField NuNv = (SFShortByteField) dataset.getSFDataObject().getObject("NuNv");
				
				int Nu=NuNv.getByte(0);
				int Nv=NuNv.getByte(1);
				
				System.err.println("Nu "+Nu+" Nv "+Nv);
				
			}
				
		});
	}

	public void buildTestData() {
		
	}

}
