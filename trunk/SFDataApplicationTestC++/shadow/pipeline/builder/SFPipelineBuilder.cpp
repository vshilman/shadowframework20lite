
#include "SFPipelineBuilder.h"


namespace sf{
map<string, SFBuilderElement*> SFPipelineBuilder::typeMap;

SFBuilderElement* SFPipelineBuilder::getElement(string type) {

	if(typeMap.size()==0){

		//typeMap["Tessellator", SFParsableProgramComponent.class);
		typeMap["PrimitiveComponent"]= new SFParsableProgramComponent();
		typeMap["TransformsComponent"]= new SFParsableProgramComponent();
		typeMap["MaterialComponent"]= new SFParsableProgramComponent();
		typeMap["LightStepComponent"]= new SFParsableProgramComponent();
		//typeMap["Grid", SFBuilderGrid.class);
		typeMap["Structure"]= new SFBuilderStructure();

		typeMap["Primitive"]= new SFParsablePrimitive();
		typeMap["Transform"]= new SFParsableProgramModule();
		typeMap["Material"]= new SFParsableProgramModule();
		typeMap["LightStep"]= new SFParsableProgramModule();
	}
	return (typeMap[type]->newInstance());
}

void SFPipelineBuilder::generateElement(string type, string name) {
	SFBuilderElement* temp=SFPipelineBuilder::getElement(type);
	((SFPipelineElement*)temp)->setName(name);
	this->component=(SFPipelineElement*)temp;
}

void SFPipelineBuilder::buildDefineRule( string pWrote,short type, SFExpressionElement* function) {
	SFParameter* param=new SFParameter(pWrote,type);
	SFProgramComponent* cmp=(SFProgramComponent*)getComponent();
	cmp->addParameter(param);

	SFFunction* functionCode = new SFFunction(param,function);
	cmp->addCodeFunction(functionCode);
}

SFPipelineElement* SFPipelineBuilder::getComponent() {
	return component;
}

void SFPipelineBuilder::setComponent(SFPipelineElement* component) {
	this->component = component;
}

void SFPipelineBuilder::setUseRule(string use){
	SFPipelineRegister* global=SFPipelineRegister::getFromName(use);
	SFProgramComponent* cmp=(SFProgramComponent*)getComponent();
	cmp->addRegister(global);
}

void SFPipelineBuilder::buildWriteRule(string wrote, SFExpressionElement* function) {
	SFPipelineRegister* global=SFPipelineRegister::getFromName(wrote);
	SFProgramComponent* cmp=(SFProgramComponent*)getComponent();
	((SFProgramComponent*)getComponent())->addRegister(global);

	SFFunction* functionCode = new SFFunction(global,function);
	cmp->addCodeFunction(functionCode);
}


void SFPipelineBuilder::buildParamRule(short parameter, string use) {
	SFParameter* param=new SFParameter(use, parameter);
	SFBuilderStructure* cmp=(SFBuilderStructure*)getComponent();
	cmp->addParameter(param);
}



void SFPipelineBuilder::buildParamRule(string moduleString, vector<string> pars) {
	//vector<SFParameter*> params_;//[pars.length];

	SFPipelineElement* module = SFPipeline::getModule(moduleString);
	SFProgramComponent* cmp = (SFProgramComponent*) getComponent();

	SFPipelineStructure* sfPs = (SFPipelineStructure*) module;

	int size=sfPs->getAllParameters().size();
	SFParameter* params_=new SFParameter[size];
	
    SFParameter* parameters = sfPs->getAllParameters();

	for(int i=0;i<size;i++){
		params_[i].setType(parameters[i].getType());
		params_[i].setName(pars[i]);//=SFParameter(pars[i],sfParameteri->getType());
	}

	SFPipelineStructureInstance* instance = new SFPipelineStructureInstance((SFPipelineStructure*) module, params_,size);
	cmp->addStructureInstance(instance);
}


void SFPipelineBuilder::buildComponent(string componentName) {
	SFParsableProgramModule* module=(SFParsableProgramModule*)getComponent();
	module->addComponent((SFProgramComponent*)SFPipeline::getModule(componentName));
}


void SFPipelineBuilder::buildBlock(string block, string primitiveComponent) {
	SFParsablePrimitive* module=(SFParsablePrimitive*)getComponent();
	SFPrimitiveBlock* prBlock=SFPrimitiveBlock::valueOf(block);
	module->addComponent(prBlock,(SFProgramComponent*)SFPipeline::getModule(primitiveComponent));
}


void SFPipelineBuilder::buildDomain(string domain) {
	SFParsablePrimitive* module=(SFParsablePrimitive*)getComponent();
	SFGridModel model=SFGridModel::valueOf(domain);
	module->setGridModel(model);
}

void SFPipelineBuilder::buildGrid(vector<string> pars, string model,string type, int n) {
	if(types.size()==0){
		types["<>"]= SFParameter::GLOBAL_GENERIC;
		types["float"]= SFParameter::GLOBAL_FLOAT;
		types["float2"]= SFParameter::GLOBAL_FLOAT2;
		types["float3"]= SFParameter::GLOBAL_FLOAT3;
		types["float4"]= SFParameter::GLOBAL_FLOAT4;
	}

	int size=pars.size();
	SFParameter* params_=new SFParameter[size];

	SFProgramComponent* cmp = (SFProgramComponent*) getComponent();

	for(unsigned int i=0;i<pars.size();i++){
		params_[i].setName(pars[i]);
		params_[i].setType(types[type]);
		//(new SFParameter(pars[i],types[type]));
	}

	SFGridModel gridModel=(SFGridModel::valueOf(model));

	SFPipelineGrid* instance = new SFPipelineGrid(n+1, gridModel, params_,size);

	cmp->addGridInstance(instance);

}

void SFPipelineBuilder::closeElement() {
	if(getComponent()!=NULL)
		((SFBuilderElement*)getComponent())->finalize();

	setComponent(NULL);
}


vector<SFParameteri*> SFPipelineBuilder::getParameters(){
	SFProgramComponent* cmp=(SFProgramComponent*)getComponent();
	return cmp->getParameterArray();
}

}
