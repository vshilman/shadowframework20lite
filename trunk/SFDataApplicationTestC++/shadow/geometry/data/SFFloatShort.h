#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data/objects/SFGenericFixedFloat.h"

namespace sf{
class SFFloatShort : public SFGenericFixedFloat{

	static const float MULTIPLIER=1;
	static const float BACKMULTIPLIER=1;

public:
	SFFloatShort();

	SFFloatShort(float multiplier, float backmultiplier);

	SFFloatShort(float f);
	
	SFFloatShort* clone();

	int getBitSize();

};

}
#endif
