
function SFPipelineTransforms(){
	this.rigidTransforms=null;
}

var SFPipelineTransforms_pipelineTransforms=new SFPipelineTransforms();

function SFPipelineTransforms_generateTrasform(){
	if(SFPipelineTransforms_pipelineTransforms.rigidTransforms==null){
		SFPipelineTransforms_pipelineTransforms.rigidTransforms=SFPipeline_getSfPipelineMemory().generateRigidTransformsArray();
	}
	var index=SFPipelineTransforms_pipelineTransforms.rigidTransforms.generateElement();
	return new SFPipelineTransform3f(SFPipelineTransforms_pipelineTransforms.rigidTransforms, index);
}

