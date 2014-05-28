#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "shadow/math/SFValuenf.h"
#include "shadow/pipeline/SFPipelineStructure.h"
#include "shadow/pipeline/SFStructureData.h"
#include "shadow/system/SFIResource.h"
#include "shadow/system/SFResource.h"

///**
// * A Reference to a SFStructureArray.
// * Keeps a pointer to 1 structure instance into the array
// * 
// * @author Alessandro Martinelli
// */
class SFStructureReference implements SFIResource{

//	SFPipelineStructure structure;
	ArrayResource tableData;
//	int refIndex;
//	SFResource resource=new SFResource(1);

//	SFStructureReference(){

	}

	
//	SFResource getResource() {
//		return resource;
	}

//	SFStructureReference(SFStructureArrayResource table,int index) {
//		super();//remember: super() will call generateData(); that's way class attribute are initialized there, or things are not going to work.
		this->tableData=table;
		this->structure = tableData.getArray().getPipelineStructure();
		this->refIndex=index;
//		resource.setResource(0, table.getResource());
	}

//	void setArray(){

	}

//	void setValue(SFStructureData data) {
//		tableData.getArray().setElement(refIndex, data);
	}

//	SFPipelineStructure getStructure() {
//		return structure;
	}

//	int getIndex() {
//		return refIndex;
	}

//	void setMaterialIndex(int materialIndex) {
		this->refIndex=materialIndex;
	}


//	void setRefIndex(int refIndex) {
		this->refIndex = refIndex;
	}

//	SFStructureArrayResource getTable() {
//		return tableData;
	}

//	void setTable(SFStructureArrayResource table) {
		this->tableData=table;
	}

//	void setStructureData(SFStructureData data){
//		tableData.getArray().setElement(refIndex,data); 
	}

//	void getStructureData(SFStructureData data){
//		tableData.getArray().getElement(refIndex,data); 
	}


//	void setParameter(int index,SFValuenf data){
//		tableData.getArray().setParameterValue(refIndex,index,data); 
	}

//	void getParameter(int index,SFValuenf data){
//		tableData.getArray().getParameterValue(refIndex,index,data); 
	}

}
;
}
#endif
