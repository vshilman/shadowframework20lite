/*
 * SFLibraryReferenceList.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro
 */

#ifndef SFLIBRARYREFERENCELIST_H_
#define SFLIBRARYREFERENCELIST_H_

#include "SFDataObject.h"
#include "SFLibraryReference.h"
#include <vector>
using namespace std;


namespace sf{
template <class T>
class SFLibraryReferenceList : public vector<SFLibraryReference*>,public SFDataObject{

	SFLibraryReference<T>* type;
public:
	SFLibraryReferenceList();
	virtual ~SFLibraryReferenceList();


	SFLibraryReferenceList(SFLibraryReference<T> type) {
		this->type = type;
	}

	void add(string reference){
		SFLibraryReference<T> libraryReference = type->clone();
		libraryReference.setReference(reference);
		add(libraryReference);
	}

	void readFromStream(SFInputStream* stream) {
		int n=stream->readShort();
		for (int i = 0; i < n; i++) {
			SFLibraryReference<T> reference=type->clone();
			add(reference);
			reference.readFromStream(stream);
		}
	}

	void writeOnStream(SFOutputStream* stream) {
//		stream.writeShort((short)(size()));
//		for (int i = 0; i < size(); i++) {
//			get(i).writeOnStream(stream);
//		}
	}

	SFLibraryReferenceList<T> clone(){
		return new SFLibraryReferenceList<T>(type);
	}
};
}

#endif /* SFLIBRARYREFERENCELIST_H_ */
