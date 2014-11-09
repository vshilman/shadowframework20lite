

#ifndef SFGL20StructureArray_H
#define SFGL20StructureArray_H

#include "SFGL20ListData.h"
#include "../SFStructureArray.h"


namespace sf{
class SFGL20StructureArray : public SFStructureArray{
    
private:
	SFPipelineStructure* structure;
	
public:
	SFGL20StructureArray(SFPipelineStructure* structure) {
		this->structure=structure;
	}
	
	void assignValues(SFStructureData* writing, SFStructureData* reading){
		SFValue** writingValues=writing->getValues();
		SFValue** readingValues=reading->getValues();
		
		//if(writing.size()!=reading.size())
		//	throw new SFArrayElementException(writing, "Different Structure Size");
            
            for (int i = 0; i < reading->size(); i++) {
                //if(readingValues[i]->getSize()!=writingValues[i]->getSize()){
                //    throw new SFArrayElementException(writing, "Different Value Size");
                //}
                writingValues[i]->setArray(readingValues[i]->getV());
            }
	}
	
	
	SFStructureData* generateGenericElement() {
		//TODO should use a prototype
		return new SFStructureData((this->structure));
	}
	
	
	SFPipelineStructure* getPipelineStructure() {
		return structure;
	}
	
	
	/** writes a <E> with the content of the element at a given position
	 * @param index the index at which element is
	 * read
	 * @param element the element where to store data
	 * */
	void getParameterValue(int index,int parametersIndex,SFValuenf* element) {
		
		SFValue* value = findValue(index, parametersIndex, *element);
		element->setArray(value->getV());
	}
    
    
	SFValue* findValue(int index, int parametersIndex,
                                SFValuenf element) {
		SFStructureData* strData=data.at(index);
		//if(strData.size()<=parametersIndex)
			//throw new SFArrayElementException(strData, "Different Structure Size");
        SFValue* value=strData->getValue(parametersIndex);
        //if(value.getSize()!=element.getSize())
        //        throw new SFArrayElementException(strData, "Different Value Size");
        return value;
	}
	
	/** Writes the content of a given element at the given position
	 * @param index the index at which element is written
	 * @param element the element where data are read
	 */
	void setParameterValue(int index,int parametersIndex,SFValuenf* element){
        
		SFValue* value = findValue(index, parametersIndex, *element);
		value->setArray(element->getV());
	}

	
	void init() {
		//init will become necessary when we will use Object Buffer to store values data
	}
	
	
	void destroy() {
		//destroy will become necessary when we will use Object Buffer to store values data
	}
    
    
    
    vector<SFStructureData*> data;
	
	void eraseElements(int index, int elementsCount) {
		for (int i = 0; i < elementsCount; i++) {
			data.erase(data.begin()+index);
		}
	}
	
	SFStructureData* generateSample() {
		return generateGenericElement();
	}
	
	//Used only by implementation
	SFStructureData* getValue(int index){
		return data.at(index);
	}
	
	
	int generateElement() {
		SFStructureData* e=generateGenericElement();
		data.push_back(e);
		return data.size()-1;
	}
	
	
	int generateElements(int count) {
		int index=data.size();
		for (int i = 0; i < count; i++) {
			data.push_back(generateGenericElement());
		}
		return index;
	}
	
	void getElement(int index, SFStructureData* element){
		assignValues(element, data.at(index));
	}
	
	
	int getElementsCount() {
		return data.size();
	}
	
	void setElement(int index, SFStructureData* element){
		assignValues(data.at(index),element);
	}
};
}

#endif /* defined(SFGL20StructureArray_H) */
