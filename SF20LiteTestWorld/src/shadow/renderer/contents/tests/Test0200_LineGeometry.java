package shadow.renderer.contents.tests;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFArray;

public class Test0200_LineGeometry extends SFAbstractTest{

	private static final String FILENAME = "test0200";
	
	public static void main(String[] args) {
		execute(new Test0200_LineGeometry());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFPrimitiveArray array = getAlreadyAvailableDatasetResource("PrimitiveArray");
		
		SFMeshGeometry geometry=new SFMeshGeometry();
		geometry.setArray(array);
		geometry.setFirstElement(0);
		geometry.setLastElement(4);
		geometry.setPrimitive(array.getPrimitive());
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			//Note: material Data and material program are different, because the same material data can be used with different programs
		float[][] color={{1,0,0}};
			SFStructureArray materialArray=CommonMaterial.generateMaterial(color); 
		
			//Extract the index-th material from file libraries/library
			SFStructureReference materialReference=new SFStructureReference(materialArray, 0);

			node.getModel().getTransformComponent().setProgram("BasicP");
			node.getModel().getMaterialComponent().setProgram("BasicMat");
			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().getMaterialComponent().addData(materialReference);

		SFViewer viewer=SFViewer.generateFrame(node);
		viewer.setRotateModel(true, 0.01f);
	}
	
	@Override
	public void buildTestData() {

		SFPrimitive primitive = SFPipeline.getPrimitive("Line2P");
		
		SFPrimitiveArray array = SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);

		int element=array.generateElements(4);
		SFPrimitiveIndices indices=new SFPrimitiveIndices(primitive);
		
		SFArray<SFValuenf> points=array.getPrimitiveData(0);
		
		int position=points.generateElements(8);
		points.setElement(position, new SFVertex3f(1,0,0));
		points.setElement(position+1, new SFVertex3f(0,0,0));
		points.setElement(position+2, new SFVertex3f(0,1,0));
		points.setElement(position+3, new SFVertex3f(-1,1,0));
		points.setElement(position+4, new SFVertex3f(-1,0,0));
		points.setElement(position+5, new SFVertex3f(-1,-1,0));
		points.setElement(position+6, new SFVertex3f(0,-1,0));
		points.setElement(position+7, new SFVertex3f(1,-1,0));
		
		int indicesArray1[]={
				 0, 2, 1
		};
		indices.setPrimitiveIndices(indicesArray1);
		array.setElement(element, indices);
		
		int indicesArray2[]={
				2, 4, 3
		};
		indices.setPrimitiveIndices(indicesArray2);
		array.setElement(element+1, indices);
		
		int indicesArray3[]={
				4, 6, 5
		};
		indices.setPrimitiveIndices(indicesArray3);
		array.setElement(element+2, indices);
		
		int indicesArray4[]={
				6, 0, 7
		};
		indices.setPrimitiveIndices(indicesArray4);
		array.setElement(element+3, indices);
	
		SFPrimitiveArrayData primitiveArrayData = new SFPrimitiveArrayData();
		
		primitiveArrayData.setArray(array);
		primitiveArrayData.setPrimitive("Line2P");
		
		library.put("PrimitiveArray",primitiveArrayData);
		
		store(library);
	}
}
