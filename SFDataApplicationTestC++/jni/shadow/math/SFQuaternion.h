#ifndef SFQUATERNION_H
#define SFQUATERNION_H

#include "SFVertex4f.h"
#include "SFMatrix3f.h"


namespace sf{

class SFQuaternion:public SFVertex4f{

public:

    SFQuaternion();

    SFQuaternion(SFVertex3f direction,double angle);

    void multTo(SFQuaternion q);

    SFVertex3f* applyRotation(SFVertex3f a);

    SFMatrix3f* getRotationMatrix();
};

}

#endif // SFQUATERNION_H
