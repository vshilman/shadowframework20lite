#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions.SFSurfaceFunctionRandomizer.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFInt.h"

namespace sf{
class SFFunctionRandomizerData extends SFDataAsset<SFSurfaceFunction>{

	SFInt seed=new SFInt(9000);

	SFFunctionRandomizerData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("seed", seed);
		setData(parameters);
	}

	
	SFSurfaceFunction buildResource() {
		return new SFSurfaceFunctionRandomizer(seed.getIntValue());
	}

	
	void updateResource(SFSurfaceFunction resource) {
		SFSurfaceFunctionRandomizer randomizer=(SFSurfaceFunctionRandomizer)(resource);
		randomizer.setSeed(seed.getIntValue());
	}
}
;
}
#endif
