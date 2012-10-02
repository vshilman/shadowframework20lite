package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.proxies.SFClonedArrayReference;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;

/**
 * If you need to align this test Data, run the {@link SFGenerateAllTestData} utility once.
 * No data will be generated (so nothing will work) until you do that; as an
 * alternative, you can set SFAbstractTest.storeData to true, and then run each test
 * one by one in test number order.
 * <br/> 
 * Go to {@link SFAbstractTest} for general informations about this tests.
 * <br/>
 * Open the related FILENAME.xml file for a detailed view of this test contents. 
 * <br/>
 * Objective: TODO 
 * 
 * @author Alessandro Martinelli
 */
public class Test0013_ClonedArrayReference extends SFAbstractTest{

	private static final String FILENAME = "test0013";

	public static void main(String[] args) {
		execute(new Test0013_ClonedArrayReference());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewNode("PebblesModel");
	}
	
	@Override
	public void buildTestData() {

		SFRadialSurfaceFunctionData radialFunction = new SFRadialSurfaceFunctionData();
		radialFunction.setFirstCurve(new SFBasisSplineData("A",true));
		radialFunction.setSecondCurve(new SFBasisSplineData("B"));

		SFQuadsSurfaceGeometryData geometry = new SFQuadsSurfaceGeometryData();
			geometry.setup(radialFunction, 3, 5, new SFSimpleTexCoordGeometryuvData(), "Triangle2PNTxO");
		
		//copying "PerlinTexture" from Test0004 to library
		SFViewerObjectsLibrary test0004Library=new SFViewerObjectsLibrary(root, "test0004.sf");
			library.put("PerlinTexture", test0004Library.getLibrary().retrieveDataset("PerlinTexture"));
				
		SFObjectModelData objectModel = new SFObjectModelData();
			objectModel.getMaterialComponent().addTexture("PerlinTexture");
			objectModel.getTransformComponent().setProgram("BasicPNTx0Transform");
			objectModel.getMaterialComponent().setProgram("TexturedMat");
			objectModel.setGeometry(geometry);
	
		SFClonedArrayReference clonedArray = new SFClonedArrayReference();
			clonedArray.setNodeModel(objectModel);
			clonedArray.addName("A");
			clonedArray.addName("B");
			generateAllSplines(clonedArray);
			library.put("PebblesModel", clonedArray);
			
		store(library);
	}

	
	private static void addSpline(SFClonedArrayReference clonedArray,int size,float... data) {

		SFVertexListData16 centralSplineData = new SFVertexListData16();
		int length=data.length/size;
		for (int i = 0; i < length; i++) {
			float[] vertices=new float[size];
			for (int j = 0; j < size; j++) {
				vertices[j]=data[size*i+j];
			}
			centralSplineData.addVertices(vertices);
		}
		
		clonedArray.setData(centralSplineData);
	}
	
	private static void generateAllSplines(SFClonedArrayReference clonedArray) {
	
		addSpline(clonedArray, 2, 0.6f, 0.4f,
				0.0f, 0.4f,
				-0.1f, -0.4f,
				0.7f, -0.4f);
		addSpline(clonedArray, 3, 0.3f, 0, -0.1f,
				0.7f, 0.0f, -0.1f,
				0.7f, 0.0f, 0);
		
		addSpline(clonedArray,  2, 0.0f, 0.7f,
				-0.7f, 0.8f, 
				-0.6f, 0.0f, 
				-0.1f, 0.0f);
		addSpline(clonedArray,  3,-0.3f, 0.4f, -0.1f,
				-0.0f, 0.4f, -0.1f,
				-0.0f, 0.4f, 0);
		
		addSpline(clonedArray, 2, -0.1f, -0.1f, 
				-0.9f,-0.1f, 
				-0.6f, -0.9f, 
				-0.2f, -0.9f);
		addSpline(clonedArray, 3,-0.5f, -0.4f, -0.1f,
				-0.2f, -0.4f, -0.1f,
				-0.2f, -0.4f, 0);

		addSpline(clonedArray, 2, 0.9f, 0.9f, 
				0.5f, 0.9f, 
				0.6f, 0.1f, 
				0.9f, 0.0f);
		addSpline(clonedArray,  3,0.7f, 0.7f, -0.1f,
				1.0f, 0.7f, -0.1f,
				1.0f, 0.7f, 0);

		addSpline(clonedArray, 2, 0.4f, 0.4f,
				0.6f, 1.0f, 
				-0.1f, 1.2f, 
				0.0f, 0.5f);
		addSpline(clonedArray,  3,0.2f, 0.8f, -0.1f,
				0.55f, 0.8f, -0.1f,
				0.6f, 0.8f, 0);

		addSpline(clonedArray,  2, 0.1f, -0.5f, 
				0.1f, -0.7f, 
				1.0f, -1.0f, 
				0.7f, -0.5f);
		addSpline(clonedArray,  3,0.5f, -0.7f, -0.1f,
				0.7f, -0.7f, -0.1f,
				0.8f, -0.7f, 0);

		addSpline(clonedArray,  2, -0.6f, 0.6f,
				-0.6f, 1.0f,
				-1.1f, 0.9f,
				-1.0f, 0.6f);
		addSpline(clonedArray,  3,-0.9f, 0.8f, -0.1f,
				-0.6f, 0.8f, -0.1f,
				-0.6f, 0.8f, 0);

		addSpline(clonedArray,  2, 0.8f, -0.5f, 
				1.1f, -0.5f,
				1.2f, 0.8f, 
				0.7f, 0.0f);
		addSpline(clonedArray,  3, 1.0f, -0.2f, -0.1f,
				1.2f, -0.2f, -0.1f,
				1.2f, -0.2f, 0);
		
	}
}

