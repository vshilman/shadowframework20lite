package shadow.renderer.viewer.tests;

import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;


public class SFSceneTest0005 {

	
	public static void main(String[] args) {

		SFViewer.prepare();
		CommonPipeline.prepare();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		
		// 3) Retrieve 2 library and mixing them!
		SFViewerObjectsLibrary library=new SFViewerObjectsLibrary(SFGenerateLibrary.root,"library0602.sf");
		copyAssets("test0258", library.getLibrary());
		
		SFDataCenter.setDataCenterImplementation(library);
		
		
		//Create scenegraph root
		SFReferenceNode rootSceneNode=new SFReferenceNode();

			rootSceneNode.addNode(placeModel("SLAvatarCenterShop01", 0.0f, 0.0f, -0.0f, 1,0,0,0));
			rootSceneNode.addNode(placeModel("SLAvatarCenterShop02", -1.2f, -0.6f, -0.0f, 1,0,-1.07f,0));
			rootSceneNode.addNode(placeModel("SLAvatarCenterShop02", 0.8f, -0.6f, -0.6f, 1,0,+1.07f,0));
			rootSceneNode.addNode(placeModel("PlainChair", 0.0f, -0.5f, -0.6f, 0.2f,0,-1.07f,0));
			rootSceneNode.addNode(placeModel("PlainChair", 0.2f, -0.5f, -0.5f, 0.2f,0,+1.07f,0));
			rootSceneNode.addNode(placeModel("PlainChair", 0.0f, -0.5f, -0.4f, 0.2f,0,-1.07f,0));
			rootSceneNode.addNode(placeModel("PlainChair", -0.2f, -0.5f, -0.8f, 0.2f,0,+1.07f,0));
				
		rootSceneNode.getTransform().setOrientation(SFMatrix3f.getAmpl(0.6f, 0.6f, 0.6f));
			
		//Create scene root
		SFViewer.generateFrame(rootSceneNode,
				SFViewer.getLightStepController(),
				SFViewer.getRotationController(),
				SFViewer.getZoomController(),
				SFViewer.getWireframeController());
		
		//Camera setup (you can also create a Camera from scratch, by assigning it to the viewer with :
					//SFCamera createdCamera=new SFCamera(...);	
					//viewer.getRenderer().setCamera(createdCamera);
					
	}
	
	@SuppressWarnings("unchecked")
	public static SFReferenceNode placeModel(String modelName,float x,float y,float z){
		
		SFDataset nodeDataset=SFDataCenter.getDataCenter().getAlreadyAvailableDataset(modelName);
		SFNode node=((SFDataAsset<SFNode>)(nodeDataset)).getResource();
		
		SFReferenceNode referenceNode=new SFReferenceNode();
		referenceNode.getTransform().setPosition(new SFVertex3f(x,y,z));
		referenceNode.addNode(node.copyNode());
		
		return referenceNode;
	}
	
	public static SFNode placeModel(String modelName,float x,float y,float z,float rx,float ry,float rz){
		
		SFReferenceNode referenceNode=placeModel(modelName, x, y, z);
		SFEulerAngles3f angles=new SFEulerAngles3f(ry,rx,rz);
		SFMatrix3f temp=new SFMatrix3f();
		angles.getMatrix(temp);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}
	
	public static SFNode placeModel(String modelName,float x,float y,float z,float scale){
		
		SFReferenceNode referenceNode=placeModel(modelName, x, y, z);
		SFMatrix3f temp=SFMatrix3f.getAmpl(scale, scale, scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}
	
	public static SFNode placeModel(String modelName,float x,float y,float z,float scale,float rx,float ry,float rz){
		
		SFReferenceNode referenceNode=placeModel(modelName, x, y, z);
		SFEulerAngles3f angles=new SFEulerAngles3f(ry,rx,rz);
		SFMatrix3f temp=new SFMatrix3f();
		angles.getMatrix(temp);
		temp.mult(scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}
	
	/**
	 * Copy an array of assets from one library to another.
	 * 
	 * @param fromLibraryFileName The name of the library from which you want to load your Assets. Don't put '.sf' in file filename.
	 * @param toLibrary The {@link SFObjectsLibrary} (usually, it will be SFAbstractTest.library, the default {@link SFObjectsLibrary} of each test)
	 * @param assetsName the name of the asset in the old library.
	 */
	public static void copyAssets(String fromLibraryFileName,SFObjectsLibrary toLibrary){
		
		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(SFGenerateLibrary.root, fromLibraryFileName+".sf");
		
		for (RecordData recordData : oldLibrary.getLibrary()) {
			System.err.println(recordData.getName());
			toLibrary.put(recordData.getName(), oldLibrary.getLibrary().retrieveDataset(recordData.getName()));
		}
		
	}
}
