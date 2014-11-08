

#ifndef SFBinaryFloatArrayObject_H
#define SFBinaryFloatArrayObject_H

#include "SFPrimitiveType.h"

namespace sf{
class SFBinaryFloatArrayObject : public SFPrimitiveType {

private:
	float* values;
	int size;
	int dim;
	
public:
	SFBinaryFloatArrayObject(int size,int dim);
    
    ~SFBinaryFloatArrayObject();
    
	SFBinaryFloatArrayObject* clone() ;
	
	float* getValues();
	
	void setValues(float* values);
    
	void readFromStream(SFInputStream* stream);
	
};
}



#endif /* SFBinaryFloatArrayObject_H */
