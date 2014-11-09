#ifndef shadow_geometry_curves_data_SFCurvesDataKeeperSurface_H_
#define shadow_geometry_curves_data_SFCurvesDataKeeperSurface_H_

#include "shadow/geometry/SFCurve.h"

namespace sf{

class SFCurvesDataKeeperSurface {

public:
	virtual ~SFCurvesDataKeeperSurface();
	virtual void addBuildingCurve(SFCurve* curve)=0;

};

}
#endif
