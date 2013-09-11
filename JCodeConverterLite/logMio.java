//Package and imports are not generated automatically


public class SFParameter{

//#include "SFParameter.h";


//SFParameter::SFParameter() {
//this->type=GLOBAL_GENERIC;
//}


public SFParameter(String name) {
	super();
	this.name = name;
}

public SFParameter(String name,short type) {
	super();
	this.name = name;
	this.type = type;
}

public String getName( ) {
	return this.name;
}

public short getType( ) {
	return this.type;
}

public void setType (short type ) {
	this.type=type;
}

}