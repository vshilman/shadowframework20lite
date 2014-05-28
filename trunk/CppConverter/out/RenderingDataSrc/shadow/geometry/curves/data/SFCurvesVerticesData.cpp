#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShort.h"

abstract class SFCurvesVerticesData extends SFDataAsset<SFCurve> implements SFValuesDataKeeperCurve{

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
		new SFLibraryReference<SFValuesList<SFValuenf>>();
	SFShort closed=new SFShort((short)0);

	ArrayList<SFValuenf> addValues=new ArrayList<SFValuenf>();

	SFCurvesVerticesData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("closed", closed);
		setData(parameters);
	}

	
	void addBuildingValue(SFValuenf value) {
		addValues.add(value);
	}

	SFCurvesVerticesData(SFGraphicsAsset<SFValuesList<SFValuenf>> vertices) {
		this->vertices.setDataset(vertices);
	}

	void setVertices(SFGraphicsAsset<SFValuesList<SFValuenf>> dataset, SFValuenf... vertices) {
		setVertices(dataset);
		for (int i = 0; i < vertices.length; i++) {
			addVertex(vertices[i]);
		}

	}

	void addVertex(SFValuenf value){
		this->vertices.getDataset().getResource().addValue(value);
	}

	void setClosed(boolclosed){
		this->closed.setShortValue((short)(closed?1:0));
	}

	boolgetClosed(){
		this->closed.getShortValue()==1;
	}

	void setVertices(SFGraphicsAsset<SFValuesList<SFValuenf>> vertices) { 
		this->vertices.setDataset(vertices);
	}

	void setVertices(String name) { 
		this->vertices.setReference(name);
	}

}
;
}
#endif
