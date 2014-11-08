
#include "SFPipelineGrid.h"


namespace sf{

SFPipelineGrid::SFPipelineGrid(int n,SFGridModel model,
		SFParameteri** params,int paramsLength) {
	this->model=model;
	this->n = n;
	this->params=params;
	this->paramsLength=paramsLength;
}

SFPipelineGrid::~SFPipelineGrid(){
	delete params;
	//for(int i=0;i<paramsLength;i++){
	//    [i];
	//}
	//do not delete model!!
}

int SFPipelineGrid::getN(){
	return n;
}

SFParameteri** SFPipelineGrid::getParameters() {
	return params;
}

int SFPipelineGrid::getGridSize() {
	return this->model.getGridSize(this->n);
}

int SFPipelineGrid::size(){
	return paramsLength;
}

bool SFPipelineGrid::isTriangular(){
	return model.getGridSize==SFGridModel_Triangle.getGridSize;
}

short SFPipelineGrid::getParameterType(int parameterIndex){
	return params[parameterIndex]->getType();
}

}
