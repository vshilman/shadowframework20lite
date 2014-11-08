/*
 * SFPipelineExpression.h
 *
 *  Created on: 13/set/2013
 *      Author: Alessandro
 */

#ifndef SFPIPELINEEXPRESSION_H_
#define SFPIPELINEEXPRESSION_H_

#include "../../system/data/SFDataObject.h"
#include "../expression/SFExpressionElement.h"

#include <iostream>
using namespace std;

namespace sf {


class SFPipelineExpression : public SFDataObject{

	//static int SFPipelineExpression__AA ;

	SFExpressionElement* element;

public:
	SFPipelineExpression();
	virtual ~SFPipelineExpression();

	SFDataObject* clone() {
		return new SFPipelineExpression();
	}

	void readFromStream(SFInputStream *stream) {
		//cout << "Trying to read expression "<<element;
		readFromStream(element, stream);
	}

	void writeOnStream(SFOutputStream* stream) {
		writeOnStream(element, stream);
	}


	void readFromStream(SFExpressionElement* element,SFInputStream* stream) {

		cout << "Trying to read expression " <<endl;

		string name=stream->readString();
		cout << name << " ";
		char type=stream->readByte();
		cout << (int)type << " ";
		bool raw=stream->readByte()==1;
		cout << (int)raw << " ";
		char n=stream->readByte();
		cout << (int)n << " ";


		for (int i = 0; i < n; i++) {
			cout << "creating element "<< i << " "<<endl;
			SFExpressionElement* elementNew=new SFExpressionElement("");
			readFromStream(elementNew,stream);
			element->getList().push_back(elementNew);
			//SFPipelineExpression__AA++;
			//if(SFPipelineExpression__AA>4)
			//	i=n;
		}
//
//		element->setElement(name);
//		element->setType(type);
//		element->setRawElement(raw);
	}

	void writeOnStream(SFExpressionElement* element,SFOutputStream* stream) {

		stream->writeString(element->getElement());
		stream->writeByte(element->getType());
		stream->writeByte(element->isRawElement()?1:0);
		stream->writeByte(element->getList().size());

		for (unsigned int i = 0; i < element->getList().size(); i++) {
			writeOnStream(element->getList().at(i),stream);
		}
	}

	SFExpressionElement* getElement() {
		return element;
	}

	void setElement(SFExpressionElement* element) {
		this->element = element;
	}
};

} /* namespace sf */
#endif /* SFPIPELINEEXPRESSION_H_ */
