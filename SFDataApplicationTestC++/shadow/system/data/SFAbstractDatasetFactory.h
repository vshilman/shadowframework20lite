
#ifndef SFAbstractDatasetFactory_H
#define SFAbstractDatasetFactory_H

#include "SFInputStream.h"
#include "SFOutputStream.h"
#include "SFDataset.h"
#include "SFDataAsset.h"
#include <string>
using namespace std;

namespace sf{
class SFAbstractDatasetFactory {

public:
	virtual ~SFAbstractDatasetFactory(){

	}
	virtual void writeDataset(SFOutputStream* stream,SFDataset* dataset)=0;
	virtual SFDataset* readDataset(SFInputStream* stream)=0;

	virtual SFDataAsset<void*>* createDataset(int index)=0;
};
}
#endif
