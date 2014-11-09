/*
 * SFCompositeDataCenter.h
 *
 *  Created on: 12/set/2013
 *      Author: Alessandro
 */

#ifndef SFCOMPOSITEDATACENTER_H_
#define SFCOMPOSITEDATACENTER_H_

#include "SFIDataCenter.h"
#include "SFDataAsset.h"
#include <map>
#include <string>
#include <utility>
using namespace std;

namespace sf {

class SFCompositeDataCenter : public SFIDataCenter{

	map<string,SFIDataCenter*> dataCenters;

public:

	SFCompositeDataCenter(){

	}

	~SFCompositeDataCenter(){

	}

	SFDataAsset<void*>* makeDatasetAvailable(string name) {

		int indexOf=name.find_last_of('.');
		if(indexOf>=0){
			string libraryName=name.substr(0,indexOf);
			string dataAssetName=name.substr(indexOf+1,name.size()-(indexOf+1));

			SFIDataCenter* dataCenter=dataCenters.at(libraryName);
			if(dataCenter!=0){
				SFDataAsset<void*>* dataAsset=dataCenter->makeDatasetAvailable(dataAssetName);
				if(dataAsset!=0){
					return dataAsset;
				}
			}
		}

		map<string,SFIDataCenter*>::iterator it;
		for (it=dataCenters.begin(); it!=dataCenters.end(); it++){
			SFDataAsset<void*>* dataAsset=it->second->makeDatasetAvailable(name);
			if(dataAsset!=0){
				return dataAsset;
			}
		}
		  //  std::cout << it->first << " => " << it->second << '\n';

		return 0;
	}

	map<string,SFIDataCenter*>* getDataCenters() {
		return &dataCenters;
	}

	void add(string name,SFIDataCenter* dataCenter){
		dataCenters[name]=dataCenter;
	}

};


} /* namespace sf */
#endif /* SFCOMPOSITEDATACENTER_H_ */
