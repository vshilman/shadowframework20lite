#ifndef SFMESH_H
#define SFMESH_H

#include "SFIndexRange.h"
#include "SFPrimitiveArray.h"
#include "SFPipeline.h"
#include "../system/SFInitiable.h"

namespace sf{

class SFMesh: public SFInitiable{

private:
    SFPrimitive* primitive;
    SFPrimitiveArray* array;
    int firstElement;
    int lastElement;
    SFIndexRange* ranges;

public:
    SFMesh(SFPrimitive* primitive=0);

    SFMesh(SFPrimitive* primitive,SFPrimitiveArray* array);

    ~SFMesh();

    SFPrimitiveArray* getArray();

    SFPrimitive* getPrimitive();

    void destroy();

    void init();

    int getFirstElement();

    void setFirstElement(int firstElement);

    int getLastElement();

    void setLastElement(int lastElement);

    int getSize();

    SFIndexRange* getPrimitiveDataRanges();

};

}

#endif // SFMESH_H
