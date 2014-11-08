#ifndef shadow_geometry_functions_data_H_
#define shadow_geometry_functions_data_H_

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/functions/SFSimpleTexCoordGeometryuv.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFFloat.h"

namespace sf{
class SFSimpleTexCoordGeometryuvData : public SFDataAsset<SFSurfaceFunction>{

	SFFloat* du;
	SFFloat* dv;

public:
	SFSimpleTexCoordGeometryuvData();

	~SFSimpleTexCoordGeometryuvData();

	SFSurfaceFunction* SFSimpleTexCoordGeometryuvDatabuildResource() ;
	
	void SFSimpleTexCoordGeometryuvDataupdateResource(SFSurfaceFunction* resource);

};

}
#endif
