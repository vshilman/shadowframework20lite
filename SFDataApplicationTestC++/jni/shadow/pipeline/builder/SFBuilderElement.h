//
//  SFBuilderElement.h
//  
//
//  Created by Alessandro Martinelli on 23/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef _SFBuilderElement_h
#define _SFBuilderElement_h

#include <string> 

using namespace std;

namespace sf{

class SFBuilderElement {
    
public:

	SFBuilderElement(){

	}

	virtual ~SFBuilderElement(){

	}

	/**
	 * the end command is called
	 */
	virtual void finalize()=0;

	virtual SFBuilderElement* newInstance()=0;

};

}

#endif
