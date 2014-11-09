#include "SFPipeline.h"



namespace sf{

SFPipeline SFPipeline::pipeline;


int SFProgramTrace_compareTo(SFProgramTrace a,SFProgramTrace b) {
    if(a.modulesNames.size()!=b.modulesNames.size())
        return (a.modulesNames.size()-b.modulesNames.size());
    for (unsigned int i = 0; i < a.modulesNames.size(); i++) {
        int compare=a.modulesNames[i].compare(b.modulesNames[i]);
        if(compare!=0)
            return compare;
    }
    
    return 0;
}


SFPipeline::SFPipeline() {
    this->sfPipelineGraphics=0;
    this->sfPipelineMemory=0;
    this->sfProgramBuilder=0;
    this->sfTexturePipeline=0;
    this->sfPipelineNets=0;
}

SFPipeline SFPipeline::getPipeline() {
    return pipeline;
}

void SFPipeline::setSfTexturePipeline(SFTexturePipeline* sfTexturePipeline) {
    pipeline.sfTexturePipeline=sfTexturePipeline;
}

SFProgramBuilder* SFPipeline::getSfProgramBuilder() {
    return pipeline.sfProgramBuilder;
}

void SFPipeline::setSfProgramBuilder(SFProgramBuilder* sfProgramBuilder) {
    pipeline.sfProgramBuilder=sfProgramBuilder;
}

SFTexturePipeline* SFPipeline::getSfTexturePipeline() {
    return pipeline.sfTexturePipeline;
}

SFPipelineMemory* SFPipeline::getSfPipelineMemory() {
    return pipeline.sfPipelineMemory;
}

void SFPipeline::setSfPipelineMemory(SFPipelineMemory* sfPipelineMemory) {
    pipeline.sfPipelineMemory = sfPipelineMemory;
}

SFPipelineGraphics* SFPipeline::getSfPipelineGraphics() {
    return pipeline.sfPipelineGraphics;
}

void SFPipeline::setSfPipelineGraphics(
                                  SFPipelineGraphics* sfPipelineGraphics) {
    pipeline.sfPipelineGraphics=sfPipelineGraphics;
}

SFPipelineNets* SFPipeline::getSfPipelineNets() {
    return pipeline.sfPipelineNets;
}

void SFPipeline::setSfPipelineNets(SFPipelineNets* sfPipelineNets) {
    pipeline.sfPipelineNets=sfPipelineNets;
}

// Pipeline components.
void SFPipeline::loadShaderComponent(string code,
                                SFProgramComponent* component) {
    pipeline.components[code]=component;
}

// Pipeline components.
void SFPipeline::loadPrimitive(string code,
                          SFPrimitive* component) {
    pipeline.primitives[code]=component;
}

// Pipeline components.
void SFPipeline::loadShaderModule(string code,
                             SFProgramModule* component) {
    pipeline.modules[code]=component;
}


SFPipelineElement* SFPipeline::getModule(string structureCode) {
    SFPipelineStructure* structure=pipeline.structures[structureCode];
    if (structure != 0) {
        return structure;
    }
    return pipeline.components[structureCode];
}

SFProgramModule* SFPipeline::getProgramModule(string name){
    SFProgramModule* module=pipeline.modules[name];
    if(module==0){
        SFProgramComponent* component=(SFProgramComponent*)(getModule(name));
        if(component!=0){
            module=new SFProgramModule();
            vector<SFProgramComponent*> components;
            components.clear();
            components.push_back(component);
            module->setComponents(components);
        }
    }
    return module;
}

SFPrimitive* SFPipeline::getPrimitive(string name){
    SFPrimitive* module=pipeline.primitives[name];
    return module;
}

SFPipelineStructure* SFPipeline::getStructure(string structureCode) {
    return pipeline.structures[structureCode];
}

// Pipeline components.
void SFPipeline::loadStructure(string code, SFPipelineStructure* component) {
    //		if(pipeline.structures.containsKey(code))
    //			throw new RuntimeException("Structure "+code+" has already been loaded");
    pipeline.structures[code]=component;
}

/**
 * Generate a unique SFShader given its parameters, or create it if
 * it does not exists.
 *
 * @param tessellator
 * @param primitive
 * @param transform
 * @param material
 * @param light
 * @return
 */
SFProgram* SFPipeline::getStaticProgram(SFPrimitive* primitive, string transform, string material, string light) {
    
    SFProgramTrace* trace=generateTrace(primitive->getName(), transform, material, light);
    
    int index = 0;
    //TO be OPTIMIZED
    //like Collections.binarySearch(pipeline.traces, trace);
    for(unsigned int i=0;i<pipeline.traces.size();i++){
        if(SFProgramTrace_compareTo(*pipeline.traces.at(i),*trace)==0){
            index=i;
            i=pipeline.traces.size();
        }
    }
    
    if(index>=0){
        return pipeline.traces.at(index)->program;
        
    }else{
        SFProgram* program=pipeline.sfProgramBuilder->generateNewProgram();
        
        program->setPrimitive(primitive);
        SFProgramModule* transformP=getProgramModule(transform);
        SFProgramModule* materialP=getProgramModule(material);
        SFProgramModule* lightP=getProgramModule(light);
        program->setTransform(transformP);
        program->setMaterial(materialP);
        program->setLightStep(lightP);
        trace->program=program;
        
        pipeline.traces.push_back(trace);
        //TO BE PTIMIZED
        //Collections.sort(pipeline.traces);
        
        // Now program is returned, and it is ready to be used
        return program;
    }
    
    
    
    
}

SFProgramTrace* SFPipeline::generateTrace(string primitive, string transform, string material,
                                     string light) {
    
    vector<string> modules;
    modules.clear();
    modules.push_back(primitive);
    modules.push_back(transform);
    modules.push_back(material);
    modules.push_back(light);
    
    SFProgramTrace* trace=new SFProgramTrace(modules);
    return trace;
}

SFProgramTrace* SFPipeline::generateTrace(string material,
                                     string light) {
    
    
    vector<string> modules;
    modules.clear();
    modules.push_back(material);
    modules.push_back(light);
    
    SFProgramTrace* trace=new SFProgramTrace(modules);
    return trace;
}

/**
 * Generate a unique SFShader given its parameters, or create it if
 * it does not exists.
 *
 * @param tessellator
 * @param primitive
 * @param transform
 * @param material
 * @param light
 * @return
 */
SFProgram* SFPipeline::getStaticImageProgram(string material, string light) {
    
    SFProgramTrace* trace=generateTrace(material, light);
    
    int index = 0;
    //TO be OPTIMIZED
    //like Collections.binarySearch(pipeline.traces, trace);
    for(unsigned int i=0;i<pipeline.traces.size();i++){
        if(SFProgramTrace_compareTo(*pipeline.traces.at(i),*trace)==0){
            index=i;
            i=pipeline.traces.size();
        }
    }
    
    if(index>=0){
        
        return pipeline.traces.at(index)->program;
        
    }else{
        SFProgram* program=pipeline.sfProgramBuilder->generateImageProgram();
        
        program->setMaterial(getProgramModule(material));
        program->setLightStep(getProgramModule(light));
        
        trace->program=program;
        
        pipeline.traces.push_back(trace);
        //Collections.sort(pipeline.traces);
        
        // Now program is returned, and it is ready to be used
        return program;
    }
}

}
