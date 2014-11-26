#include "shadow\system\data\SFDataObject.h"
#include "shadow\system\data\objects\SFFloat.h"
#include "shadow\system\data\objects\SFInt.h"


class SFNamedParametersObjectTest001: public SFDataObject{

	int int1;
	float float1;

	public:
	SFNamedParametersObjectTest001(int int1, float float1);
		int getInt1();
		float getFloat1();

};
