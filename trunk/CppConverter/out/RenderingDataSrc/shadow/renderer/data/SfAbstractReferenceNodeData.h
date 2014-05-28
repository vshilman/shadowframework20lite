#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "java/util/HashMap.h"

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFTransform3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFAbstractReferenceNode.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/renderer/data.transforms.SFNoTransformData.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.SFNamedParametersObject.h"

abstract class SfAbstractReferenceNodeData extends SFDataAsset<SFNode> {

	SFLibraryReferenceList<SFNode> nodes = new SFLibraryReferenceList<SFNode>(
				new SFLibraryReference<SFNode>());

	abstract SFAbstractReferenceNode generateReferenceNode();

	SFLibraryReference<SFTransformResource> transform=new SFLibraryReference<SFTransformResource>(new SFNoTransformData());

	SfAbstractReferenceNodeData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("nodes", nodes);
		setData(parameters);
	}

	void setTransform(SFDataAsset<SFTransformResource> transform){
		this->transform.setDataset(transform);
	}


	HashMap<String,SFNode> registeredDatasets=new HashMap<String,SFNode>();
	HashMap<String,SFNode> registeredNodes=new HashMap<String,SFNode>();

	
	void updateResource(SFNode resource) {

		SFTransform3f transform=this->transform.getDataset().getResource();
		SFVertex3f vertex=new SFVertex3f();
		transform.getPosition(vertex);
		resource.getTransform().setPosition(vertex);
		SFMatrix3f matrix=new SFMatrix3f();
		transform.getMatrix(matrix);
		resource.getTransform().setOrientation(matrix);

		final SFAbstractReferenceNode reference=(SFAbstractReferenceNode)resource;

		//TODO : you should destroy all the transforms and the nodes
		reference.getNodes().clear();

		for (int i = 0; i < nodes.size(); i++) {
			SFNode node=nodes.get(i).getResource().copyNode();
			reference.addNode(node);
		}


	}

	
	SFNode buildResource() {

		final SFAbstractReferenceNode reference=generateReferenceNode();

		SFTransform3f transform=this->transform.getDataset().getResource();
		SFVertex3f vertex=new SFVertex3f();
		transform.getPosition(vertex);
		reference.getTransform().setPosition(vertex);
		SFMatrix3f matrix=new SFMatrix3f();
		transform.getMatrix(matrix);
		reference.getTransform().setOrientation(matrix);

		getDataAssetResource().setResource(1, this->transform.getDataset().getDataAssetResource());

		for (int i = 0; i < nodes.size(); i++) {
//			nodes.get(i).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFNode>>() {
//
//				

//				void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
//					SFDataAsset<?> registeredDataset=registeredDatasets.get(name);
//					if(registeredDataset!=null){//second time we get name, dataset may be changed.
//						SFNode node=registeredNodes.get(name);
//						//REMOVE ALL
//						reference.removeNode(node);//node removed from renference
//						registeredNodes.remove(name);//node removed from registeredNodes
//						registeredDatasets.remove(name);//node removed from registeredDatasets
}
//					//create new node from the new dataset,
//					SFNode node=dataset.getResource().copyNode();
//					reference.addNode(node);
//					registeredNodes.put(name, node);
//					registeredDatasets.put(name, dataset);
}
}
//			String name=nodes.get(i).getReference();
//			SFNode dataset=nodes.get(i).retrieveDataset(null);
//			SFDataAsset<?> registeredDataset=registeredDatasets.get(name);
//			if(registeredDataset!=null){//second time we get name, dataset may be changed.
//				SFNode node=registeredNodes.get(name);
//				//REMOVE ALL
//				reference.removeNode(node);//node removed from renference
//				registeredNodes.remove(name);//node removed from registeredNodes
//				registeredDatasets.remove(name);//node removed from registeredDatasets
}
			//create new node from the new dataset,
			SFNode node=nodes.get(i).getResource().copyNode();

			reference.addNode(node);

			getDataAssetResource().setResource(i+2, nodes.get(i).getDataset().getDataAssetResource());
//			registeredNodes.put(name, node);
//			registeredDatasets.put(name, dataset);
		}


		return reference;
	}

	void addNode(String nodeName) {
		SFLibraryReference<SFNode> reference=new SFLibraryReference<SFNode>();
		reference.setReference(nodeName);
		this->nodes.add(reference);
	}

}
;
}
#endif
