//
//  SFGlobalVSetGL20Implementor.cpp
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFGlobalVSetGL20Implementor.h"


namespace sf{

	map<short, string> SFGlobalVSetGL20Implementor::declarations;

	string SFGlobalVSetGL20Implementor::generateShaderParameters(vector<SFParameter*> set) {
		string parameters="";
		for(unsigned int i=0;i<set.size();i++){
			SFParameter* pr=set.at(i);
			if(SFGL20GlobalV::requiresDeclaration(pr)){
				string name=pr->getName();
				if(  dynamic_cast<SFPipelineRegister*>(pr))
					name=SFGL20GlobalV::getregName((SFPipelineRegister*)pr);
				string declaration=SFGL20GlobalV::getModifiers(pr);
				declaration+=" ";
				declaration+=SFGlobalVSetGL20Implementor::getDeclarationstring(pr->getType());
				declaration+=" ";
				name+";\n";

				parameters+=declaration;
			}
		}
		return parameters;
	}

	string SFGlobalVSetGL20Implementor::getDeclarationstring(short param_) {
		return declarations.at(param_);
	}

	string SFGlobalVSetGL20Implementor::generateInstancedStructures(SFPipelineStructureInstance* instance,
													 SFParameteri* functionParameter,string suffix,vector<SFParameter> parameters){

		string res="\n";
		SFParameter* sfParameters=instance->getParameters();

		for(int i=0;i<instance->getParametersLength();i++){
			SFParameter* sfparameteri=sfParameters+i;
			bool found=false;

			for(unsigned int j=0;j<parameters.size();j++){
				SFParameter parameter=parameters.at(j);
				if(parameter.getName().compare(sfparameteri->getName())==0){
					found=true;
				}
			}
			if(!found){
				short param_=sfparameteri->getType();
				res+="uniform "+getDeclarationstring(param_)+" "+suffix+sfparameteri->getName()+";\n";
				parameters.push_back(*sfparameteri);
			}

		}

		return res;
	}

	string SFGlobalVSetGL20Implementor::generateInstancedGrids(SFPipelineGrid instance,short type,string suffix){

		string res="\n";

		for(int i=0;i<instance.size();i++){
			res+="uniform "+getDeclarationstring(type)+" "+suffix+instance.getParameters()[i]->getName()+";\n";
		}

		return res;
	}

}
