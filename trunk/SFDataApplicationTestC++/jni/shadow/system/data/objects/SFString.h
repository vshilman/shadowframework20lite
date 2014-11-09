
#ifndef SFString_H
#define SFString_H

#include "SFPrimitiveType.h"
#include "../SFInputStream.h"

namespace sf{
class SFString :public SFPrimitiveType {
    
    string str;
	
public:
	SFString();
	virtual ~SFString(){

	}
	
	SFString(string str);
    
	string getString();
    
	void setString(string str);
    
	void readFromStream(SFInputStream* stream);
	
	SFString* clone();
};
}

#endif /* SFString_H */
