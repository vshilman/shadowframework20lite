

#ifndef SFIMAGEFORMAT_H
#define SFIMAGEFORMAT_H

namespace sf{

enum SFImageFormat {
    
	GRAY8,//8 bit
	RGB8,//24 bit
	RGB565,//16 bit
	ARGB8,//32 bit
	ARGB4,//16 bit
	DEPTH8,//8 bit
	DEPTH16,//16 bit
	STENCIL8//8 bit
};
}

#endif
