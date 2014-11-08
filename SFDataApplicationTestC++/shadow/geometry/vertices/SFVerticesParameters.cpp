
#include "SFVerticesParameters.h"


namespace sf{

	SFVerticesParameters::SFVerticesParameters(){

	}

	SFVerticesParameters* SFVerticesParameters::getParameters() {
		return &verticesParameters;
	}

	void SFVerticesParameters::clear(){
		parameters.clear();
	}

	void SFVerticesParameters::setParameter(string parameter,float value){
		parameters[parameter]=value;
	}

	float SFVerticesParameters::getValue(string parameter){
		return parameters[parameter];
	}

}
