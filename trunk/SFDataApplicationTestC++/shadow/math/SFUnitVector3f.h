#ifndef SFUNITVECTOR3F_H
#define SFUNITVECTOR3F_H

#include "SFVertex2f.h"
#include "SFVertex3f.h"

namespace sf{

class SFUnitVector3f:public SFVertex2f{

public:
    SFUnitVector3f();

    void getVertex3f(SFVertex3f* write);

    void setVertex3f(SFVertex3f* read);

};
}

#endif // SFUNITVECTOR3F_H
