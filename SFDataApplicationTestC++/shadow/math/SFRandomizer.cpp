#include "SFRandomizer.h"

#define a 40
#define b 1000000
#define size b+1
#define beginTimes 4
#define step 0.0000001


namespace sf{

SFRandomizer::SFRandomizer() {
    //step=1.0/b;
    this->value=0;
    this->seed = 0;
}

SFRandomizer::SFRandomizer(int seed) {
    //step=1.0/b;
    this->value=0;
    this->seed = seed % size;
    reset();
    for (int i = 0; i < beginTimes; i++) {
        randomInt();
    }
}

void SFRandomizer::setSeed(int seed){
	this->seed=seed;
}

int SFRandomizer::randomInt() {
    //Linear congruential generator
    value = (a * value + b) % size;
    return value;
}

float SFRandomizer::randomFloat() {
    return randomInt() * step;
}

void SFRandomizer::reset() {
    value = seed;
}

}
