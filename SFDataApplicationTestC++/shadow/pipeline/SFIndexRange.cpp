#include "SFIndexRange.h"

namespace sf{

SFIndexRange::SFIndexRange(int position, int size) {
    this->position = position;
    this->size = size;
}

int SFIndexRange::getPosition() {
    return position;
}
void SFIndexRange::setPosition(int position) {
    this->position = position;
}
int SFIndexRange::getSize() {
    return size;
}
void SFIndexRange::setSize(int size) {
    this->size = size;
}

void SFIndexRange::insertIndex(int index){
    if(this->position==-1){
        this->position=index;
        this->size=1;
    }else{
        int last=position+size;
        if(index<position){
            position=index;
            size=last-position;
        }else if(index>=last){
            size=index-position+1;
        }
    }
}

}
