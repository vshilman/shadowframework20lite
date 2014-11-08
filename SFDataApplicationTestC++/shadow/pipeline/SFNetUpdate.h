/*
 * SFNetUpdate.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFNETUPDATE_H_
#define SFNETUPDATE_H_

namespace sf{

class SFNetPolygon;

class SFNetUpdate{

public:
	virtual ~SFNetUpdate(){};

	//cast polygon to SFNetPolygon
	virtual void init(SFNetPolygon* polygon)=0;
	//cast polygon to SFNetPolygon
	virtual void update(SFNetPolygon* polygon)=0;
};

}

#endif /* SFNETUPDATE_H_ */
