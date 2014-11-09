//
//  SFObjectsLibrary.h
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFObjectsLibrary__
#define SFObjectsLibrary__


#include "SFDataset.h"
#include "objects/SFString.h"
#include "objects/SFCompositeDataArray.h"
#include "objects/SFDataList.h"
#include "objects/SFDatasetObject.h"
#include <string>

using namespace std;


namespace sf{
/**
 * A basic structure containing a Library Record
 * @author Alessandro Martinelli
 */
/*class RecordData{
    string name;
    SFDataset* dataset;
    
public:
    
    RecordData(string name, SFDataset* dataset);
    
    string getName();
    
    SFDataset* getDataset();
};*/


class SFLibraryRecord : public SFCompositeDataArray{
    
public:
    SFString name;
    SFDatasetObject<SFDataset> object;
    
    SFLibraryRecord(string name,SFDataset* dataset);
    
    void generateData();
    
    int compareTo(SFLibraryRecord o);
    
    SFLibraryRecord* clone();
};




class SFObjectsLibrary :public SFDataset{
	
    
    SFObjectsLibrary();
    
public:
    SFDataList<SFLibraryRecord>* records;
    
    ~SFObjectsLibrary();
    
	/**
	 * Look for the {@link Dataset} registered with the given name
	 * @param name the name of the {@link Dataset}
	 * @return
	 */
	SFDataset* retrieveDataset(string name);
	
	void addLibrary(SFObjectsLibrary* library);
    
    SFLibraryRecord* get(int index);
	
	int size();
	
	SFObjectsLibrary* generateNewDatasetInstance();
	
	void invalidate();
    
    string getType();
	
	SFDataList<SFLibraryRecord>* getSFDataObject();
	
};


}

#endif /* defined(SFObjectsLibrary__) */
