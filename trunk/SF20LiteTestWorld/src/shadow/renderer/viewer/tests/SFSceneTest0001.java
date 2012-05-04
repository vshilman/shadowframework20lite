package shadow.renderer.viewer.tests;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;


public class SFSceneTest0001 {

	
	public static void main(String[] args) {

		SFViewer.prepare();
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(
				new SFViewerObjectsLibrary(SFGenerateLibrary.root,SFGenerateLibrary.filename));
		
		//Create scenegraph root
		SFReferenceNode rootSceneNode=new SFReferenceNode();
		
			//add a 'Tube' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Tube"));
			//add a 'Mushroom' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Mushroom"));
			//add a 'Mushroom' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Mushroom"));
			//add a 'Mushroom' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Mushroom"));
			//add a 'Glass' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Glass"));
			//add a 'Glass' at random position
			rootSceneNode.addNode(generateModelaAtRandomPosition("Glass"));
			//add a lot of 'Tubes'
				for(int i=0;i<20;i++)
					rootSceneNode.addNode(generateModelaAtRandomPosition("Tube"));
				
		//Create scene root
		SFViewer viewer=SFViewer.generateFrame(rootSceneNode,
				SFViewer.getLightStepController(),
				SFViewer.getRotationController(),
				SFViewer.getZoomController(),
				SFViewer.getWireframeController());
		
		//Camera setup (you can also create a Camera from scratch, by assigning it to the viewer with :
					//SFCamera createdCamera=new SFCamera(...);	
					//viewer.getRenderer().setCamera(createdCamera);
					
		//setup camera Focus F
		viewer.getCamera().setF(new SFVertex3f(0,2.7*0.5,-2.7f*0.86));
		//setup Camera's Dir, that's the Direction to which camera is oriented or camera z-axis
		viewer.getCamera().setDir(new SFVertex3f(0,-0.5,0.86));
		//setup Camera's Left, that's the camera x-axis
		viewer.getCamera().setLeft(new SFVertex3f(1,0,0));
		//setup Camera's Up, that's the camera y-axis
		viewer.getCamera().setUp(new SFVertex3f(0,0.86,0.5));
		//setup camera to be perspective
		viewer.getCamera().setPerspective(true);
		//update camera 
		viewer.getCamera().update();
		
		
		//NOTE: to verify camera content call viewer.getCamera().extractTransform();
	}
	
	
	public static SFNode generateModelaAtRandomPosition(String modelName){
		
		//A new scenegraph node
		SFObjectModel node=new SFObjectModel();

		//random index int [0,2]
		int materialsNumber=CommonMaterial.generateColoursNames().length;
		int index=(int)(materialsNumber*Math.random());
		if(index==materialsNumber)
			index=0;
		//Extract the index-th material from file libraries/library
		SFStructureArray array=((SFStructureArrayData)SFDataCenter.getDataCenter().getAlreadyAvailableDataset("Materials")).getArray();
		SFStructureReference materialReference=new SFStructureReference(array, index);//generateStructureDataReference( "Materials", materialData);

		//Set the selected material to the node
		node.getModel().addMaterial(new SFProgramStructureReference("BasicMat",materialReference));
		
			//Add to this node a reference to the model 'modelName'; modelNames are the ones
			//available in 'libraries/library', actually containing only 'Tube', 'Mushroom' and 'Glass'
			SFGeometry geometry=((SFQuadsSurfaceGeometryData)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(modelName)).getResource();
			node.getModel().setRootGeometry(geometry);
		
		//setup Node position 	
		node.getTransform().setPosition(new SFVertex3f((float)((4*Math.random())-2),0,(float)((2.4*Math.random())-1.2f)));
		
		return node;
	}
}
