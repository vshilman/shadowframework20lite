/*
 * SFValuenfList.h
 *
 *  Created on: 23/set/2013
 *      Author: Alessandro
 */

#ifndef SFVALUENFLIST_H_
#define SFVALUENFLIST_H_

#include "shadow/system/SFIResource.h"
#include "shadow/math/SFValuenf.h"

namespace sf {

class SFValuenfList : public SFIResource {

	SFValuenf* values;
	short values_length;
	SFResource resource;

public:
	SFValuenfList();

	virtual ~SFValuenfList();

	SFValuenfList(int n) ;

	SFValuenf* getValues();

	int getValuesLength();

	SFResource* getResource();

};

} /* namespace sf */
#endif /* SFVALUENFLIST_H_ */
