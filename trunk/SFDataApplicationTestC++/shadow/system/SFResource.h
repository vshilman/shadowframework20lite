/*
 * SFResource.h
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#ifndef SFRESOURCE_H_
#define SFRESOURCE_H_

#include <algorithm>
#include <vector>
using namespace std;

#include "SFAsset.h"
#include "SFUpdatable.h"
#include "SFUpdater.h"

namespace sf {

class SFResource {

	vector<SFResource*> usedResources;
	vector<SFResource*> usingResources;
	SFAsset<void*>* asset;
	SFUpdatable* updatable;

public:

	SFResource(int size,SFUpdatable* updatable=0);

	SFResource();

	void setAsset(SFAsset<void*>* asset);

	void resourceChanged();

	void checkIndex(unsigned int index);

	SFResource* getResource(int index);

	void setResource(int index,SFResource* resource);

	void free(SFResource* res);

	void lock(SFResource* resource);

};

} /* namespace sf */
#endif /* SFRESOURCE_H_ */
