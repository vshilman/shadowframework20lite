#ifndef SFPIPELINE_H
#define SFPIPELINE_H

#include <vector>
#include <string>
#include <map>

#include "SFProgram.h"
#include "SFPrimitive.h"
#include "SFPipelineStructure.h"
#include "SFProgramModule.h"
#include "SFProgramBuilder.h"
#include "SFTexturePipeline.h"
#include "SFPipelineMemory.h"
#include "SFPipelineGraphics.h"
#include "SFTexturePipeline.h"
#include "SFPipelineNets.h"

using namespace std;


namespace sf{

class SFProgramTrace{

public:
    vector<string> modulesNames;
    SFProgram* program;

    SFProgramTrace(vector<string> modulesNames) {
        this->modulesNames = modulesNames;
        this->program=0;
    }

};

int SFProgramTrace_compareTo(SFProgramTrace a,SFProgramTrace b);


class SFPipeline {

private:

    map<string,SFProgramComponent*> components;
    map<string,SFProgramModule*> modules;
    map<string,SFPipelineStructure*> structures;
    map<string,SFPrimitive*> primitives;

    vector<SFProgramTrace*> traces;

    SFProgramBuilder* sfProgramBuilder;
    SFPipelineGraphics* sfPipelineGraphics;
    SFPipelineMemory* sfPipelineMemory;
    SFTexturePipeline* sfTexturePipeline;
    SFPipelineNets* sfPipelineNets;

    SFPipeline();

    static SFPipeline pipeline;

public:
    
    static void freePipeline(){
        delete pipeline.sfProgramBuilder;
        delete pipeline.sfPipelineGraphics;
        delete pipeline.sfPipelineMemory;
        delete pipeline.sfTexturePipeline;
        for(unsigned int i=0;i<pipeline.traces.size();i++){
            delete pipeline.traces[i];
        }
        //TODO : free components, moules,, structures, primitives
        
        //TODO : free SFPipelineRegister registers; free SFPrimitiveBlock blocks&values
    }

    static SFPipeline getPipeline();

    static void setSfTexturePipeline(SFTexturePipeline* sfTexturePipeline);

    static SFProgramBuilder* getSfProgramBuilder();

    static void setSfProgramBuilder(SFProgramBuilder* sfProgramBuilder);

    static SFTexturePipeline* getSfTexturePipeline();

    static SFPipelineMemory* getSfPipelineMemory();

    static void setSfPipelineMemory(SFPipelineMemory* sfPipelineMemory);

    static SFPipelineGraphics* getSfPipelineGraphics();

    static void setSfPipelineNets(SFPipelineNets* sfPipelineNets);

    static SFPipelineNets* getSfPipelineNets();

    static void setSfPipelineGraphics(
                                      SFPipelineGraphics* sfPipelineGraphics);

    // Pipeline components.
    static void loadShaderComponent(string code,
                                    SFProgramComponent* component);

    // Pipeline components.
    static void loadPrimitive(string code,
                              SFPrimitive* component);

    // Pipeline components.
    static void loadShaderModule(string code,
                                 SFProgramModule* component);


    static SFPipelineElement* getModule(string structureCode);

    static SFProgramModule* getProgramModule(string name);

    static SFPrimitive* getPrimitive(string name);

    static SFPipelineStructure* getStructure(string structureCode);

    // Pipeline components.
    static void loadStructure(string code, SFPipelineStructure* component);

    /**
     * Generate a unique Static SFShader given its parameters, or create it if
     * it does not exists.
     *
     * @param tessellator
     * @param primitive
     * @param transform
     * @param material
     * @param light
     * @return
     */
    static SFProgram* getStaticProgram(SFPrimitive* primitive, string transform, string material, string light) ;

    static SFProgramTrace* generateTrace(string primitive, string transform, string material,
                                         string light) ;

    static SFProgramTrace* generateTrace(string material,
                                         string light) ;

    /**
     * Generate a unique Static SFShader given its parameters, or create it if
     * it does not exists.
     *
     * @param tessellator
     * @param primitive
     * @param transform
     * @param material
     * @param light
     * @return
     */
    static SFProgram* getStaticImageProgram(string material, string light);
};

}

#endif // SFPIPELINE_H
