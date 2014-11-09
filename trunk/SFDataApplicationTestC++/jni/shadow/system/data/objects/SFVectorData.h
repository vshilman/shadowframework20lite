
#ifndef SFVectorData_H
#define SFVectorData_H

#include "SFPrimitiveType.h"


namespace sf{
class SFVectorData : public SFPrimitiveType{
    
private:
    float* floatValues;
    int n;
	
public:
	SFVectorData(float* floatValues,int floatValuesSize);

	SFVectorData(int floatValuesSize);
    
    ~SFVectorData();
    
	float* getFloatValues();
    
	void readFromStream(SFInputStream* stream);
	
	SFVectorData* clone();
};
}
#endif /* SFVectorData_H */
