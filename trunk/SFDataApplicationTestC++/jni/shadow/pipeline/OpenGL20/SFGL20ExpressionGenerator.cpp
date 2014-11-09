//
//  SFGL20ExpressionGenerator.cpp
//  
//
//  Created by Alessandro Martinelli on 20/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFGL20ExpressionGenerator.h"


namespace sf{

	SFGL20ExpressionGenerator* SFGL20ExpressionGenerator::generator;

    SFParameteri* SFGL20ExpressionGenerator::refParameter;

	map<string, string> SFGL20ExpressionGenerator::renameMap;

	map<string, string> SFGL20ExpressionGenerator::functionsOperator;

/* return null if operator string is not a function operator*/
	string SFGL20ExpressionGenerator::getAsFunction(string op){
        if(functionsOperator.size()==0){
            functionsOperator["clamp"]="clamp";
            functionsOperator["cos"]="cos";
            functionsOperator["sin"]="sin";
            functionsOperator["dot"]="dot";
            functionsOperator["sqrt"]="sqrt";
            functionsOperator["sample"]="texture2D";
            functionsOperator["cross"]="cross";
            functionsOperator["normalize"]="normalize";
            functionsOperator["inversesqrt"]="inversesqrt";
            functionsOperator["clamp"]="clamp";
        }

        string tmp=functionsOperator[op];
        return tmp;

	}

	string SFGL20ExpressionGenerator::getTypeWrapOpenstring(short wrappedType,short wrappingType){

		//No wrap needed!
		if(SFParameter::getExpressionDimension(wrappedType)==SFParameter::getExpressionDimension(wrappingType))
			return "";

		if(wrappingType==SFParameter::GLOBAL_GENERIC){
			return getTypeWrapOpenstring(wrappedType,refParameter->getType());
		}

		if(wrappingType<=SFParameter::GLOBAL_MATRIX4){
			if(wrappedType<wrappingType){
                string tmp="vec";
                stringstream tmp2;
                tmp2 << SFParameter::getExpressionDimension(wrappingType);
                tmp=tmp.append(tmp2.str());
                tmp=tmp.append("(");
                return tmp;
			}else if(wrappedType!=SFParameter::GLOBAL_GENERIC){
				return "(";
            }
		}

		return "";
	}

	string SFGL20ExpressionGenerator::getTypeWrapClosestring(short wrappedType,short wrappingType){

		if(SFParameter::getExpressionDimension(wrappedType)==SFParameter::getExpressionDimension(wrappingType))
			return "";

		if(wrappedType==SFParameter::GLOBAL_FLOAT){
			return ")";
		}

		switch (wrappingType) {
			case SFParameter::GLOBAL_GENERIC:
				//if(refParameter.getType()==SFParameter::GLOBAL_GENERIC)
				//	throw new SFException("Bad Code");
				return getTypeWrapClosestring(wrappedType,refParameter->getType());
			case SFParameter::GLOBAL_FLOAT:
				return ").x";
			case SFParameter::GLOBAL_FLOAT2:
				if(wrappedType<SFParameter::GLOBAL_MATRIX4){
					return ").xy";
				}break;
			case SFParameter::GLOBAL_FLOAT3:
				if(wrappedType==SFParameter::GLOBAL_FLOAT2)
					return ",0)";
				switch (wrappedType) {
					case SFParameter::GLOBAL_FLOAT4: return ").xyz";
					case SFParameter::GLOBAL_MATRIX4: return ").xyz";
				}break;
			case SFParameter::GLOBAL_FLOAT4:
				switch (wrappedType) {
					case SFParameter::GLOBAL_FLOAT2: return ",0,1)";
					case SFParameter::GLOBAL_FLOAT3: return ",1)";
				}break;
			case SFParameter::GLOBAL_MATRIX4:
				switch (wrappedType) {
					case SFParameter::GLOBAL_FLOAT3: return ",1)";
				}
		}
		return "";
	}

