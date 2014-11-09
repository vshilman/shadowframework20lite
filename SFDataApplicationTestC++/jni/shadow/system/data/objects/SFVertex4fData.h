
#ifndef SFVertex4fData_H
#define SFVertex4fData_H

#include "SFVectorData.h"
#include "../../../math/SFVertex4f.h"


namespace sf{
class SFVertex4fData : public SFVectorData{
	
    SFVertex4f vertex;
	
public:
	SFVertex4fData();
    
	SFVertex4f getVertex4f();
	
	SFVectorData* clone();
};
}

#endif /* SFVertex4fData_H */
