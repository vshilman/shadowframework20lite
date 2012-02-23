package shadow.renderer.tests;

import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.data.SFStructureReference;
import shadow.renderer.tests.utils.SFViewer;


public class SFSceneTest0001 {

	
	public static void main(String[] args) {
		
		SFViewer.prepare();
		
		//Create scenegraph root
		SFNode rootSceneNode=new SFNode();
		
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
		SFViewer viewer=SFViewer.generateFrame(rootSceneNode);
		
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
		SFNode node=new SFNode();

		//random index int [0,2]
		int index=(int)(3*Math.random());
		if(index==3)
			index=0;
		//Extract the index-th material from file libraries/library
		SFStructureReference materialReference=new SFStructureReference("Materials", index);//generateStructureDataReference( "Materials", materialData);

		//Set the selected material to the node
		node.addMaterial(new SFProgramStructureReference("BasicMat",materialReference));
		
			//Add to this node a reference to the model 'modelName'; modelNames are the ones
			//available in 'libraries/library', actually containing only 'Tube', 'Mushroom' and 'Glass'
			node.setGeometryReference(modelName);
		
		//setup Node position 	
		node.getPosition().set3f((float)((4*Math.random())-2),0,(float)((2.4*Math.random())-1.2f));
		
		return node;
	}
}
