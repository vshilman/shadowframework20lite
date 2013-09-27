import java.util.ArrayList;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;


public class Test0251_StrangeGlassExtractFunction extends SFAbstractTestAO{

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
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("StrangeGlassSurface", new SFDataCenterListener<SFCurvedTubeFunctionData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFCurvedTubeFunctionData dataset) {
				
				SFCurvedTubeFunction function=(SFCurvedTubeFunction)dataset.getResource();
				
				System.err.println(function);
				
				float u=0.5f;
				float v=0.5f;
				
				System.err.println("Position(0.5f,0.5f) " + function.getPosition(u,v));
				System.err.println("Normal(0.5f,0.5f) " + function.getPosition(u,v));
				System.err.println("Derivative_u(0.5f,0.5f) " + function.getDu(u,v));
				System.err.println("Derivative_v(0.5f,0.5f) " + function.getDv(u,v));
				
				
			}
				
		});
		
	}

	public void buildTestData() {
		
	}

}
