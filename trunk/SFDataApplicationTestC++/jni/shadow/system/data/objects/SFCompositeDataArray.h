

#ifndef SFCompositeDataArray_H
#define SFCompositeDataArray_H

#include "../SFDataObject.h"
#include <vector>
using namespace std;

namespace sf{
class SFCompositeDataArray : public SFDataObject{
    
	vector<SFDataObject*> dataObject;
	
public:
	virtual void generateData(){};
	virtual SFCompositeDataArray* clone()=0;
	
	SFCompositeDataArray();
    
    ~SFCompositeDataArray();

	void addDataObject(SFDataObject* object);
	
	vector<SFDataObject*>* getDataObject();
    
	int elementsSize();
	
	void readFromStream(SFInputStream* stream);
	
};
}

#endif /* SFCompositeDataArray_H */
