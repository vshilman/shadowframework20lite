//
//  SFGL20ImageObject.h
//  
//
//  Created by Alessandro Martinelli on 22/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFGL20ImageObject_H
#define SFGL20ImageObject_H

namespace sf{

class SFGL20ImageObject {
    
public:
	virtual ~SFGL20ImageObject(){};

	virtual void build()=0;
    
	virtual void destroy()=0;
    
};

}

#endif
