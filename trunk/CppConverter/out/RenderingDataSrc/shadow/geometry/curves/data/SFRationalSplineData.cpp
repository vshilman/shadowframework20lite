#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves.SFRationalCurve.h"
#include "shadow/math/SFValue1f.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFLibraryReference.h"

abstract class SFRationalSplineData extends SFDataAsset<SFCurve> {

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
		new SFLibraryReference<SFValuesList<SFValuenf>>();
	SFLibraryReference<SFValuesList<SFValuenf>> weights = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	SFRationalSplineData() {
		super();
	}

	
	void updateResource(SFCurve resource) {
		SFRationalCurve spline=(SFRationalCurve)resource;

		//SFValuesIterator<SFValuenf> iterator=vertices.getResource().getIterator();
		int size=vertices.getResource().getSize();
		spline.setSize(size);
		int vertexSize=vertices.getResource().getValueSize();
		for (int i = 0; i < size; i++) {
			SFValuenf vertex=new SFValuenf(vertexSize);
			vertices.getResource().getValue(i, vertex);
			spline.setControlPoint(i, vertex);
		}
		float[] weights=new float[this->weights.getResource().getSize()];
		SFValue1f vertex=new SFValue1f(1);
		for (int i = 0; i < size; i++) {
			this->weights.getResource().getValue(i, vertex);
			weights[i]=vertex.getV()[0];
		}
		spline.setWeights(weights);

		spline.compileCurve();
	}

}
;
}
#endif
