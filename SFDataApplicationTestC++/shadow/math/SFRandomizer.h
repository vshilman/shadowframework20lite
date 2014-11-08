#ifndef SFRANDOMIZER_H
#define SFRANDOMIZER_H

namespace sf{

class SFRandomizer {

private:
    int seed, value;

public:
    SFRandomizer();

    SFRandomizer(int seed);

    void setSeed(int seed);

    int randomInt();

    float randomFloat();

    void reset();

};

}

#endif // SFRANDOMIZER_H
