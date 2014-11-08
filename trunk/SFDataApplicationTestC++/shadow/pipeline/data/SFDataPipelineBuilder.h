//
//  SFDataPipelineBuilder.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFDataPipelineBuilder__
#define SFDataPipelineBuilder__


#include <iostream>
using namespace std;

#include "../../system/data/objects/SFCompositeDataArray.h"
#include "../../system/data/objects/SFDataList.h"
#include "../builder/SFIPipelineBuilder.h"
#include "../builder/SFPipelineBuilder.h"
//#include "../expression/SFExpressionGeneratorKeeper.h"
//#include "../expression/SFBasicExpressionGenerator.h"

#include <sstream>

#include "SFPipelineInstructionObjects.h"

namespace sf{

class SFDataPipelineBuilder : public SFCompositeDataArray{
    
private:
    
	SFDataList<SFPipelineInstructionObjects>* allInformations;
    
	SFPipelineBuilder pipelineBuilder;
    
public:
    
	SFDataPipelineBuilder() {
        generateData();
	}
    
    //this will use the deconstructor in SFCompositeDataArray
    
	void generateData() {
		allInformations = new SFDataList<SFPipelineInstructionObjects>(new SFPipelineInstructionObjects());
		addDataObject(allInformations);
	}
    
    
	SFCompositeDataArray* clone(){
        return new SFDataPipelineBuilder();
    }
	
	void apply(SFPipelineBuilder alternativeBuilder) {

			cout << "allInformations->size() " << allInformations->size() << endl;
        
			for (int i = 0; i < allInformations->size(); i++) {

				cout << "i " << i << " command " << allInformations->get(i) << " " <<endl;

				vector<string> parameters = allInformations->get(i)->getParameters();
				string command = allInformations->get(i)->commandName();

				cout << "i " << i << " command " << command << " " <<endl;
				if (command.compare("Begin")==0) {
					alternativeBuilder.generateElement(allInformations->get(i)->getData(0),
                                                       allInformations->get(i)->getData(1));
				}
                //				if (command.compare("Vertex")) {
                //					alternativeBuilder.addGridVertex(allInformations->get(i)->getData(0));
                //				}
                //				if (command.compare("Edge")) {
                //					alternativeBuilder.buildEdge(parameters);
                //				}
				else if (command.compare("Define")==0) {
                    short shortValue;
                    std::stringstream ss(allInformations->get(i)->getData(1));
                    ss >> shortValue;
                    SFExpressionElement* function=allInformations->get(i)->getExpression()->getElement();
                    alternativeBuilder.buildDefineRule(allInformations->get(i)->getData(0),
                                                       shortValue, function);
				}
				else if (command.compare("Grid")==0) {
                    short intValue;
                    std::stringstream ss(allInformations->get(i)->getData(2));
                    ss >> intValue;
					alternativeBuilder.buildGrid(parameters, allInformations->get(i)->getData(0),
                                                 allInformations->get(i)->getData(1),
                                                 intValue);
				}
                //				if (command.compare("Internal")) {
                //					alternativeBuilder.buildGridInternals(parameters);
                //				}
				else if (command.compare("Param")==0) {
                    short shortValue;
                    std::stringstream ss(allInformations->get(i)->getData(0));
                    ss >> shortValue;
					alternativeBuilder.buildParamRule(shortValue,
                                                      allInformations->get(i)->getData(1));
				}
				else if (command.compare("Param2")==0) {
					alternativeBuilder.buildParamRule(allInformations->get(i)->getData(0), parameters);
				}
                //				if (command.compare("Path")) {
                //					alternativeBuilder.buildPath(parameters);
                //				}
				else if (command.compare("Write")==0) {
                    SFExpressionElement* function=allInformations->get(i)->getExpression()->getElement();
					alternativeBuilder.buildWriteRule(allInformations->get(i)->getData(0), function);
				}
				//if (command.compare("Rewrite")==0) {
				//	alternativeBuilder.buildRewriteRule(allInformations->get(i)->getData(0), allInformations->get(i)->getData(1));
				//}
				else if (command.compare("Close")==0) {
					alternativeBuilder.closeElement();
				}
				else if (command.compare("Use")==0) {
					alternativeBuilder.setUseRule(allInformations->get(i)->getData(0));
				}
				else if (command.compare("Component")==0) {
					alternativeBuilder.buildComponent(allInformations->get(i)->getData(0));
				}
				else if (command.compare("Domain")==0) {
					alternativeBuilder.buildDomain(allInformations->get(i)->getData(0));
				}
				else if (command.compare("Block")==0) {
					alternativeBuilder.buildBlock(allInformations->get(i)->getData(0),allInformations->get(i)->getData(1));
				}
			}
        
	}
	
		
	int getSize() {
		return allInformations->size();
	}
};

}

#endif /* defined(SFDataPipelineBuilder__) */
