#ifndef shadow_geometry_curves_data_SFValuesDataKeeperCurve_H_
#define shadow_geometry_curves_data_SFValuesDataKeeperCurve_H_

#include "shadow/math/SFValuenf.h"

namespace sf{
class SFValuesDataKeeperCurve {

public:
	virtual ~SFValuesDataKeeperCurve();
	virtual void addBuildingValue(SFValuenf value)=0;
};

}
#endif
