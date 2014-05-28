#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFEnumObject.h"
#include "shadow/tmp/voronoi.SFBasicVoronoiDistances.h"
#include "shadow/tmp/voronoi.SFBasicVoronoiModels.h"
#include "shadow/tmp/voronoi.SFVoronoiSurfaceFunction.h"

namespace sf{
class SFVoronoiBitmapFunctionData extends SFDataAsset<SFSurfaceFunction>{

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	static String[] modelsNames=SFBasicVoronoiModels.names();

	static SFBasicVoronoiModels[] models=SFBasicVoronoiModels.values();

	SFEnumObject<SFBasicVoronoiModels> model=new SFEnumObject<SFBasicVoronoiModels>(models,modelsNames);

	static String[] distancesNames=SFBasicVoronoiDistances.names();

	static SFBasicVoronoiDistances[] distances=SFBasicVoronoiDistances.values();

	SFEnumObject<SFBasicVoronoiDistances> distance=new SFEnumObject<SFBasicVoronoiDistances>(distances,distancesNames);

	SFVoronoiBitmapFunctionData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("model", model);
		parameters.addObject("distance", distance);
		setData(parameters);
	}

	
	SFSurfaceFunction buildResource() {
		SFVoronoiSurfaceFunction function=new SFVoronoiSurfaceFunction();
		updateResource(function);
		return function;
	}


	
	void updateResource(SFSurfaceFunction resource) {
		SFVoronoiSurfaceFunction function=(SFVoronoiSurfaceFunction)resource;
		function.setDistance(distance.getElement());
		function.setModel(model.getElement());
		SFValuesList<SFValuenf> vertices=this->vertices.getDataset().getResource();
		ArrayList<SFVertex2f> centers=new ArrayList<SFVertex2f>();
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex2f vertex=new SFVertex2f(0,0);
			vertices.getValue(i, vertex);
			centers.add(vertex);
		}
		function.setCenters(centers);
	}
}
;
}
#endif
