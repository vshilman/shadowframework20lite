#ifndef shadow_renderer_SFStructureReference_H_
#define shadow_renderer_SFStructureReference_H_

#include "SFValuenf.h"
#include "SFPipelineStructure.h"
#include "SFStructureData.h"
#include "SFIResource.h"
#include "SFResource.h"
#include "SFStructureArrayResource.h"

namespace sf{
class SFStructureReference : public SFIResource{

  	SFPipelineStructure* structure;
  	SFStructureArrayResource* tableData;
  	int refIndex;
  	SFResource resource;

public:
  	SFStructureReference();

  	SFResource* getResource();

  	SFStructureReference(SFStructureArrayResource* table,int index);

  	void setArray();

  	void setValue(SFStructureData data);

  	SFPipelineStructure* getStructure();

  	int getIndex();

  	void setMaterialIndex(int materialIndex);

  	void setRefIndex(int refIndex);

  	SFStructureArrayResource* getTable();

  	void setTable(SFStructureArrayResource* table);

  	void setStructureData(SFStructureData* data);

  	void getStructureData(SFStructureData* data);

  	void setParameter(int index,SFValuenf* data);

  	void getParameter(int index,SFValuenf* data);

};

}
#endif
