
#include "SFStructureReference.h"

namespace sf{

	SFStructureReference::SFStructureReference(){
  		refIndex=0;
	}

	
  	SFResource* SFStructureReference::getResource() {
  		return &resource;
	}

  	SFStructureReference::SFStructureReference(SFStructureArrayResource* table,int index) {
  		this->tableData=table;
		this->structure = tableData->getArray()->getPipelineStructure();
		this->refIndex=index;
  		resource.setResource(0, table->getResource());
	}

  	void SFStructureReference::setArray(){

	}

  	void SFStructureReference::setValue(SFStructureData data) {
  		tableData->getArray()->setElement(refIndex, &data);
	}

  	SFPipelineStructure* SFStructureReference::getStructure() {
  		return structure;
	}

  	int SFStructureReference::getIndex() {
  		return refIndex;
	}

  	void SFStructureReference::setMaterialIndex(int materialIndex) {
		this->refIndex=materialIndex;
	}


  	void SFStructureReference::setRefIndex(int refIndex) {
		this->refIndex = refIndex;
	}

  	SFStructureArrayResource* SFStructureReference::getTable() {
  		return tableData;
	}

  	void SFStructureReference::setTable(SFStructureArrayResource* table) {
		this->tableData=table;
	}

  	void SFStructureReference::setStructureData(SFStructureData* data){
  		tableData->getArray()->setElement(refIndex,data);
	}

  	void SFStructureReference::getStructureData(SFStructureData* data){
  		tableData->getArray()->getElement(refIndex,data);
	}


  	void SFStructureReference::setParameter(int index,SFValuenf* data){
  		tableData->getArray()->setParameterValue(refIndex,index,data);
	}

  	void SFStructureReference::getParameter(int index,SFValuenf* data){
  		tableData->getArray()->getParameterValue(refIndex,index,data);
	}

}
