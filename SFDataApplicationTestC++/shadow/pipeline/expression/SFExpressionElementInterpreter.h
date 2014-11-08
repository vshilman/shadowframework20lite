
#ifndef SFExpressionElementInterpreter_H
#define SFExpressionElementInterpreter_H

class SFExpressionElement;

class SFExpressionElementInterpreter {
public:
	virtual ~SFExpressionElementInterpreter(){};
	virtual void startElement(SFExpressionElement* element)=0;
	virtual void refreshElement(SFExpressionElement* element)=0;
	virtual void closeElement(SFExpressionElement* element)=0;
};


#endif
