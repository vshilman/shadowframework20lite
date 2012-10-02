package shadow.renderer.contents.tests;

import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.system.SFArray;

public class Test0028_RationalModels extends SFAbstractTest{

	private static final String FILENAME = "test0028";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0028_RationalModels());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewNode("BlueCircle");
	}
	
	@Override
	public void buildTestData() {

		SFPrimitive primitive=SFPipeline.getPrimitive("Rational2DGeometry");
		
		SFPrimitiveArray array = SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);
		
		int element=array.generateElements(4);
		SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
		
		SFArray<SFValuenf> points=array.getPrimitiveData(1);
		SFArray<SFValuenf> pointsv=array.getPrimitiveData(2);
		SFArray<SFValuenf> normals=array.getPrimitiveData(0);
		int normal=normals.generateElement();
		normals.setElement(normal, new SFVertex3f(0,0,-1));
		
		int position=points.generateElements(13);
		points.setElement(position, new SFVertex3f(0,0,0));
		points.setElement(position+1, new SFVertex3f(1,0,0));
		points.setElement(position+2, new SFVertex3f(0,1,0));
		points.setElement(position+3, new SFVertex3f(-1,0,0));
		points.setElement(position+4, new SFVertex3f(0,-1,0));
		points.setElement(position+5, new SFVertex3f(1,1,0));
		points.setElement(position+6, new SFVertex3f(-1,1,0));
		points.setElement(position+7, new SFVertex3f(-1,-1,0));
		points.setElement(position+8, new SFVertex3f(1,-1,0));
		points.setElement(position+9, new SFVertex3f(0.5f,0,0));
		points.setElement(position+10, new SFVertex3f(0,0.5f,0));
		points.setElement(position+11, new SFVertex3f(-0.5f,0,0));
		points.setElement(position+12, new SFVertex3f(0,-0.5f,0));
		int positionv=pointsv.generateElements(2);
		pointsv.setElement(positionv, new SFValue1f(1));
		pointsv.setElement(positionv+1, new SFValue1f(0.707f));
		
		int indicesArray1[]={
				 0, 0,1,2,9,5,10 , 0,0,0,0,1,0
		};
		indices.setPrimitiveIndices(indicesArray1);
		array.setElement(element, indices);
		
		int indicesArray2[]={
				0 , 0,2,3,10,6,11 , 0,0,0,0,1,0
		};
		indices.setPrimitiveIndices(indicesArray2);
		array.setElement(element+1, indices);
		
		int indicesArray3[]={
				0 , 0,3,4,11,7,12 , 0,0,0,0,1,0
		};
		indices.setPrimitiveIndices(indicesArray3);
		array.setElement(element+2, indices);
		
		int indicesArray4[]={
				0 , 0,4,1,12,8,9 , 0,0,0,0,1,0
		};
		indices.setPrimitiveIndices(indicesArray4);
		array.setElement(element+3, indices);

		SFPrimitiveArrayData primitiveArrayData = new SFPrimitiveArrayData();
		
		primitiveArrayData.setPrimitive("Rational2DGeometry");
		primitiveArrayData.setArray(array);
		
		library.put("PrimitiveData", primitiveArrayData);
		
		SFMeshGeometryData meshGeometryData=new SFMeshGeometryData();
		meshGeometryData.setupGeometry("PrimitiveData", 0, 4);
		library.put("PrimitiveGeometry", meshGeometryData);
		
		float[][] colours = {{0,0,1}};
		SFStructureArrayDataUnit8 material = CommonMaterial.generateMaterialData(colours);
			library.put("BasicMatColours", material);
		
		SFObjectModelData objectModel=new SFObjectModelData();
			objectModel.setGeometry("PrimitiveGeometry");
			objectModel.getTransformComponent().setProgram("BasicPNTransform");
			objectModel.getMaterialComponent().setProgram("BasicMat");
			objectModel.getMaterialComponent().addStructure("BasicMatColours", 0);
			//we insert the material in the library
			library.put("BlueCircle", objectModel);
		
		store(library);
	}

}
