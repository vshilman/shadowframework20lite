
#ifndef SFVertex3fData_H
#define SFVertex3fData_H


#include "SFVectorData.h"
#include "../../../math/SFVertex3f.h"

namespace sf{
class SFVertex3fData : public SFVectorData{
	
private:
	SFVertex3f vertex;
	
public:
	SFVertex3fData(float x,float y,float z);
	
	SFVertex3fData() ;
    
	SFVertex3f getVertex3f();
	
	SFVectorData* clone();
};
}

#endif /* SFVertex3fData_H */
