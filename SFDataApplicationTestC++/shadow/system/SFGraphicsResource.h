/*
 * SFGraphicsResource.h
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#ifndef SFGRAPHICSRESOURCE_H_
#define SFGRAPHICSRESOURCE_H_

#include "SFInitiable.h"
#include "SFIResource.h"

namespace sf {

class SFGraphicsResource : public SFIResource,public SFInitiable{

public:
	virtual ~SFGraphicsResource(){};
};

} /* namespace sf */
#endif /* SFGRAPHICSRESOURCE_H_ */
