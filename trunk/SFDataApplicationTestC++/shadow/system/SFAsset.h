/*
 * SFAsset.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFASSET_H
#define SFASSET_H

namespace sf{

template <class T>
class SFAsset{

public:

	virtual ~SFAsset()=0;

	virtual T* getResource()=0;

	virtual void disposeResource()=0;

};
}
#endif /* SFASSET_H */
