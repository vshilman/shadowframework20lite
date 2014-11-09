#include "SFInitiator.h"


namespace sf{

SFInitiator::SFInitiator() {

}

SFInitiator* SFInitiator::initiator=new SFInitiator();


void SFInitiator::addInitiable(SFInitiable* initiable) {
    initiator->initiables.push_back(initiable);
}

void SFInitiator::addDestroyable(SFInitiable* initiable) {
    initiator->destroyables.push_back(initiable);
}

void SFInitiator::solveInitiables() {
    vector<SFInitiable*>::iterator dIterator;
    for(dIterator = initiator->destroyables.begin();
        dIterator != initiator->destroyables.end();dIterator++){
        (*dIterator)->destroy();
    }
    initiator->destroyables.clear();
    vector<SFInitiable*>::iterator iIterator;
    for(iIterator = initiator->initiables.begin();
        iIterator != initiator->initiables.end();iIterator++){
        (*iIterator)->init();
    }
    initiator->initiables.clear();
}

}
