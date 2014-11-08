#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data/objects/SFGenericFixedFloat.h"

namespace sf{
class SFFixedFloatUnit8 : public SFGenericFixedFloat{

	static const int BIT_SIZE=8;
	static const float MULTIPLIER=0.004f;
	static const float BACKMULTIPLIER=250.0f;

public:
	SFFixedFloatUnit8();

	SFFixedFloatUnit8(float multiplier, float backmultiplier);

	SFFixedFloatUnit8(float f);

	SFFixedFloatUnit8* clone();

	int getBitSize() ;

};

}
#endif
