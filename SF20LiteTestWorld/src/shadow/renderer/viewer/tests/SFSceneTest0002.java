package shadow.renderer.viewer.tests;

import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFViewer;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;


public class SFSceneTest0002 {

	
	public static void main(String[] args) {

		SFViewer.prepare();
		CommonPipeline.prepare();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(
				new SFViewerObjectsLibrary(SFGenerateLibrary.root,"library0602.sf"));
		
		//Create scenegraph root
		SFAbstractReferenceNode rootSceneNode=new SFReferenceNode();
		
			rootSceneNode.addNode(placeModel("Dummy01", 0.6f, -0.5f, -0.8f, 0.8f,0,0,0));
			rootSceneNode.addNode(placeModel("Dummy01", 0.8f, -0.5f, 0, 0.8f,0,1.57f,0));
			rootSceneNode.addNode(placeModel("Dummy01", 0.3f, -0.5f, 0.4f, 0.8f,0,1.57f,0));
			rootSceneNode.addNode(placeModel("Dummy01", 1.2f, -0.5f, 0, 0.8f,0,1.57f,0));
			rootSceneNode.addNode(placeModel("Dummy01", 0.6f, -0.5f, -0.8f, 0.8f,0,1.57f,0));
			rootSceneNode.addNode(placeModel("Dummy01", -0.6f, -0.5f, 0, 0.8f,0,1.57f,0));
			rootSceneNode.addNode(placeModel("Dummy01", 0.8f, -0.5f, 0.8f, 0.8f,0,1.57f,0));
				
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
	
	
}
