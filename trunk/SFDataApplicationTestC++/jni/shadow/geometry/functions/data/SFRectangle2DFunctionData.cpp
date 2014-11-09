
#include "SFRectangle2DFunctionData.h"

namespace sf{

SFRectangle2DFunctionData::SFRectangle2DFunctionData() {
	x=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	y=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	w=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	h=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
	SFNamedParametersObject* parametersObject=new SFNamedParametersObject();
	parametersObject->addObject(x);
	parametersObject->addObject(y);
	parametersObject->addObject(w);
	parametersObject->addObject(h);
	setData(parametersObject);
}


SFRectangle2DFunctionData::~SFRectangle2DFunctionData() {
	delete x;
	delete y;
	delete w;
	delete h;
}

SFRectangle2DFunction* SFRectangle2DFunctionData::buildResource() {
	SFRectangle2DFunction* function=new SFRectangle2DFunction(
			x->getBinaryValue()->getFloat(),
			y->getBinaryValue()->getFloat(),
			w->getBinaryValue()->getFloat(),
			h->getBinaryValue()->getFloat()
	);
	return function;
}


void SFRectangle2DFunctionData::updateResource(SFSurfaceFunction* resource) {
	SFRectangle2DFunction* function=(SFRectangle2DFunction*)resource;
	function->setX(x->getBinaryValue()->getFloat());
	function->setY(y->getBinaryValue()->getFloat());
	function->setW(w->getBinaryValue()->getFloat());
	function->setH(h->getBinaryValue()->getFloat());
}

}
