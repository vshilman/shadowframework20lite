//
//  SFPipelineElement.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPipelineElement__
#define SFPipelineElement__

#include <string>

using namespace std;


namespace sf{

class SFPipelineElement {
    
private:
	string name;
    
public:
	SFPipelineElement();
    
	/**
	 * Name must be a valid SFCode
	 * @return
	 */
	string getName();
    
	/**
	 * Name must be a valid SFCode
	 * @return
	 */
	void setName(string name);
};

}

#endif /* defined(SFPipelineElement__) */
