#ifndef shadow_renderer_data_proxies_H_
#define shadow_renderer_data_proxies_H_

#include "shadow/nodes/SFReferenceNode.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFClonedArrayReference extends SFGraphicsAsset<SFNode>{

	//SFIndexedProxyDataObject mappedProxy=new SFIndexedProxyDataObject();
	SFDataObjectsList<SFString> properties=new SFDataObjectsList<SFString>(new SFString());
	SFDataObjectsList<SFString> constants=new SFDataObjectsList<SFString>(new SFString());
	AssetList<SFGraphicsResource> elements=new SFDataAssetList<SFGraphicsResource>();
	SFLibraryReference<SFNode> node=new SFLibraryReference<SFNode>(null);
	SFIndexedProxyDataCenter proxyDataCenter=new SFIndexedProxyDataCenter();


	SFClonedArrayReference() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("node", node);
		parameters.addObject("properties", properties);
		parameters.addObject("constants", constants);
		parameters.addObject("elements", elements);
		//parameters.addObject("mappedProxy", mappedProxy);
		setData(parameters);
	}

	void setNodeModel(SFDataAsset<SFNode> model){
		this->node.setDataset(model);
	}

	SFLibraryReference<SFNode> getNode() {
		return node;
	}

	void addName(String name){
		properties.add(new SFString(name));
	}

	@SuppressWarnings("unchecked")
	void setData(SFDataAsset<?> dataset)
			throws RuntimeException {
		elements.add((SFDataAsset<SFGraphicsResource>)dataset);
	}

	SFIndexedProxyDataCenter getProxyDataCenter() {
		return proxyDataCenter;
	}

	
	SFNode buildResource() {
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

			SFNode model=this->node.getDataset().getResource();

			node.addNode(model);
		}

		setUpdateMode(false);

		proxyDataCenter.unset();



		return node;
	}

	
	void updateResource(SFNode resource) {
		// TODO Auto-generated method stub

	}

}
;
}
#endif
