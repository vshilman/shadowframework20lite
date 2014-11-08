

#ifndef SFMatrix3fData_H
#define SFMatrix3fData_H


#include "SFVectorData.h"
#include "../../../math/SFMatrix3f.h"


namespace sf{
class SFMatrix3fData : public SFVectorData{
	
private:
	SFMatrix3f matrix;
	
public:
	SFMatrix3fData(SFMatrix3f matrix);
	
	SFMatrix3fData() ;
    
	SFMatrix3f getMatrix3f();
	
	SFVectorData* clone();
};
}

#endif /* SFMatrix3fData_H */
