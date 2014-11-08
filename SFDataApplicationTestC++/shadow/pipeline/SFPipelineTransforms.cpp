
#include "SFPipelineTransforms.h"
#include "SFPipeline.h"


namespace sf{

SFPipelineTransforms SFPipelineTransforms::pipelineTransforms;

SFPipelineTransform3f* SFPipelineTransforms::generateTrasform(){
    if(pipelineTransforms.rigidTransforms==0){
        pipelineTransforms.rigidTransforms=SFPipeline::getSfPipelineMemory()->generateRigidTransformsArray();
    }
    int index=pipelineTransforms.rigidTransforms->generateElement();
    return new SFPipelineTransform3f(pipelineTransforms.rigidTransforms, index);
}

}
