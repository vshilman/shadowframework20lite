package shadow.renderer;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.pipeline.SFPipelineTransforms;
import shadow.system.SFInitiable;

public abstract class SFTransformNode  implements SFNode, SFInitiable, SFTransformKeeper {

	protected SFPipelineTransform3f transform;

	public SFTransformNode() {
		this.transform=SFPipelineTransforms.generateTrasform();
	}
	
	/* (non-Javadoc)
	 * @see shadow.renderer.SFTransformKeeper#getTransform()
	 */
	@Override
	public SFPipelineTransform3f getTransform() {
		return transform;
	}

}