
#ifndef SFVoidData_H
#define SFVoidData_H

#include "../SFDataObject.h"

namespace sf{
class SFVoidData : public SFDataObject{
    
	
public:
    
	static SFVoidData* getData();
    
	SFVoidData* clone();
	
	void readFromStream(SFInputStream* stream);
		
};

static SFVoidData voidData;

}


#endif /* SFVoidData_H */
