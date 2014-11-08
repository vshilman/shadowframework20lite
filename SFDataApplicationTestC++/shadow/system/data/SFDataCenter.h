//
//  SFDataCenter.h
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFDataCenter__
#define SFDataCenter__

#include "SFIDataCenter.h"
#include "SFDataAsset.h"
#include "SFAbstractDatasetFactory.h"


namespace sf{
class SFDataCenter : public SFIDataCenter, SFAbstractDatasetFactory{
    
private:
    static SFDataCenter dataCenter;
	
    SFIDataCenter* dataCenterImplementation;
	
    SFAbstractDatasetFactory* datasetFactory;
    
	SFDataCenter();
	
public:
	static void setDataCenterImplementation(SFIDataCenter* dataCenterImplementation);
	
	static void setDatasetFactory(SFAbstractDatasetFactory* datasetFactory);
	
	static SFDataCenter* getDataCenter();
    
	SFIDataCenter* getDataCenterImplementation();

	SFDataAsset<void*>* makeDatasetAvailable(string name);

	SFDataset* getAlreadyAvailableDataset(string object);
	
	SFDataset* readDataset(SFInputStream* stream);
	
	void writeDataset(SFOutputStream* stream, SFDataset* dataset);

	SFDataAsset<void*>* createDataset(int type);

};

}


#endif /* defined(SFDataCenter__) */