	void SFGL20ExpressionGenerator::closeElement(SFExpressionElement* element) {

		if(element==parameters.at(parameters.size()-1)){
			//System.out.println("ENTER!");
            SFExpressionElement* el=parameters.at(parameters.size()-1);

            string op=el->getElement();
			string function=getAsFunction(op);
			if(function.size()!=0){
				value+=")";
			}else{
				if(element->getElement().length()==1 && !element->getElement().compare(",")==0){
					value+=")";
				}
			}
			//if(!( dynamic_cast<SFExpressionVariable*>(parameters.at(parameters.size()-1)))){
			//	parameters.erase(parameters.end()-1);
			//}
			if(function.size()==0 && element->getElement().compare(",")!=0){
				value+=getTypeWrapClosestring(element->getType(),parameters.at(parameters.size()-1)->getType());
			}else{
				if(element->getType()!=parameters.at(parameters.size()-1)->getType()){
					function=getAsFunction(parameters.at(parameters.size()-1)->getElement());
					if(function.size()==0){
						value+=getTypeWrapClosestring(element->getType(),parameters.at(parameters.size()-1)->getType());
					}
				}
			}
		}else{
			/*string function=getAsFunction(parameters.at(parameters.size()-1)->getElement());
			if(function.size()==0 && dynamic_cast<SFExpressionVariable*>(element)){
				value+=getTypeWrapClosestring(element->getType(),parameters.at(parameters.size()-1)->getType());
			}*/
		}
	}

	void SFGL20ExpressionGenerator::refreshElement(SFExpressionElement* element) {
		string function=getAsFunction(element->getElement());
		if(function.size()==0){
			value+=element->getElement();
		}else{
			value+=",";
		}
	}

	void SFGL20ExpressionGenerator::startElement(SFExpressionElement* element) {
		/*
		if(element->getType()!=parameters.at(parameters.size()-1)->getType() && element->getType()>=0){
			string function=getAsFunction(parameters.at(parameters.size()-1)->getElement());
			if(function.size()==0){
				value+=getTypeWrapOpenstring(element->getType(),parameters.at(parameters.size()-1)->getType());
				if(!( dynamic_cast<SFExpressionVariable*>(element)))
					parameters.push_back(element);
			}
		}
		if(dynamic_cast<SFExpressionOperator*>(element) ){
			string function=getAsFunction(element->getElement());
			if(function.size()!=0){
				value+=function+"(";
			}else if(!element->getElement().compare(",")==0){
				if(element->getElement().compare("-")==0)
					if(element->getElementSize()==1)
						value+="-";
				value+="(";
			}
			if(parameters.at(parameters.size()-1)!=element)
				parameters.push_back(element);
		}
		if(dynamic_cast<SFExpressionVariable*>(element)){

			string data=element->getElement();

            try{
                string renameMapData=renameMap.at(data);
                value=value.append(data);
            }catch(...){
                value=value.append(data);
            }
		}*/
	}


	map<string, string> SFGL20ExpressionGenerator::getRenameMap() {
		return renameMap;
	}

	void SFGL20ExpressionGenerator::setRenameMap(map<string, string> renameMap) {
        SFGL20ExpressionGenerator::renameMap = renameMap;
	}

	SFParameteri* SFGL20ExpressionGenerator::getRefParameter() {
		return refParameter;
	}

	void SFGL20ExpressionGenerator::setRefParameter(SFParameteri* refParameter) {
        SFGL20ExpressionGenerator::refParameter=refParameter;
	}

	SFGL20ExpressionGenerator* SFGL20ExpressionGenerator::getGenerator(SFParameteri* outputParameter) {
		generator->parameters.clear();
		//generator->parameters.push_back(new SFExpressionTypeWrapper(outputParameter->getType()));
		generator->value="";

		return generator;
	}

	string SFGL20ExpressionGenerator::getFunctionstring(){
		return generator->value;
	}

}
