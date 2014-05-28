#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFTransform3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/nodes/SFReferenceNode.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/renderer/data.transforms.SFNoTransformData.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFObjectsArrayData extends SFDataAsset<SFNode> {

	SFDataAssetObject<SFTransformResource> startTransform=new SFDataAssetObject<SFTransformResource>(new SFNoTransformData());
	SFDataAssetObject<SFTransformResource> incrementalTransform=new SFDataAssetObject<SFTransformResource>(new SFNoTransformData());
	SFShort arraySize=new SFShort((short)0);
	SFLibraryReference<SFNode> objectNode=new SFLibraryReference<SFNode>(null);

	SFObjectsArrayData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startTransform", startTransform);
		parameters.addObject("incremental", incrementalTransform);
		parameters.addObject("arraySize", arraySize);
		parameters.addObject("objectNode", objectNode);
		setData(parameters);
	}

	SFReferenceNode node;

	
	SFNode buildResource() {

		node=new SFReferenceNode();

		SFNode modelNode=objectNode.getResource();

		SFTransform3f incremental=SFObjectsArrayData.this->incrementalTransform.getDataset().getResource();
		SFTransform3f startTransform=SFObjectsArrayData.this->startTransform.getDataset().getResource();		

		for (int i = 0; i < arraySize.getShortValue(); i++) {
			SFNode modelNodeClone=(SFNode)modelNode.copyNode();

			SFMatrix3f matrix=new SFMatrix3f();
			SFVertex3f position=new SFVertex3f();

			startTransform.getMatrix(matrix);
			startTransform.getPosition(position);

			modelNodeClone.getTransform().setOrientation(matrix);
			modelNodeClone.getTransform().setPosition(position);

			node.addNode(modelNodeClone);

			startTransform.mult(incremental);
		}

		return node;
	}

	
	void updateResource(SFNode resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
