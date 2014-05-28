#ifndef shadow_geometry_geometries_data_H_
#define shadow_geometry_geometries_data_H_

#include "shadow/geometry/geometries.SFTiledTexCoord.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryArrayObject.h"
#include "shadow/system/data.objects.SFIntShortField.h"
#include "shadow/system/data.objects.SFString.h"
#include "shadow/tmp/SFTilesSpace.h"

namespace sf{
class SFTiledTexCoordData extends SFGraphicsAsset<SFTiledTexCoord>{

	SFIntShortField dimension=new SFIntShortField(0);
	ArrayObject matrix=new SFBinaryArrayObject(1);
	SFString primitive=new SFString();
	SFLibraryReference<SFTilesSpace> tilesSpace=new SFLibraryReference<SFTilesSpace>();

	SFTiledTexCoordData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("dimension", dimension);
		parameters.addObject("tilesSpace", tilesSpace);
		parameters.addObject("matrix", matrix);
		setData(parameters);
	}

	void setMatrix(int[] matrix){
		this->matrix.setBytes(matrix);
	}

	void setPrimitive(String primitive){
		this->primitive.setString(primitive);
	}

	void setDimension(int width,int height){
		dimension.setShort(0, width);
		dimension.setShort(1, height);
	}

	void setTilesSpace(String space){
		tilesSpace.setReference(space);
	}

	
	SFTiledTexCoord buildResource() {
		final SFTiledTexCoord tileCoord=new SFTiledTexCoord(matrix.getValues(),8,8);
		tileCoord.setSpace(tilesSpace.getResource().getTilesSet());
		SFPrimitive tilesPrimitive=SFPipeline.getPrimitive(primitive.getString());
		tileCoord.setPrimitive(tilesPrimitive);
		return tileCoord;
	}

	
	void updateResource(SFTiledTexCoord resource) {
		final SFTiledTexCoord tileCoord=(SFTiledTexCoord)resource;
		tileCoord.setMatrix(matrix.getValues());
		tileCoord.setTilesX(8);//TODO : is really that way??
		tileCoord.setTilesY(8);
		tileCoord.setSpace(tilesSpace.getResource().getTilesSet());
		SFPrimitive tilesPrimitive=SFPipeline.getPrimitive(primitive.getString());
		tileCoord.setPrimitive(tilesPrimitive);
	}
}
;
}
#endif
