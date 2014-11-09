
#include "SFBufferData.h"


namespace sf{

SFBufferData::SFBufferData(int width, int height, SFImageFormat format) {
    this->width = width;
    this->height = height;
    this->format = format;
}

int SFBufferData::getWidth() {
    return width;
}

int SFBufferData::getHeight() {
    return height;
}

SFImageFormat SFBufferData::getFormat() {
    return format;
}

}
