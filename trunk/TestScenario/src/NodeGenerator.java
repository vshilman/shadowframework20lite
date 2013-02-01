
import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;

public class NodeGenerator {

	private int id;
	SFNode geometryNode;
	
	public NodeGenerator(int id, SFVertex3f position, int color) {
		
		this.id = id;
		
	   SFViewerObjectsLibrary library = new SFViewerObjectsLibrary("libraries", "test0004.sf");
		SFDataCenter.setDataCenterImplementation(library);
		this.geometryNode = placeModel("PerlinMushroom", position.getX(), position.getY(), position.getZ(),1, 0, 0, 0);
		
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

	
	

	public static SFReferenceNode placeModel(String modelName, float x,
			float y, float z) {

		SFDataset nodeDataset = SFDataCenter.getDataCenter()
				.getAlreadyAvailableDataset(modelName);
		SFNode node = ((SFDataAsset<SFNode>) (nodeDataset)).getResource();

		SFReferenceNode referenceNode = new SFReferenceNode();
		referenceNode.getTransform().setPosition(new SFVertex3f(x, y, z));
		referenceNode.addNode(node.copyNode());

		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float rx, float ry, float rz) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFEulerAngles3f angles = new SFEulerAngles3f(ry, rx, rz);
		SFMatrix3f temp = new SFMatrix3f();
		angles.getMatrix(temp);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float scale) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFMatrix3f temp = SFMatrix3f.getAmpl(scale, scale, scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	public static SFNode placeModel(String modelName, float x, float y,
			float z, float scale, float rx, float ry, float rz) {

		SFReferenceNode referenceNode = placeModel(modelName, x, y, z);
		SFEulerAngles3f angles = new SFEulerAngles3f(ry, rx, rz);
		SFMatrix3f temp = new SFMatrix3f();
		angles.getMatrix(temp);
		temp.mult(scale);
		referenceNode.getTransform().setOrientation(temp);
		return referenceNode;
	}

	
}
