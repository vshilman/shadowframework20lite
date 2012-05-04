package shadow.renderer.data.proxies;

import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFClonedArrayReference extends SFDataAsset<SFNode>{

	private SFIndexedProxyDataObject mappedProxy=new SFIndexedProxyDataObject();
	private SFDatasetObject<SFDataAsset<SFNode>> node=new SFDatasetObject<SFDataAsset<SFNode>>(null);
	
	public SFClonedArrayReference() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(node,mappedProxy));
	}
	
	public void setNodeModel(SFDataAsset<SFNode> model){
		this.node.setDataset(model);
	}
	
	public SFIndexedProxyDataObject getMappedProxy() {
		return mappedProxy;
	}

	public SFDatasetObject<SFDataAsset<SFNode>> getNode() {
		return node;
	}
	
	public void setData(String name, int index, SFDataset dataset)
			throws RuntimeException {
		getMappedProxy().getProxyDataCenter().setData(name, index, dataset);
	}

	@Override
	protected SFNode buildResource() {
		SFReferenceNode node=new SFReferenceNode();
		
		mappedProxy.getProxyDataCenter().setup();
		
		setUpdateMode(true);
		
		for (int i = 0; i < mappedProxy.getProxyDataCenter().size(); i++) {
			mappedProxy.getProxyDataCenter().setIndex(i);
			
			SFNode model=this.node.getDataset().getResource();
			
			node.addNode(model);
		}
		
		setUpdateMode(false);

		mappedProxy.getProxyDataCenter().unset();
		
		return node;
	}
	
}

