package shadow.renderer;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.pipeline.SFPipelineTransforms;
import shadow.system.SFInitiable;

public abstract class SFTransformNode  implements SFNode, SFInitiable {

	protected SFPipelineTransform3f transform;

	public SFTransformNode() {
		this.transform=SFPipelineTransforms.generateTrasform();
	}
	
	public SFPipelineTransform3f getTransform() {
		return transform;
	}

}