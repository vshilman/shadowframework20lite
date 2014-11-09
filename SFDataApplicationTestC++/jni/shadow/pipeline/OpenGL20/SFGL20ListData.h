//
//  SFGL20ListData.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20ListData__
#define SFGL20ListData__

#include "../../system/SFArray.h"
#include <vector>

using namespace std;


namespace sf{
template <class E>
class SFGL20ListData: public SFArray<E>{
    
public:
    //TODO: i would rather avoid using vectors here.
	vector<E*> data;
	
	virtual ~SFGL20ListData(){};

	virtual E* generateGenericElement()=0;
	virtual void assignValues(E* writing,E* reading)=0;
	
	void eraseElements(int index, int elementsCount){
		for (int i = 0; i < elementsCount; i++) {
			data.erase(data.begin()+index);
		}
	}
	
	E* generateSample() {
		return generateGenericElement();
	}
	
	//Used only by implementation
	E* getValue(int index){
		return data.at(index);
	}
	
	int generateElement() {
		E* e=generateGenericElement();
		data.push_back(e);
		return data.size()-1;
	}
	
	
	int generateElements(int count) {
		int index=data.size();
		for (int i = 0; i < count; i++) {
			data.push_back(generateGenericElement());
		}
		return index;
	}
	
	void getElement(int index, E* element){
		assignValues(element, data.at(index));
	}
	
	
	int getElementsCount() {
		return data.size();
	}
	
	void setElement(int index, E* element){
		assignValues(data.at(index),element);
	}
	
};
}

#endif /* defined(SFGL20ListData__) */
