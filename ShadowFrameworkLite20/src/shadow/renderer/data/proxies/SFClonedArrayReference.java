package shadow.renderer.data.proxies;

import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetList;
import shadow.renderer.data.SFDataObjectsList;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.SFInitiable;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFClonedArrayReference extends SFDataAsset<SFNode>{

	//private SFIndexedProxyDataObject mappedProxy=new SFIndexedProxyDataObject();
	private SFDataObjectsList<SFString> properties=new SFDataObjectsList<SFString>(new SFString());
	private SFDataObjectsList<SFString> constants=new SFDataObjectsList<SFString>(new SFString());
	private SFDataAssetList<SFInitiable> elements=new SFDataAssetList<SFInitiable>();
	private SFLibraryReference<SFNode> node=new SFLibraryReference<SFNode>(null);
	private SFIndexedProxyDataCenter proxyDataCenter=new SFIndexedProxyDataCenter();
	
	
	public SFClonedArrayReference() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("node", node);
		parameters.addObject("properties", properties);
		parameters.addObject("constants", constants);
		parameters.addObject("elements", elements);
		//parameters.addObject("mappedProxy", mappedProxy);
		setData(parameters);
	}
	
	public void setNodeModel(SFDataAsset<SFNode> model){
		this.node.setDataset(model);
	}

	public SFLibraryReference<SFNode> getNode() {
		return node;
	}
	
	public void addName(String name){
		properties.add(new SFString(name));
	}
	
	@SuppressWarnings("unchecked")
	public void setData(SFDataAsset<?> dataset)
			throws RuntimeException {
		elements.add((SFDataAsset<SFInitiable>)dataset);
	}
	
	public SFIndexedProxyDataCenter getProxyDataCenter() {
		return proxyDataCenter;
	}

	@Override
	protected SFNode buildResource() {
		SFAbstractReferenceNode node=new SFReferenceNode();
		
		int index=0;
		for (int i = 0; i < elements.size(); ) {
			for (int j = 0; j < properties.size(); j++,i++) {
				proxyDataCenter.setData(properties.get(j).getString(), index, elements.get(i));
			}
			index++;
		}
		

		for (int i = 0; i < constants.size(); i++) {
			proxyDataCenter.addConstant(constants.get(i).getString());
		}
		
		proxyDataCenter.setup();

		setUpdateMode(true);
		
		for (int i = 0; i < proxyDataCenter.size(); i++) {
			
			proxyDataCenter.setIndex(i);
			
			SFNode model=this.node.getDataset().getResource();
			
			node.addNode(model);
		}
		
		setUpdateMode(false);

		proxyDataCenter.unset();
		
		return node;
	}
	
}

