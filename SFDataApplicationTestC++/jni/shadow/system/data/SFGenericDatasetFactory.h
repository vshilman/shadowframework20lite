//
//  SFGenericDatasetFactory.h
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGenericDatasetFactory__
#define SFGenericDatasetFactory__

#include "SFAbstractDatasetFactory.h"
#include "SFInputStream.h"
#include "SFDataAsset.h"
#include <vector>
using namespace std;

namespace sf{
class SFGenericDatasetFactory :public SFAbstractDatasetFactory{
    
private:

	//vector<SFDataAsset*> assetsList;
	vector<SFDataAsset<void*>*> assetsList;
	
	SFGenericDatasetFactory();
    
    //This is responsibile for the destruction of all Dataset propotype in assetsList
    ~SFGenericDatasetFactory();
    
    //No responsibility on the destruction of sfasset, which will be cleaned by this factory
	template <class T>
	void addSFDataset(SFDataAsset<T>* sfasset);
	
    //The one calling this method will be responsibile to delete the Dataset
	SFDataset* readDataset(SFInputStream* stream);
	

	void writeDataset(SFOutputStream* stream,SFDataset* dataset){
		//Do nothing. no need of write..
	}

	template <class T>
	SFDataAsset<T>* createDataset(int index);
};
}



#endif /* defined(SFGenericDatasetFactory__) */
