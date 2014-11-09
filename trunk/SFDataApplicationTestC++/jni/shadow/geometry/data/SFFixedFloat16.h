#ifndef shadow_geometry_data_H_
#define shadow_geometry_data_H_

#include "shadow/system/data/objects/SFGenericFixedFloat.h"

namespace sf{
class SFFixedFloat16 : public SFGenericFixedFloat{

	static const float MULTIPLIER=0.001f;
	static const float BACKMULTIPLIER=1000.0f;

public:
	SFFixedFloat16();

	SFFixedFloat16(float multiplier, float backmultiplier);

	SFFixedFloat16(float f);
	
	SFFixedFloat16* clone();

	int getBitSize();
};

}
#endif
