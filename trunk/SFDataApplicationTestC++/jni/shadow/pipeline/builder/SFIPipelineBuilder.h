
#ifndef _SFIPipelineBuilder_h
#define _SFIPipelineBuilder_h

#include <string>
#include <vector>

#include "../SFPipelineElement.h"
#include "../expression/SFExpressionElement.h"

using namespace std;

namespace sf{

class SFIPipelineBuilder {
    
public:
	SFIPipelineBuilder(){};

	virtual ~SFIPipelineBuilder(){}

	virtual void generateElement(string type, string name)=0;
    
	virtual SFPipelineElement* getComponent()=0;
    
	virtual void setComponent(SFPipelineElement* component)=0;
    
	virtual void setUseRule(string use) =0;
	
	virtual void buildDefineRule( string pWrote,short type, SFExpressionElement* function) =0;
    
	//virtual void addGridVertex(string token);
    
	virtual void buildWriteRule(string wrote, SFExpressionElement* function)=0;
	
	virtual void buildParamRule(short parameter, string use)=0;
    
	//virtual void buildPath(List<string> paths);
    
	virtual void buildParamRule(string modulestring, vector<string> pars)=0;
    
	//virtual void buildGridInternals(List<string> internals);
    
	virtual void buildGrid(vector<string> pars, string model,string type, int n)=0;
	
	virtual void buildComponent(string componentName)=0;
    
	virtual void buildDomain(string domain)=0;
    
	virtual void buildBlock(string block,string primitiveComponent)=0;
    
	virtual void closeElement()=0;

	virtual vector<SFParameteri*> getParameters()=0;
    
	//virtual void buildEdge(List<string> edges);
  
};

}

#endif
