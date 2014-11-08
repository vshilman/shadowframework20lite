
#include "SFExpressionElement.h"


SFExpressionElement::SFExpressionElement(string element) {
    this->element = element;
    this->type=0;
    this->rawElement=true;
}

SFExpressionElement::~SFExpressionElement(){
	//yes; expression frees its own elements
    for(unsigned int i=0;i<list.size();i++){
        delete list[i];
    }
}

string SFExpressionElement::getElement() {
    return element;
}

bool SFExpressionElement::isRawElement() {
    return this->rawElement;
}

SFExpressionElement* SFExpressionElement::getExpressionElement(int index) {
    return list[index];
}

void SFExpressionElement::setElement(string element) {
    this->element = element;
}

int SFExpressionElement::getElementSize(){
    return list.size();
}

void SFExpressionElement::addElement(SFExpressionElement* element){
    list.push_back(element);
}

vector<SFExpressionElement*> SFExpressionElement::getList() {
	return this->list;
}

short SFExpressionElement::getType() {
    return type;
}

void SFExpressionElement::setType(short type) {
    this->type = type;
}

void SFExpressionElement::setRawElement(bool rawElement) {
	this->rawElement = rawElement;
}

void SFExpressionElement::evaluateType(){

}

void SFExpressionElement::traverse(SFExpressionElementInterpreter* interpreter){
    interpreter->startElement(this);
    for(unsigned int i=0;i<list.size();i++){
        list[i]->traverse(interpreter);
        if(i<list.size()-1){
            interpreter->refreshElement(this);
        }
    }
    interpreter->closeElement(this);
}
