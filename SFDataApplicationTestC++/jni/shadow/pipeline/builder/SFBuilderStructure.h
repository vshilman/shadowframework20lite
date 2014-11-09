
#ifndef SFBuilderStructure__
#define SFBuilderStructure__

#include <vector>
#include "../parameters/SFParameteri.h"
#include "../SFPipelineStructure.h"
#include "../SFPipeline.h"
#include "SFBuilderElement.h"

namespace sf{

class SFBuilderStructure : public SFPipelineStructure , public SFBuilderElement{
    
public:
	static vector<SFParameter*> loadingParameters;
    
    ~SFBuilderStructure();
    
	void addParameter(SFParameter* parameter);
	
	void finalize();

    SFBuilderElement* newInstance();
};

}

#endif /* defined(SFBuilderStructure__) */
