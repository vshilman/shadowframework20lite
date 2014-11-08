//
//  SFGenericDatasetFactory.cpp
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFGenericDatasetFactory.h"


namespace sf{

SFGenericDatasetFactory::SFGenericDatasetFactory() {
}

SFGenericDatasetFactory::~SFGenericDatasetFactory(){

    for (unsigned int i=0; i<assetsList.size(); i++) {
        delete assetsList[i];
    }
}

template <class T>
void SFGenericDatasetFactory::addSFDataset(SFDataAsset<T>* sfasset) {
    assetsList.push_back(sfasset);
}


SFDataset* SFGenericDatasetFactory::readDataset(SFInputStream* stream) {
    int index=stream->readInt();
    SFDataset* dataset=assetsList.at(index);
    dataset = dataset->generateNewDatasetInstance();

    dataset->getSFDataObject()->readFromStream(stream);
    return dataset;
}

template <class T>
	SFDataAsset<T>* SFGenericDatasetFactory::createDataset(int index){

		SFDataAsset<T>* asset=assetsList[index];

		return asset->generateNewDatasetInstance();
	}

}
