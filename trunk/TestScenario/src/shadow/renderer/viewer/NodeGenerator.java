package shadow.renderer.viewer;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFStructureArrayData;
import shadow.system.data.SFDataCenter;

public class NodeGenerator {

	private int id;
	private SFVertex3f position;
	SFNode geometryNode;

	
	public NodeGenerator(int id, SFVertex3f position, int color) {
		
		this.id = id;
		this.geometryNode = generateModelAtPosition("Mushroom", position, color);
		
		
	}
	

	public static SFNode generateModelAtPosition(String modelName, SFVertex3f position, int color) {

		SFObjectModel node;
		node =  new SFObjectModel();
	//	int materialsNumber = CommonMaterial.generateColoursNames().length;
		//int index = (int) (materialsNumber * Math.random());
		//if (index == materialsNumber)
			int index = color;
		// Extract the index-th material from file libraries/library
		SFStructureArray array = ((SFStructureArrayData) SFDataCenter
				.getDataCenter().getAlreadyAvailableDataset("Materials"))
				.getArray();
		SFStructureReference materialReference = new SFStructureReference(
				array, index);// generateStructureDataReference( "Materials",
								// materialData);

		// Set the selected material to the node
		node.getModel().addMaterial(
				new SFProgramStructureReference("BasicMat", materialReference));

		// Add to this node a reference to the model 'modelName'; modelNames are
		// the ones
		// available in 'libraries/library', actually containing only 'Tube',
		// 'Mushroom' and 'Glass'
		SFGeometry geometry = ((SFQuadsSurfaceGeometryData) SFDataCenter
				.getDataCenter().getAlreadyAvailableDataset(modelName))
				.getResource();
		
	
		node.getModel().setRootGeometry(geometry);

		// setup Node position
		node.getTransform().setPosition(position);

		return node;
	}
	
	
	void setPosition(SFVertex3f position){
		
		geometryNode.getTransform().setPosition(position);
		
		}
	
	
	void setId(int id){
		
		
		this.id = id;
		
	}
	
	int getId(){
		
		return id;
	}
	
	public SFNode getGeometryNode(){
		
		
		return geometryNode;
	}
	
}
