import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.math.SFValue1f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;


public class Test0251b_StrangeGlassAO extends SFAbstractTestAO{

	private static final String FILENAME="test0251b";
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	public static void main(String[] args) {
		execute(new Test0251b_StrangeGlassAO());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("StrangeGlassMesh", new SFDataCenterListener<SFMeshGeometryData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFMeshGeometryData dataset) {
				
				SFMeshGeometry meshGeometry=(SFMeshGeometry)dataset.getResource();
				
				storeXML(dataset);
				
				triangleMesh = convertMeshGeometryInTriangles(meshGeometry);
				
				//triangleMesh = tessellation(triangleMesh);
				
				triangleMesh = calculateAOValues(triangleMesh);
				
				//printAOValues(triangleMesh);
				
			}
				
		});
		
		final SFPrimitive primitive=SFPipeline.getPrimitive("TrianglePND1");
		
		SFMeshGeometry geometry = new SFMeshGeometry(primitive){
	
		@Override
		public void compile() {
			
			super.compile();
			
			for (int i=0; i<triangleMesh.size(); i++){
				
				int positions = getArray().getPrimitiveData(0).generateElements(3);
				int normals = getArray().getPrimitiveData(1).generateElements(3);
				int datas = getArray().getPrimitiveData(2).generateElements(3);
				
				SFVertex3f[] positionsArray = triangleMesh.get(i).getVertices();
				SFVertex3f[] normalsArray = triangleMesh.get(i).getNormals();
				float[] occlusionsArray = triangleMesh.get(i).getAOValues();
			
				for (int j=0; j<3; j++){
				
					getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
					getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);
					getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionsArray[j]));
				
				}
		
				int primitiveIndex=getArray().generateElement();
				int [] indices = { positions,positions+1,positions+2   ,  normals,normals+1,normals+2   ,    datas,datas+1,datas+2 };
				SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
				prIndices.setPrimitiveIndices(indices);
				getArray().setElement(primitiveIndex, prIndices);
			
			}
				
		}
	};

	
	SFObjectModel model=new SFObjectModel();
	model.getModel().setRootGeometry(geometry);
	model.getModel().setMaterialComponent(new SFProgramModuleStructures("Data1OccMat") );
	model.getModel().setTransformComponent(new SFProgramModuleStructures("BasicPND1") );
	
	geometry.init();
	model.init();

	SFViewer.generateFrame(model,new SFProgramModuleStructures("BasicColor"),SFViewer.getRotationController(), SFViewer.getZoomController());
	
	}
	

	public void buildTestData() {
		
	}

}
