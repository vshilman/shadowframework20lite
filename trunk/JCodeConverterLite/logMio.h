#include <string>
#include SFParameteri.h

using namespace std;

class SFParameter : public SFParameteri{

	string name;


public:


	//package shadow.pipeline.parameters;

	//protected short type=GLOBAL_GENERIC;

	SFParameter();
	SFParameter(string name,short type);
	SFParameter(string name);
	string getName ( );
	short getType ( );
	void setType( short type );
	//@Override	public String toString() {
//return name+"("+type+")";
//}

};