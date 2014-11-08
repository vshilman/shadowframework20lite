/*
 * SFDataObjectsList.h
 *
 *  Created on: 06/giu/2013
 *      Author: Alessandro Martinelli
 */

#ifndef SFDATAOBJECTSLIST_H_
#define SFDATAOBJECTSLIST_H_

#include <vector>
using namespace std;


namespace sf{
template <class T>
class SFDataObjectsList :public vector<T>, public SFDataObject{

	T* type;

public:
	virtual ~SFDataObjectsList(){
		delete type;
	}

	SFDataObjectsList(T* type) {
		super();
		this->type = type;
	}

	void readFromStream(SFInputStream* stream) {
		int n=stream->readShort();
		for (int i = 0; i < n; i++) {
			add((T)type->clone());
			get(i).readFromStream(stream);
		}
	}

	void writeOnStream(SFOutputStream* stream) {
		stream->writeShort((short)(size()));
		for (int i = 0; i < size(); i++) {
			get(i).writeOnStream(stream);
		}
	}

	SFDataObjectsList<T> clone(){
		return new SFDataObjectsList<T>(type);
	}

	 int getSize() {
		return size();
	}
};
}

#endif /* SFDATAOBJECTSLIST_H_ */
