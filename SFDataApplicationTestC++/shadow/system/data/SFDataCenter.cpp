//
//  SFDataCenter.cpp
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFDataCenter.h"


namespace sf{
SFDataCenter SFDataCenter::dataCenter;


SFDataCenter::SFDataCenter(){
	dataCenterImplementation=0;
	datasetFactory=0;
}

void SFDataCenter::setDataCenterImplementation(SFIDataCenter* dataCenterImplementation) {
    dataCenter.dataCenterImplementation = dataCenterImplementation;
}

void SFDataCenter::setDatasetFactory(SFAbstractDatasetFactory* datasetFactory) {
    dataCenter.datasetFactory = datasetFactory;
}

SFDataCenter* SFDataCenter::getDataCenter() {
    return &dataCenter;
}

SFIDataCenter* SFDataCenter::getDataCenterImplementation() {
    return dataCenterImplementation;
}

SFDataset* SFDataCenter::readDataset(SFInputStream* stream) {
    SFDataset* dataset=dataCenter.datasetFactory->readDataset(stream);
    //TODO DELETE: this datasets are never deleted!
    return dataset;
}

SFDataAsset<void*>* SFDataCenter::makeDatasetAvailable(string name){
	SFDataAsset<void*>* asset=dataCenterImplementation->makeDatasetAvailable(name);
	return  asset;
}


void SFDataCenter::writeDataset(SFOutputStream* stream, SFDataset* dataset) {
	dataCenter.datasetFactory->writeDataset(stream, dataset);
}

SFDataAsset<void*>* SFDataCenter::createDataset(int type){
	return dataCenter.datasetFactory->createDataset(type);
}

//SFDataAsset* SFDataCentermakeDatasetAvailable(string name,SFAssetListener* listener){
//	return dataCenterImplementation->makeDatasetAvailable(object,memoryPointer);
//}
//SFDataAsset* SFDataCenter::makeDatasetAvailable(string object,SFAssetListener* memoryPointer){
//	return dataCenterImplementation->makeDatasetAvailable(object,memoryPointer);
//}
//void SFDataCenter::makeDatasetAvailable(string object,SFDataCenterListener<SFDataset>* memoryPointer){
//    dataCenterImplementation->makeDatasetAvailable(object, memoryPointer);
//}

//SFDataset* SFDataCenter::getAlreadyAvailableDataset(string object){
//    SFDataset** dataset=new SFDataset*();//destroyed within this method
//    SFInstantDataCenterListener listener(dataset);
//    getDataCenter().makeDatasetAvailable(object,&listener);
//    SFDataset* tmp=*dataset;
//    delete dataset;
//    return tmp;
//}

}
