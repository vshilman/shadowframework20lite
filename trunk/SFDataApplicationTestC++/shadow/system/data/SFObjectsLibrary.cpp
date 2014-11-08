//
//  SFObjectsLibrary.cpp
//  
//
//  Created by Alessandro Martinelli on 19/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFObjectsLibrary.h"


namespace sf{
/*RecordData::RecordData(string name, SFDataset* dataset) {
    this->name = name;
    this->dataset = dataset;
}

string RecordData::getName() {
    return name;
}

SFDataset* RecordData::getDataset() {
    return dataset;
}*/


SFLibraryRecord::SFLibraryRecord(string name,SFDataset* dataset) {
    this->name.setString(name);
    this->object.setDataset(dataset);
}


void SFLibraryRecord::generateData() {
    getDataObject()->push_back(&name);
    getDataObject()->push_back(&object);
}


int SFLibraryRecord::compareTo(SFLibraryRecord o) {
    return name.getString().compare(o.name.getString());
}


SFLibraryRecord* SFLibraryRecord::clone() {
    return new SFLibraryRecord(name.getString(), object.getDataset());
}


SFObjectsLibrary::SFObjectsLibrary(){
    records=new SFDataList<SFLibraryRecord>(new SFLibraryRecord("0",0));
}

SFObjectsLibrary::~SFObjectsLibrary(){
    delete records;
}


/**
 * Look for the {@link Dataset} registered with the given name
 * @param name the name of the {@link Dataset}
 * @return
 */
SFDataset* SFObjectsLibrary::retrieveDataset(string name){
    int index=0;
    //SLow search, TO be Optimized
    for (int i=0; i<records->size(); i++) {
        if(records->get(i)->name.getString().compare(name)==0){
            index=i;
            i=records->size();
        }
    }
    if(index<0)
        return 0;
    return records->get(index)->object.getDataset();
}

void SFObjectsLibrary::addLibrary(SFObjectsLibrary* library){
    for (int i=0; i<library->records->size(); i++) {
        this->records->add(library->get(i));
    }
}

SFLibraryRecord* SFObjectsLibrary::get(int index){
    return records->get(index);
}

int SFObjectsLibrary::size(){
    return records->getDataObject().size();
}

SFObjectsLibrary* SFObjectsLibrary::generateNewDatasetInstance() {
    return new SFObjectsLibrary();
}

void SFObjectsLibrary::invalidate() {
}


string SFObjectsLibrary::getType(){
    return "SFObjectsLibrary";
}

SFDataList<SFLibraryRecord>* SFObjectsLibrary::getSFDataObject(){
    return records;
}

}
