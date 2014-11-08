
#ifndef SFParsableProgramComponent_H
#define SFParsableProgramComponent_H

#include "../SFProgramComponent.h"
#include "../SFProgramComponent.h"
#include "SFBuilderElement.h"
#include "../SFPipeline.h"


namespace sf{

class SFParsableProgramComponent :public SFProgramComponent , public SFBuilderElement{
    
public:
	void finalize();
    
//	vector<string> allCommands;


    SFBuilderElement* newInstance(){
        return new SFParsableProgramComponent();
    }
//
//	vector<string> getAllCommands() {
//        if(allCommands.size()==0){
//
//            allCommands.push_back("begin");
//            allCommands.push_back("use");
//            allCommands.push_back("write");
//            allCommands.push_back("rewrite");
//            allCommands.push_back("param");
//            allCommands.push_back("grid");
//            allCommands.push_back("define");
//            allCommands.push_back("end");
//        }
//        return allCommands;
//	}
};

}

#endif /* defined(SFParsableProgramComponent__) */
