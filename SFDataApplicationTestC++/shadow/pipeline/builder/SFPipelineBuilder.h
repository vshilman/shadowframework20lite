
#ifndef SFPipelineBuilder_H
#define SFPipelineBuilder_H

#include <map>

#include "SFParsableProgramComponent.h"
#include "SFIPipelineBuilder.h"
#include "SFParsableProgramModule.h"
#include "SFBuilderStructure.h"
#include "SFParsablePrimitive.h"
#include "../SFGridModel.h"

using namespace std;


namespace sf{

class SFPipelineBuilder : public SFIPipelineBuilder {
    
private:
	static map<string, SFBuilderElement*> typeMap;
	
	static SFBuilderElement* getElement(string type);
	
	SFPipelineElement* component;

    
public:
	
	void generateElement(string type, string name);
	
	void buildDefineRule( string pWrote,short type, SFExpressionElement* function);
	
	SFPipelineElement* getComponent();
	
	void setComponent(SFPipelineElement* component);
    
	void setUseRule(string use);
	
	void buildWriteRule(string wrote, SFExpressionElement* function) ;
	
	
	void buildParamRule(short parameter, string use);
	
	void buildParamRule(string moduleString, vector<string> pars);
    
	map<string, short> types;
	
	void buildComponent(string componentName) ;
	
	void buildBlock(string block, string primitiveComponent);
	
	void buildDomain(string domain);
	
	void buildGrid(vector<string> pars, string model,string type, int n);
	
	void closeElement();

	vector<SFParameteri*> getParameters();
};

}

#endif /* defined(SFPipelineBuilder__) */
