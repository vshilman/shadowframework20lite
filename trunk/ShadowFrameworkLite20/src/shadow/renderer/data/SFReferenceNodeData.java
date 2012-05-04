package shadow.renderer.data;

import shadow.math.SFEulerAngles3f;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFBinaryObject;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFVertex3fData;
import shadow.system.data.objects.binaries.SFBinaryVector;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFReferenceNodeData extends SFDataAsset<SFNode> implements SFDataCenterListener<SFDataAsset<SFNode>>{

	private SFDataList<SFLibraryReference<SFDataAsset<SFNode>>> nodes=new SFDataList<SFLibraryReference<SFDataAsset<SFNode>>>(
			new SFLibraryReference<SFDataAsset<SFNode>>());
	private SFVertex3fData position=new SFVertex3fData();
	private SFBinaryObject<SFBinaryVector<SFEulerAngles3f>> orientation=
			new SFBinaryObject<SFBinaryVector<SFEulerAngles3f>>(
					new SFBinaryVector<SFEulerAngles3f>(-(float)(Math.PI),(float)(Math.PI),10,10,10));
	
	private SFReferenceNode reference=null;
	
	public SFReferenceNodeData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(nodes,position,orientation));
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFReferenceNodeData();
	}
	
	
	public void setPosition(SFVertex3f vertex){
		position.getVertex3f().set(vertex);
	}
	
	public void getPosition(SFVertex3f vertex){
		vertex.set(position.getVertex3f());
	}
	
	public void setOrientation(SFMatrix3f matrix){
		orientation.getBinaryValue().setValue(new SFEulerAngles3f(matrix));
	}
	
	public void getOrientation(SFMatrix3f matrix){
		SFEulerAngles3f angles=new SFEulerAngles3f();
		orientation.getBinaryValue().getValue(angles);
		angles.getMatrix(matrix);
	}

	@Override
	public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
		SFNode node=dataset.getResource();
		reference.addNode(node.copyNode());
	}
	
	@Override
	protected SFNode buildResource() {
		
		reference=new SFReferenceNode();
		SFMatrix3f matrix=new SFMatrix3f();
		getOrientation(matrix);
		reference.getTransform().setOrientation(matrix);
		
		reference.getTransform().setPosition(position.getVertex3f());
		
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).retrieveDataset(this);
		}
		return reference;
	}
	
	public void addNode(String nodeName){
		SFLibraryReference<SFDataAsset<SFNode>> reference=new SFLibraryReference<SFDataAsset<SFNode>>();
		reference.setReference(nodeName);
		this.nodes.add(reference);
	}
}
