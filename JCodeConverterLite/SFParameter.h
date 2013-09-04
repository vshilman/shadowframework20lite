//
//  SFParameter.h
//  SpotLightTownTest
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef __SpotLightTownTest__SFParameter__
#define __SpotLightTownTest__SFParameter__

#include <string>
#include "SFParameteri.h"

using namespace std;

class SFParameter:public SFParameteri {
    
    string name;

    short type;

public:
    
	SFParameter();
    
	SFParameter(string name);
	
	SFParameter(string name, short type);
    
	string getName();
    
	short getType();
    
	void setType(short type);
	
    //shouldnot be necessary
	//public string* toString() {
	//	return name+"("+type+")";
	//}
};


#endif /* defined(__SpotLightTownTest__SFParameter__) */
