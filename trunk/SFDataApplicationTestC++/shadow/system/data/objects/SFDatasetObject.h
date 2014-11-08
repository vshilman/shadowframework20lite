

#ifndef SFDatasetObject_H
#define SFDatasetObject_H

#include "SFPrimitiveType.h"
#include "../SFDataset.h"
#include "../SFDataCenter.h"


namespace sf{
template <class T>
class SFDatasetObject: public SFPrimitiveType {
    
private:
    T* dataset;
	
public:
	SFDatasetObject() {
		dataset=0;
	}
    
    ~SFDatasetObject(){
        delete dataset;
    }
    
    
    SFDatasetObject(T* dataset) {
		this->dataset = dataset;
	}
    
	T* getDataset() {
		return dataset;
	}
    
	void setDataset(T* dataset) {
		this->dataset = dataset;
	}
    
	
	SFDatasetObject<T>* clone() {
		return new SFDatasetObject<T>(0);
	}
	
	void readFromStream(SFInputStream* stream) {
		SFDataset* dataset=SFDataCenter::getDataCenter()->readDataset(stream);
		this->dataset=(T*)dataset;
	}
	
	
};
}

#endif /* SFDatasetObject_H */
