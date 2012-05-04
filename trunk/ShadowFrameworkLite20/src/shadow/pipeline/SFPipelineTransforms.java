package shadow.pipeline;

public class SFPipelineTransforms {

	private static SFPipelineTransforms pipelineTransforms=new SFPipelineTransforms();
	
	private SFRigidTransform3fArray rigidTransforms;
	
	private SFPipelineTransforms(){
		
	}

	public static SFPipelineTransform3f generateTrasform(){
		if(pipelineTransforms.rigidTransforms==null){
			pipelineTransforms.rigidTransforms=SFPipeline.getSfPipelineMemory().generateRigidTransformsArray();
		}
		int index=pipelineTransforms.rigidTransforms.generateElement();
		return new SFPipelineTransform3f(pipelineTransforms.rigidTransforms, index);
	}
}
