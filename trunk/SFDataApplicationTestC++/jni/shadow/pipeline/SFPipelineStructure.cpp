
#include "SFPipelineStructure.h"


namespace sf{

string SFPipelineStructure::getName() {
    return name;
}

void SFPipelineStructure::setName(string name) {
    this->name = name;
}

int SFPipelineStructure::size(){
    return parametersLength;
}

//vector<SFParameter*> SFPipelineStructure::getAllParameters(){
//    return parameters;
//}

//void SFPipelineStructure::addParameter(SFParameter* param){
//    parameters.push_back(param);
//}

//void SFPipelineStructure::addParameters(vector<SFParameter*> parameters){
//    this->parameters.insert(this->parameters.begin(),parameters.begin(),parameters.end());
//}
    
SFParameter* SFPipelineStructure::getAllParameters(){
    return this->parameters;
}
	
void SFPipelineStructure::setParameters(SFParameter* parameters,int parametersLength){
    this->parametersLength=parametersLength;
    this->parameters=parameters;
}
    

int SFPipelineStructure::floatSize(){
    int floatSize=0;
    for (unsigned int i=0; i < parametersLength; i++) {
        switch (parameters[i].getType()) {
            case SFParameteri::GLOBAL_FLOAT:
                floatSize++;break;
            case SFParameteri::GLOBAL_FLOAT2:
                floatSize+=2;break;
            case SFParameteri::GLOBAL_FLOAT3:
                floatSize+=3;break;
            case SFParameteri::GLOBAL_FLOAT4:
                floatSize+=4;break;
            case SFParameteri::GLOBAL_TEXTURE:
                floatSize++;break;
        }
    }
    return floatSize;
}

}
