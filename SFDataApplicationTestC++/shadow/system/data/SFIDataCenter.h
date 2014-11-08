/*
 *  Created on: 06/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFIDATACENTER_H_
#define SFIDATACENTER_H_

#include "SFDataAsset.h"
#include <string>
using namespace std;


namespace sf{

class SFIDataCenter {

	public:

	virtual ~SFIDataCenter(){}

	virtual SFDataAsset<void*>* makeDatasetAvailable(string name)=0;
};
}



#endif /* SFIDATACENTER_H_ */
