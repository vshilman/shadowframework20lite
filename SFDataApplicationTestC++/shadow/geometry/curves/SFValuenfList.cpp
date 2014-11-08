/*
 * SFValuenfList.cpp
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#include "shadow/geometry/curves/SFValuenfList.h"

namespace sf {


SFValuenfList::~SFValuenfList(){
	for(int i=0;i<values_length;i++){
		delete[] values[i].getV();
	}
	delete[] values;
}

SFValuenfList::SFValuenfList(int n) {
	values=new SFValuenf[n];
	values_length=n;
}

SFValuenf* SFValuenfList::getValues() {
	return values;
}

int SFValuenfList::getValuesLength() {
	return values_length;
}

SFResource* SFValuenfList::getResource() {
	return &resource;
}

} /* namespace sf */
