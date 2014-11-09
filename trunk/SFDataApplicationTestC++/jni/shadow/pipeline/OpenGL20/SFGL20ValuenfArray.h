//
//  SFGL20ValuenfArray.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20ValuenfArray__
#define SFGL20ValuenfArray__

#include "SFValuesArray.h"
#include "SFValuenf.h"
#include "SFGL20ValuenfArray.h"
#include "SFGL20ListData.h"


namespace sf{
class SFGL20ValuenfArray :  public SFGL20ListData<SFValuenf>{
    
	int n;

	SFGL20ValuenfArray() {
		this->n = 1;
	}

public:
	SFGL20ValuenfArray(int n) {
		this->n = n;
	}

	~SFGL20ValuenfArray(){
		for(unsigned int i=0;i<data.size();i++){
			delete data[i]->getV();
		}
	}
    
	void assignValues(SFValuenf* writing, SFValuenf* reading){
		writing->set(reading);
	}
	
	SFValuenf* generateGenericElement() {
		float* f=new float[n];
		return new SFValuenf(f,n);
	}
    
};
}


#endif /* defined(SFGL20ValuenfArray__) */
