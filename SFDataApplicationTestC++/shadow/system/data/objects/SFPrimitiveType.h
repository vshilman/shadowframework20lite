
#ifndef SFPrimitiveType_H
#define SFPrimitiveType_H

#include "../SFDataObject.h"

namespace sf{
class SFPrimitiveType : public SFDataObject{
    
public:
	virtual ~SFPrimitiveType(){

	};
	int elementsSize();
	
    SFDataObject* sonsObject(int son);
	
    virtual SFPrimitiveType* clone()=0;
};
}



#endif /* defined(SFPrimitiveType__) */
