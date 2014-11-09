//
//  SFParameter.h
//  
//
//  Created by Alessandro Martinelli on 14/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFParameter__
#define SFParameter__

#include <string>
#include "SFParameteri.h"

using namespace std;

namespace sf{
class SFParameter:public SFParameteri {
    
    string name;

    short type;

public:
    
	SFParameter();

	virtual ~SFParameter(){};
    
	SFParameter(string name);
	
	SFParameter(string name, short type);

	void setName(string name);

	string getName();
    
	short getType();
    
	void setType(short type);
	
    //shouldnot be necessary
	//public string* toString() {
	//	return name+"("+type+")";
	//}
};

}
#endif /* defined(SFParameter__) */
