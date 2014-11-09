/*
 * SFDataObject.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFDATAOBJECT_H_
#define SFDATAOBJECT_H_

#include "SFInputStream.h"
#include "SFOutputStream.h"

namespace sf{
class SFDataObject{

public:
	virtual ~SFDataObject(){

	}

	virtual void readFromStream(SFInputStream* stream)=0;
	/** write the content of this dataset
	 */
	//virtual void writeOnStream(SFOutputStream* stream)=0;

	virtual SFDataObject* clone()=0;

};
}


#endif /* SFDATAOBJECT_H_ */
