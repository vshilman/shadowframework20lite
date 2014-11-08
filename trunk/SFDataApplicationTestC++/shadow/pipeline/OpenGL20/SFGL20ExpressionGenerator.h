//
//  SFGL20ExpressionGenerator.h
//  
//
//  Created by Alessandro Martinelli on 20/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20ExpressionGenerator__
#define SFGL20ExpressionGenerator__

#include "../expression/SFExpressionElementInterpreter.h"
#include "../expression/SFExpressionElement.h"
//#include "../expression/SFExpressionVariable.h"
//#include "../expression/SFExpressionOperator.h"

#include <map>
#include <string>
#include <vector>
#include <sstream>
#include "../parameters/SFParameter.h"


using namespace std;

namespace sf{
class SFGL20ExpressionGenerator : public SFExpressionElementInterpreter{
    
private: 
	static SFGL20ExpressionGenerator* generator;

	string value;
	
    //do not delete this; are stored temporarly
    //here, when they come from 
    vector<SFExpressionElement*> parameters;
	
    static SFParameteri* refParameter;
	
	static map<string, string> renameMap;
	
	static map<string, string> functionsOperator;
	
	/*static{  note: only sample is different....
		functionsOperator.put("clamp","clamp");
		functionsOperator.put("cos","cos");
		functionsOperator.put("sin","sin");
		functionsOperator.put("dot","dot");
		functionsOperator.put("sqrt","sqrt");
		functionsOperator.put("sample","texture2D");
		functionsOperator.put("cross","cross");
		functionsOperator.put("normalize","normalize");
		functionsOperator.put("inversesqrt","inversesqrt");
		functionsOperator.put("pow","pow");
	}*/
	
public:
	/* return null if operator string is not a function operator*/
	static string getAsFunction(string op);
	
private:
	string getTypeWrapOpenstring(short wrappedType,short wrappingType);
    
	string getTypeWrapClosestring(short wrappedType,short wrappingType);
	
public:
	void closeElement(SFExpressionElement* element) ;
	
	void refreshElement(SFExpressionElement* element) ;
	
	void startElement(SFExpressionElement* element) ;
	
    
	static map<string, string> getRenameMap() ;
    
	static void setRenameMap(map<string, string> renameMap) ;
	
	static SFParameteri* getRefParameter() ;
    
	static void setRefParameter(SFParameteri* refParameter) ;
    
	static SFGL20ExpressionGenerator* getGenerator(SFParameteri* outputParameter);
	
	static string getFunctionstring();
};
}


#endif /* defined(SFGL20ExpressionGenerator__) */
