#include <string>

using namespace std;

class SFParameter{

public:

//#include "SFParameter.h";


//SFParameter::SFParameter() {
//this->type=GLOBAL_GENERIC;
//}


SFParameter(string name);

SFParameter(string name,short type);

string getName ( );

short getType ( );

void setType( short type );

};