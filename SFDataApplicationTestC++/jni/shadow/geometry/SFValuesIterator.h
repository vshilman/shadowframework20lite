/*
 * SFValuesIterator.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFVALUESITERATOR_H_
#define SFVALUESITERATOR_H_

#include "shadow/math/SFValuenf.h"

namespace sf {

class SFValuesIterator {
public:
	virtual ~SFValuesIterator();

	virtual bool hasNext()=0;

	virtual void getNext(SFValuenf* write)=0;

};

} /* namespace sf */
#endif /* SFVALUESITERATOR_H_ */
