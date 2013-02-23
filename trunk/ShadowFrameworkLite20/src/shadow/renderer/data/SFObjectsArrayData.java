package shadow.renderer.data;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFReferenceNode;
import shadow.renderer.SFTransformNode;
import shadow.renderer.data.transforms.SFNoTransformData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShort;

public class SFObjectsArrayData extends SFDataAsset<SFNode> {

	protected SFDataAssetObject<SFTransform3f> startTransform=new SFDataAssetObject<SFTransform3f>(new SFNoTransformData());
	protected SFDataAssetObject<SFTransform3f> incrementalTransform=new SFDataAssetObject<SFTransform3f>(new SFNoTransformData());
	protected SFShort arraySize=new SFShort((short)0);
	protected SFLibraryReference<SFTransformNode> objectNode=new SFLibraryReference<SFTransformNode>(null);
	
	public SFObjectsArrayData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("startTransform", startTransform);
		parameters.addObject("incremental", incrementalTransform);
		parameters.addObject("arraySize", arraySize);
		parameters.addObject("objectNode", objectNode);
		setData(parameters);
	}
	
	private SFReferenceNode node;
	
	@Override
	protected SFNode buildResource() {
	
		node=new SFReferenceNode();
		
		objectNode.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTransformNode>>() {
			
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFTransformNode> dataset) {
				
				SFTransformNode modelNode=dataset.getResource();
					
				SFTransform3f incremental=SFObjectsArrayData.this.incrementalTransform.getDataset().buildResource();
				SFTransform3f startTransform=SFObjectsArrayData.this.startTransform.getDataset().buildResource();		
						
				for (int i = 0; i < arraySize.getShortValue(); i++) {
					SFTransformNode modelNodeClone=(SFTransformNode)modelNode.copyNode();
					
					SFMatrix3f matrix=new SFMatrix3f();
					SFVertex3f position=new SFVertex3f();
					
					startTransform.getMatrix(matrix);
					startTransform.getPosition(position);
					
					modelNodeClone.getTransform().setOrientation(matrix);
					modelNodeClone.getTransform().setPosition(position);
					
					node.addNode(modelNodeClone);
					
					startTransform.mult(incremental);
				}
			}
		});

		return node;
	}
}
