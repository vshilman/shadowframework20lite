
#ifndef SFVertex2fData_H
#define SFVertex2fData_H

#include "SFVectorData.h"
#include "../../../math/SFVertex2f.h"


namespace sf{
class SFVertex2fData : public SFVectorData{
	
    SFVertex2f vertex;
	
public:
	SFVertex2fData();
    
	SFVertex2f getVertex2f();
	
    SFVectorData* clone() ;
};
}


#endif /* SFVertex2fData_H */
