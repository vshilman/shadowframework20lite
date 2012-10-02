package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.proxies.SFClonedArrayReference;

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
public class Test0012_RadialSurfaceFunction extends SFAbstractTest{

	private static final String FILENAME = "test0012";

	public static void main(String[] args) {
		execute(new Test0012_RadialSurfaceFunction());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();

		viewNode("ArrayModel");

	}
	
	@Override
	public void buildTestData() {

		SFRadialSurfaceFunctionData radialFunction1 = new SFRadialSurfaceFunctionData();
		radialFunction1.setFirstCurve(new SFBasisSplineData(new SFVertexListData16(),true,
				new SFVertex2f(0.6f, 0.4f),new SFVertex2f(0.0f, 0.4f),
				new SFVertex2f(-0.1f, -0.4f), new SFVertex2f(0.7f, -0.4f)
				));
		radialFunction1.setSecondCurve(new SFBasisSplineData(new SFVertexListData16(),
				new SFVertex3f(0.3f, 0, -0.1f),new SFVertex3f(0.7f, 0.0f, -0.1f),
				new SFVertex3f(0.7f, 0.0f, 0)
				));
		
		SFRadialSurfaceFunctionData radialFunction2 = new SFRadialSurfaceFunctionData();
			radialFunction2.setFirstCurve(new SFBasisSplineData(new SFVertexListData16(),true,
					new SFVertex2f(-0.1f, 0.4f),new SFVertex2f(-0.7f, 0.4f),
					new SFVertex2f(-1.0f, -0.4f), new SFVertex2f(0.0f, -0.4f)
					));
			radialFunction2.setSecondCurve(new SFBasisSplineData(new SFVertexListData16(),
					new SFVertex3f(-0.4f, 0, -0.3f),new SFVertex3f(0.4f, 0.0f, -0.3f),
					new SFVertex3f(0.4f, 0.0f, 0)
					));
	
		SFQuadsSurfaceGeometryData geometry = new SFQuadsSurfaceGeometryData();
			geometry.setup("Func", 11, 5, "Triangle2PN");
	
		float[][] colour={{0.5f,0.9f,0.4f}};
		SFDataAsset<SFStructureArray> material = CommonMaterial.generateMaterialData(colour);
		library.put("Color", material);
	
		SFObjectModelData objectModel = new SFObjectModelData();
			objectModel.setGeometry(geometry);
			objectModel.getMaterialComponent().addStructure("Color", 0);
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("BasicMat");
		
			
		SFClonedArrayReference clonedArray = new SFClonedArrayReference();
			clonedArray.setNodeModel(objectModel);
			clonedArray.addName("Func");
			clonedArray.setData(radialFunction1);
			clonedArray.setData(radialFunction2);
		library.put("ArrayModel", clonedArray);
			
		store(library);
	}
}
