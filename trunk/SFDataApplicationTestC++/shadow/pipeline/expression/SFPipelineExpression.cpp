/*
 * SFPipelineExpression.cpp
 *
 *  Created on: 13/set/2013
 *      Author: Alessandro
 */

#include "SFPipelineExpression.h"

namespace sf {


//int SFPipelineExpression::SFPipelineExpression__AA = 0;

SFPipelineExpression::SFPipelineExpression() {
	string tmp("");
	element=new SFExpressionElement(tmp);
}

SFPipelineExpression::~SFPipelineExpression() {
	// TODO Auto-generated destructor stub
}

} /* namespace sf */
