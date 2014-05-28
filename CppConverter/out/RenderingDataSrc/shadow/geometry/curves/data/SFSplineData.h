#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves.SFSpline.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

abstract class SFSplineData extends SFDataAsset<SFSpline>  implements SFValuesDataKeeperCurve{

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();
	ArrayList<SFValuenf> addValues=new ArrayList<SFValuenf>();

	SFSplineData(){
		build();
	}
	
	void addBuildingValue(SFValuenf value) {
		addValues.add(value);
	}

	void build() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}

	
	void updateResource(SFSpline spline) {

		//SFValuesIterator<SFValuenf> iterator=vertices.getResource().getIterator();
		int size=0;
		if(addValues.size()>0){
			size=addValues.size();

			spline.setSize(size);
			for (int i = 0; i < size; i++) {
				spline.setControlPoint(i, addValues.get(i));
			}
		}
			size=vertices.getResource().getSize();

			spline.setSize(size);
			int vertexSize=vertices.getResource().getValueSize();
			for (int i = 0; i < size; i++) {
				SFValuenf vertex=new SFValuenf(vertexSize);
				vertices.getResource().getValue(i, vertex);
				spline.setControlPoint(i, vertex);
			}
		}

		spline.compileCurve();

	}
}
;
}
#endif
