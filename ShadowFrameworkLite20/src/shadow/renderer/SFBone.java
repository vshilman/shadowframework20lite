package shadow.renderer;

import java.util.ArrayList;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.pipeline.SFPipelineTransforms;
import shadow.system.SFInitiable;

public class SFBone implements SFInitiable, SFTransformKeeper{

	private ArrayList<SFBone> bones=new ArrayList<SFBone>();
	private SFPipelineTransform3f transform;
	
	public SFBone(){
		this.transform=SFPipelineTransforms.generateTrasform();
	}
	
	public SFPipelineTransform3f getTransform() {
		return transform;
	}
	
	public void addNode(SFBone bone) {
		this.bones.add(bone);
		bone.transform.attachOn(this.transform);
	}
	
	@Override
	public void init() {
		//Nothing to do
	}
	
	@Override
	public void destroy() {
		//Nothing to do
	}
	
}
