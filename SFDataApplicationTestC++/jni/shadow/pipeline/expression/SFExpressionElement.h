
#ifndef SFExpressionElement_H
#define SFExpressionElement_H

#include <vector>
#include <string>

#include "../parameters/SFParameter.h"

#include "SFExpressionElementInterpreter.h"

//#include "SFExpressionValuesList.h"

using namespace std;

class SFExpressionElement {
	
private:
	string element;
	short type;//SFParameter.type.
	bool rawElement;
    vector<SFExpressionElement*> list;
    
public:
    
	SFExpressionElement(string element);
    
    virtual ~SFExpressionElement();
    
	string getElement();
	
	bool isRawElement();

	SFExpressionElement* getExpressionElement(int index);

	void setElement(string element);
    
	int getElementSize();
	
	void addElement(SFExpressionElement* element);
	
	vector<SFExpressionElement*> getList();

	short getType();
    
	void setType(short type);
	
	virtual void evaluateType();

	void setRawElement(bool rawElement);

	//void SFExpressionElement::evaluateType();

	void traverse(SFExpressionElementInterpreter* interpreter);
	
	//virtual void addSubExpression(SFExpressionElement* element)=0;
	
	//virtual SFValuenf evaluate(SFExpressionValuesList* values)=0;
    
	//virtual SFExpressionElement* cloneAsIndexed(vector<SFParameter*> toBeIndexed)=0;
};

#endif /* defined(SFExpressionElement__) */
