package shadow.renderer.contents.tests;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.SFArray;
import shadow.system.data.SFDataCenter;

public class Test0028_RationalModels {

	private static final String root = "testsData";

	private static CommonPipeline pipeline;

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		// Also prepare the pipeline
		pipeline = new CommonPipeline();
		// 3) Retrieve the library and make model available so that it can be
		// added to a Viewer

		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, "test0002.sf"));

		SFPrimitive primitive = new SFPrimitive();
		primitive.addPrimitiveElement(PrimitiveBlock.NORMAL,
				(SFProgramComponent) (SFPipeline.getModule("Constant")));
		primitive.addPrimitiveElement(PrimitiveBlock.POSITION,
				(SFProgramComponent) (SFPipeline.getModule("RationalTriangle2")));
		primitive.setAdaptingTessellator((SFProgramComponent) (SFPipeline.getModule("BasicTess")));

		SFPrimitiveArray array = SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);

		prepareArray(primitive, array);
		
		SFMeshGeometry geometry=new SFMeshGeometry();
		geometry.setArray(array);
		geometry.setFirstElement(0);
		geometry.setLastElement(4);
		geometry.setPrimitive(primitive);
		
		SFObjectModel node=new SFObjectModel();
		
		node.getModel().setRootGeometry(geometry);

			//Note: material Data and material program are different, because the same material data can be used with different programs
		float[][] color={{1,0,0}};
			SFStructureArray materialArray=CommonMaterial.generateMaterial(color); 
		
			//Extract the index-th material from file libraries/library
			SFStructureReference materialReference=new SFStructureReference(materialArray, 0);

			//Set the selected material program to the node
			//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
			node.getModel().addMaterial(new SFProgramStructureReference("BasicMat",materialReference));

		SFViewer viewer=SFViewer.generateFrame(node);
		viewer.setRotateModel(true, 0.01f);
	}

	private static void prepareArray(SFPrimitive primitive, SFPrimitiveArray array) {
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
	
		//TODO : store SFPrimitiveIndices
		
		//TODO : store Basic MeshGeometry
	}

}
