package shadow.renderer.contents.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;

/**
 * 
 * Objective: verify the capability to store a Surface Geometry, load it and show
 * it on a Viewer.
 * 
 * <br/>
 * <h1>SF Contents Test/Tutorial Series</h1>
 * This Test/Tutorial is a part of a series whose aim is showing SF contents.
 * Each test is performed following the basic steps of any SF content lifetime.
 * <ol>
 * 		<li>Editing</li>
 * 		<li>Storing</li>
 * 		<li>Viewing</li>		
 * </ol>
 * Each SF content Module has a '*Data' counterpart which is involved in data processing.
 * The '*.Data' module will generate a proper SF module, and will be discarded when
 * unnecessary. 
 * 
 * @author Alessandro Martinelli
 */
public class Test0001_MushroomGeometry {

	private static final String root = "testsData";
	
	/**
	 * This test has 4 steps
	 * 
	 * 1) Create a Surface Object (Mushroom) and edit its Data. 
	 * 2) Store the Surface Function in file 'testsData\test0001.sf' 
	 * 3) Retrieve the Surface Function from file 'testsData\test0001.sf' 
	 * 4) Show the Surface Function on an SFViewer
	 * 
	 */
	public static void main(String[] args) {
		
		//Preparation
		
		// In order to work properly load utilities at step (3) will need the Datacenter to
		// contain a valid DatasetFactory...
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		//... and a complete ShadowFramework Pipeline must be prepared.
		final CommonPipeline pipeline=new CommonPipeline();

		// 1) Create a Surface Function (Mushroom) and edit its Data.
		// At Editing time we need to work directly on Data version of each
		// Asset
		//Note: Here we use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData function = new SFCurvedTubeFunctionData();	
		function.setFirstCurve(new SFBasisSplineData(new SFVertexFixedListData(3),
				new SFVertex3f(0, 0, 0),
				new SFVertex3f(0.0f, 0.1f, 0),
				new SFVertex3f(0.025f, 0.2f, 0),
				new SFVertex3f(0.0f, 0.3f, 0),
				new SFVertex3f(0.0f, 0.4f, 0),
				new SFVertex3f(0.0f, 0.5f, 0)));
		
		function.setSecondCurve(new SFBasisSplineData(new SFVertexFixedListData(3),
				new SFVertex3f(0.1f, 0, 0),
				new SFVertex3f(0.2f, 0.1f, 0),
				new SFVertex3f(0.2f, 0.2f, 0),
				new SFVertex3f(0.05f, 0.3f, 0),
				new SFVertex3f(0.05f, 0.4f, 0),
				new SFVertex3f(0.45f, 0.5f, 0),
				new SFVertex3f(0.05f, 0.5f, 0)));
		
		// 2) Store the Surface Function in file 'testsData\test0001.sf'
		SFDataUtility.saveDataset(root, "test0001.sf", function);

		// 3) Retrieve the Surface Function from file 'testsData\test0001.sf'
						
		//Note: Here we still use 'SFCurvedTubeFunctionData'
		SFCurvedTubeFunctionData retrievedData = (SFCurvedTubeFunctionData) SFDataUtility
				.loadDataset(root, "test0001.sf");

		//resource will be instance of 'SFCurvedTubeFunction'; we are no more using 'SFCurvedTubeFunctionData'
		SFSurfaceFunction resource=retrievedData.getResource();
		SFGeometry geometry = new SFQuadsSurfaceGeometry(pipeline.getPrimitive(), resource,
				new SFSimpleTexCoordGeometryuv(1,1), 8, 8);
		geometry.init();
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			//Note: material Data and material program are different, because the same material data can be used with different programs
			float[][] colours={{0.5f,0,0}};
			SFStructureArray array=CommonMaterial.generateMaterial(colours); 
		
			//Extract the index-th material from file libraries/library
			SFStructureReference materialReference=new SFStructureReference(array, 0);

			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().addMaterial(new SFProgramStructureReference("BasicMat",materialReference));

		SFViewer.generateFrame(node);
	}
}
