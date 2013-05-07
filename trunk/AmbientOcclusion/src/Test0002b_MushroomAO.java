import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFInitiator;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;


public class Test0002b_MushroomAO extends SFAbstractTestAO{

	private static final String FILENAME="test0002b";
	
	public static void main(String[] args) {
		execute(new Test0002b_MushroomAO());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}

	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomMesh", new SFDataCenterListener<SFMeshGeometryData>() {
			
			@Override
			public void onDatasetAvailable(String name, SFMeshGeometryData dataset) {
				
				SFMeshGeometry meshGeometry=(SFMeshGeometry)dataset.getResource();
				
				storeXML(dataset);
				
				ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
				ArrayList<Triangle> triangleCurrent = new ArrayList<Triangle>();

				triangleMesh = convertMeshGeometryInTriangles(meshGeometry);
	
				
				for (int k=0; k<triangleMesh.size(); k++){
					
					triangleCurrent = (ArrayList<Triangle>)triangleMesh.clone();
					triangleCurrent.remove(k);
					triangleMesh.get(k).setAO1(calculateAOValue(triangleMesh.get(k).getVertex1(), triangleMesh.get(k).getNormal1(), triangleCurrent));
					triangleMesh.get(k).setAO2(calculateAOValue(triangleMesh.get(k).getVertex2(), triangleMesh.get(k).getNormal2(), triangleCurrent));
					triangleMesh.get(k).setAO3(calculateAOValue(triangleMesh.get(k).getVertex3(), triangleMesh.get(k).getNormal3(), triangleCurrent));
					
				}
				
				for (int z=0; z<triangleMesh.size(); z++){
					
					 System.out.println(z);
					 System.out.println(triangleMesh.get(z).getAO1() + " " + triangleMesh.get(z).getAO2() + " " + triangleMesh.get(z).getAO3() );
					 
				}
				
				triangleMesh = tessellation(triangleMesh);
				
				for (int t=0; t<triangleMesh.size(); t++){
					triangleCurrent = (ArrayList<Triangle>)triangleMesh.clone();
					triangleCurrent.remove(t);
					triangleMesh.get(t).setAO1(calculateAOValue(triangleMesh.get(t).getVertex1(), triangleMesh.get(t).getNormal1(), triangleCurrent));
					triangleMesh.get(t).setAO2(calculateAOValue(triangleMesh.get(t).getVertex2(), triangleMesh.get(t).getNormal2(), triangleCurrent));
					triangleMesh.get(t).setAO3(calculateAOValue(triangleMesh.get(t).getVertex3(), triangleMesh.get(t).getNormal3(), triangleCurrent));
				}
				
				
				for (int s=0; s<triangleMesh.size(); s++){
					 System.out.println(s);
					 System.out.println(triangleMesh.get(s).getAO1() + " " + triangleMesh.get(s).getAO2() + " " + triangleMesh.get(s).getAO3() );
				}
				
				
				/*SFInitiator.addInitiable(meshGeometry);
				
				SFObjectModel node=new SFObjectModel();
				
				node.getModel().setRootGeometry(meshGeometry);

					float[][] colours={{0.5f,0.5f,0}};
					SFStructureArray array=CommonMaterial.generateMaterial(colours); 
				
					node.getModel().getTransformComponent().setProgram("BasicPNTransform");
					node.getModel().getMaterialComponent().setProgram("BasicMat");
					node.getModel().getMaterialComponent().addData(new SFStructureReference(array, 0));
					
				SFViewer.generateFrame(node);
				*/
			}
		});
	}

	public void buildTestData() {
		
	}

}
