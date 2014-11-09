#ifndef shadow_geometry_curves_data_H_
#define shadow_geometry_curves_data_H_

#include "shadow/geometry/SFCurve.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/curves/SFRationalCurve.h"
#include "shadow/math/SFValue1f.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/data/SFDataAsset.h"
#include "shadow/system/data/SFLibraryReference.h"

namespace sf{
class SFRationalSplineData : public SFDataAsset<SFCurve> {

	SFLibraryReference<SFValuesList>* vertices;
	SFLibraryReference<SFValuesList>* weights;

public:
	SFRationalSplineData() {
		vertices = new SFLibraryReference<SFValuesList>();
		weights = new SFLibraryReference<SFValuesList>();
	}

	void updateResource(SFCurve* resource) {
		SFRationalCurve* spline=(SFRationalCurve*)resource;

		//SFValuesIterator<SFValuenf> iterator=vertices.getResource().getIterator();
		int size=vertices->getResource()->getSize();
		spline->setSize(size);
		int vertexSize=vertices->getResource()->getValueSize();
		for (int i = 0; i < size; i++) {
			SFValuenf* vertex=new SFValuenf(vertexSize);
			vertices->getResource()->getValue(i, vertex);
			spline->setControlPoint(i, vertex);
		}
		short size_=this->weights->getResource()->getSize();
		float* weights=new float[size_];
		SFValue1f* vertex=new SFValue1f(1);
		for (int i = 0; i < size_; i++) {
			this->weights->getResource()->getValue(i, vertex);
			weights[i]=vertex->getV()[0];
		}
		spline->setWeights(weights,size_);

		spline->compileCurve();
	}

};

}
#endif
