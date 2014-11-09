/*
 * SFValuesList.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFVALUESLIST_H_
#define SFVALUESLIST_H_

#include "shadow/system/SFGraphicsResource.h"
#include "shadow/math/SFValuenf.h"
#include "SFValuesIterator.h"

namespace sf {

class SFValuesList : virtual SFGraphicsResource {
public:

	virtual ~SFValuesList();

	virtual int getSize()=0;

	virtual void getValue(int index,SFValuenf* write)=0;

	virtual void setValue(int index,SFValuenf* read)=0;

	virtual int addValue(SFValuenf* write)=0;

	virtual int getValueSize()=0;

	virtual SFValuesIterator* getIterator()=0;

};

} /* namespace sf */
#endif /* SFVALUESLIST_H_ */
