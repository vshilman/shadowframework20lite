
#ifndef SFBUFFERDATA_H
#define SFBUFFERDATA_H

#include "SFImageFormat.h"

namespace sf{

class SFBufferData {

private:
    int width,height;
	SFImageFormat format;

public:
	SFBufferData() ;
    
	SFBufferData(int width, int height, SFImageFormat format) ;
    
	int getWidth();
    
	int getHeight();
    
	SFImageFormat getFormat();//added comment
	
};

}

#endif /* SFBUFFERDATA_H */
