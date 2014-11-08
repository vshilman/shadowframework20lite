#ifndef shadow_renderer_SFStructureArrayResource_H_
#define shadow_renderer_SFStructureArrayResource_H_

#include "SFValuenf.h"
#include "SFStructureArray.h"
#include "SFGraphicsResource.h"
#include "SFResource.h"

namespace sf{
class SFStructureArrayResource : public SFGraphicsResource{

	SFStructureArray* array;
  	SFResource resource;

public:
  	SFStructureArrayResource(SFStructureArray* array);

  	SFStructureArray* getArray();

  	void setArray(SFStructureArray* array);

  	SFResource* getResource();

  	void setParameterValue(int index,int parametersIndex,SFValuenf element);

  	void getParameterValue(int index,int parametersIndex,SFValuenf element);

  	void init();
	
  	void destroy();

};

}
#endif
