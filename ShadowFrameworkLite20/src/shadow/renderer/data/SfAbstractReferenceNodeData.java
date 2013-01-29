package shadow.renderer.data;

import java.util.HashMap;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFNode;
import shadow.renderer.data.transforms.SFNoTransformData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public abstract class SfAbstractReferenceNodeData extends SFDataAsset<SFNode> {

	protected SFLibraryReferenceList<SFNode> nodes = new SFLibraryReferenceList<SFNode>(
				new SFLibraryReference<SFNode>());

	protected abstract SFAbstractReferenceNode generateReferenceNode();

	protected SFLibraryReference<SFTransform3f> transform=new SFLibraryReference<SFTransform3f>(new SFNoTransformData());
	
	public SfAbstractReferenceNodeData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("nodes", nodes);
		setData(parameters);
	}

	@SuppressWarnings("all")
	public void updateReferences() {
		SFLibraryReference[] references=new SFLibraryReference[nodes.size()];
		for (int i = 0; i < references.length; i++) {
			references[i]=nodes.get(i);
		}
		setReferences(references);
	}
	
	public void setTransform(SFDataAsset<SFTransform3f> transform){
		this.transform.setDataset(transform);
	}

	@Override
	protected SFNode buildResource() {
		
		final SFAbstractReferenceNode reference=generateReferenceNode();
		
		SFTransform3f transform=this.transform.getDataset().getResource();
		SFVertex3f vertex=new SFVertex3f();
		transform.getPosition(vertex);
		reference.getTransform().setPosition(vertex);
		SFMatrix3f matrix=new SFMatrix3f();
		transform.getMatrix(matrix);
		reference.getTransform().setOrientation(matrix);
		
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFNode>>() {
				
				private HashMap<String,SFDataAsset<SFNode>> registeredDatasets=new HashMap<String,SFDataAsset<SFNode>>();
				private HashMap<String,SFNode> registeredNodes=new HashMap<String,SFNode>();
				
				@Override
				public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
					SFDataAsset<SFNode> registeredDataset=registeredDatasets.get(name);
					if(registeredDataset!=null){//second time we get name, dataset may be changed.
						SFNode node=registeredNodes.get(name);
						//REMOVE ALL
						reference.removeNode(node);//node removed from renference
						registeredNodes.remove(name);//node removed from registeredNodes
						registeredDatasets.remove(name);//node removed from registeredDatasets
					}
					//create new node from the new dataset,
					SFNode node=dataset.getResource().copyNode();
					reference.addNode(node);
					registeredNodes.put(name, node);
					registeredDatasets.put(name, dataset);
				}
			});
		}
		return reference;
	}

	public void addNode(String nodeName) {
		SFLibraryReference<SFNode> reference=new SFLibraryReference<SFNode>();
		reference.setReference(nodeName);
		this.nodes.add(reference);
		updateReferences();
	}

}