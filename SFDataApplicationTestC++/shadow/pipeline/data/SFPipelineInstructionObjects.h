//
//  SFPipelineInstructionObjects.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineInstructionObjects__
#define SFPipelineInstructionObjects__

#include <iostream>
using namespace std;

#include "../../system/data/objects/SFString.h"
#include "../../system/data/objects/SFCompositeDataArray.h"
#include "../../system/data/objects/SFDataList.h"
#include "shadow/pipeline/data/SFPipelineInstructionObjects.h"
//#include "../../system/data/objects/SFString.h"
#include "shadow/pipeline/expression/SFPipelineExpression.h"

namespace sf{

class SFPipelineInstructionObjects : public SFCompositeDataArray{
    
private:
	SFString command;
	SFDataList<SFString>* parametersList;
	SFDataList<SFString>* dataList;
	SFPipelineExpression* expression;
	
public:
	SFPipelineInstructionObjects(){
		parametersList=0;
		dataList=0;
		expression=0;
		generateData();
	}
	
	~SFPipelineInstructionObjects(){
		delete parametersList;
		delete parametersList;
		delete expression;
	}

	SFPipelineExpression* getExpression(){
		return this->expression;
	}

	void generateData() {
		command=SFString("");
		parametersList=new SFDataList<SFString>(new SFString(""));
		dataList=new SFDataList<SFString>(new SFString(""));
		expression=new SFPipelineExpression();
		addDataObject(&command);
		addDataObject(parametersList);
		addDataObject(dataList);
		addDataObject(expression);
	}
	
	
	SFCompositeDataArray* clone() {
		return new SFPipelineInstructionObjects();
	}
    
	SFCompositeDataArray* newInstance() {
		return new SFPipelineInstructionObjects();
	}
	
	string commandName(){
		return command.getString();
	}
	
	vector<string> getParameters(){
		vector<string> parameters;
        for(int i=0;i<parametersList->size();i++){
            SFString* str=parametersList->get(i);
			parameters.push_back(str->getString());
		}
		return parameters;
	}
	
	string getData(int index){
		return dataList->get(index)->getString();
	}
	
	
};

}

#endif /* defined(SFPipelineInstructionObjects__) */
