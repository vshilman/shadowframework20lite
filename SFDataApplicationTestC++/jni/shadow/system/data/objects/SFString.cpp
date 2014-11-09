
#include "SFString.h"

#include <iostream>
using namespace std;

namespace sf{
SFString::SFString() {
    this->str = "";
}

SFString::SFString(string str) {
    this->str = str;
}

string SFString::getString() {
    return str;
}

void SFString::setString(string str) {
    this->str = str;
}

int SFString_readFromStream_a=0;

void SFString::readFromStream(SFInputStream* stream) {
    str=stream->readString();
    cout << " [SFstring:" << str << "]"<<endl;
    if(SFString_readFromStream_a==1335)
    	((char*)(0))[0]=1;
    SFString_readFromStream_a++;
}

SFString* SFString::clone() {
    return new SFString(str);
}
}
