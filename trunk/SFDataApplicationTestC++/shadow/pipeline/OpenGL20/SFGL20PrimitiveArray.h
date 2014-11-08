

#ifndef SFGL20PrimitiveArray_H
#define SFGL20PrimitiveArray_H


#include "../parameters/SFParameteri.h"

#include "../SFPrimitiveArray.h"
#include "../SFPrimitiveIndices.h"

#include "SFGL20ValuenfArray.h"


namespace sf{
class SFGL20PrimitiveArray :public SFPrimitiveArray{
    
private:

	vector<SFPrimitiveIndices*> data;
	
	SFPrimitive* primitive;
	SFGL20ValuenfArray** primitiveData;
	

public:
	SFGL20PrimitiveArray() {
		this->primitive=0;
		this->primitiveData=0;
	}
	
    ~SFGL20PrimitiveArray(){
        int size=primitive->getGridsCount();
        for(int i=0;i<size;i++){
            delete primitiveData[i];
        }
        delete primitiveData;
    }
    
	SFGL20PrimitiveArray(SFPrimitive* primitive) {
		this->primitive = primitive;
		
		primitiveData=new SFGL20ValuenfArray*[primitive->getGridsCount()];
		for (int gridIndex = 0; gridIndex < primitive->getGridsCount(); gridIndex++) {
			short type=primitive->getType(gridIndex);
			switch(type){
				case SFParameteri::GLOBAL_FLOAT: primitiveData[gridIndex]=new SFGL20ValuenfArray(1); break;
				case SFParameteri::GLOBAL_FLOAT2: primitiveData[gridIndex]=new SFGL20ValuenfArray(2); break;
				case SFParameteri::GLOBAL_FLOAT3: primitiveData[gridIndex]=new SFGL20ValuenfArray(3); break;
			}
		}
	}
	
	SFGL20PrimitiveArray* getView(SFPrimitive* primitive){
		SFGL20PrimitiveArray* primitiveArray=new SFGL20PrimitiveArray();
        
        primitiveArray->primitive=primitive;
		primitiveArray->primitiveData=primitiveData;
		return primitiveArray;
	}
	
	static SFGL20PrimitiveArray* mixArrays(SFPrimitiveArray** arrays,int arraysSize,SFPrimitive* mixPrimitive){
        
		int totalComponents=0;
		int totalGrids=0;
		for (int i = 0; i < arraysSize; i++) {
			totalComponents+=arrays[i]->getPrimitive()->getComponents().size();
			totalGrids+=arrays[i]->getPrimitive()->getGridsCount();
		}
		
		vector<SFProgramComponent*> components;
		vector<SFPrimitiveBlock*> blocks;
		
		int primitiveIndex=0;
		unsigned int inPrimitiveIndex=0;
		for (int componentIndex = 0; componentIndex < totalComponents; componentIndex++) {
			components[componentIndex]=arrays[primitiveIndex]->getPrimitive()->getComponents()[inPrimitiveIndex];
			blocks[componentIndex]=arrays[primitiveIndex]->getPrimitive()->getBlocks()[inPrimitiveIndex];
			inPrimitiveIndex++;
			if(inPrimitiveIndex>=arrays[primitiveIndex]->getPrimitive()->getComponents().size()){
				primitiveIndex++;
				inPrimitiveIndex=0;
			}
		}
		
		mixPrimitive->setPrimitiveElements(blocks, components);
		
		SFGL20PrimitiveArray* array=new SFGL20PrimitiveArray();
		array->primitive=mixPrimitive;
		
		array->primitiveData=new SFGL20ValuenfArray*[totalGrids];
		
		primitiveIndex=0;
		inPrimitiveIndex=0;
		for (int gridIndex = 0; gridIndex < totalGrids; gridIndex++) {
            
			array->primitiveData[gridIndex]=(SFGL20ValuenfArray*)(arrays[primitiveIndex]->getPrimitiveData(inPrimitiveIndex));
			inPrimitiveIndex++;
			if(inPrimitiveIndex>=arrays[primitiveIndex]->getPrimitive()->getComponents().size()){
				primitiveIndex++;
				inPrimitiveIndex=0;
			}
		}
		
		for (int i = 0; i < arrays[0]->getElementsCount(); i++) {
			int*  indices=new int[mixPrimitive->getIndicesCount()];
			
			int indicesIndex=0;
			for (int j = 0; j < arraysSize; j++) {
				int* oldIndices=((SFGL20PrimitiveArray*)(arrays[j]))->data.at(i)->getPrimitiveIndices();
                int size=((SFGL20PrimitiveArray*)(arrays[j]))->data.at(i)->length();
				for (int k = 0; k < size; k++) {
					indices[indicesIndex+k]=oldIndices[k];
				}
				indicesIndex+=size;
			}
            
			SFPrimitiveIndices* prIndices=new SFPrimitiveIndices();
			prIndices->setPrimitiveIndices(indices);
			array->data.push_back(prIndices);
		}
		
		
		return array;
	}
    
	
	void assignValues(SFPrimitiveIndices* writing,
                                SFPrimitiveIndices* reading){
		//try {
        writing->set(reading);
		//} catch (...) {
		//	e.printStackTrace();
			//throw new SFArrayElementException(writing, "Malstructured Primitive data");
		//}
	}
	
	
	void setElementData(int index, SFPrimitiveIndices element,
                               int gridIndex){
		
		data.at(index)->setData(element,primitive->getIndicesPositions()[gridIndex],
                                primitive->getIndicesSizes()[gridIndex]);
	}
	
	
	SFPrimitiveIndices* generateGenericElement() {
        SFPrimitiveIndices* indices=new SFPrimitiveIndices(this->primitive);
		return indices;
	}
    
	SFValuesArray* getPrimitiveData(int gridIndex) {
		return (SFValuesArray*)(primitiveData[gridIndex]);
	}
    
	SFGL20ValuenfArray** getPrimitiveData() {
		return primitiveData;
	}
	
	
	void init() {
		//init will become necessary when we will use Object Buffer to store values data
	}
	
	
	void destroy() {
		//destroy will become necessary when we will use Object Buffer to store values data
	}
	
	SFPrimitive* getPrimitive() {
		return primitive;
	}
    
    SFPrimitiveIndices* generateSample() {
        return generateGenericElement();
    }

    //Used only by implementation
	SFPrimitiveIndices* getValue(int index){
		return data.at(index);
	}
	
	
	int generateElement() {
		SFPrimitiveIndices* e=generateGenericElement();
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
	
	void getElement(int index, SFPrimitiveIndices* element){
		assignValues(element, data.at(index));
	}
	
	
	int getElementsCount() {
		return data.size();
	}
	
	void setElement(int index, SFPrimitiveIndices* element){
		assignValues(data.at(index),element);
	}
    
    void eraseElements(int index, int elementsCount) {
		for (int i = 0; i < elementsCount; i++) {
			data.erase(data.begin()+index);
		}
	}
	
};
}



#endif /* defined(SFGL20PrimitiveArray_H) */
