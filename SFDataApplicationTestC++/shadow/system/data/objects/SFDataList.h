

#ifndef SFDataList_H
#define SFDataList_H

#include "../SFDataObject.h"
#include <vector>
#include <iostream>
using namespace std;

//TODO: must destroy all its Element


namespace sf{
template <class T>
class SFDataList: public SFDataObject{
    
private:
	vector<T*> dataObject;
    T* type;
	
public:
	SFDataList(T* type) {
		this->type = type;
	}
    
    ~SFDataList(){
//        delete type;
        for(int i=0;i<dataObject.size();i++){
            delete dataObject[i];
        }
    }

	vector<T*> getDataObject() {
		return dataObject;
	}
    
	int elementsSize() {
		return dataObject.size();
	}
	
	
	void readFromStream(SFInputStream* stream) {
		int n=stream->readShort();
		cout << "Data List n=" << n << endl;
		for (int i = 0; i < n; i++) {
			dataObject.push_back((T*)type->clone());
			dataObject.at(i)->readFromStream(stream);
		}
	}
	
	
	SFDataList<T>* clone(){
		return new SFDataList<T>((T*)((SFDataObject*)type)->clone());
	}
	
	void add(T* t) {
		dataObject.push_back(t);
	}
	
	
	T* get(int index) {
		return dataObject.at(index);
	}
	
	int size() {
		return dataObject.size();
	}
	
};
}



#endif /* defined(SFDataList__) */
