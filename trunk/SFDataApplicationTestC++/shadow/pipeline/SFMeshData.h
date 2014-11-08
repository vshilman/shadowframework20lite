#ifndef SFMESHDATA_H
#define SFMESHDATA_H

#include "SFPrimitiveArray.h"
#include "SFPipeline.h"
#include "../system/SFValuesArray.h"

namespace sf{

class SFMeshData {

private:
    SFPrimitiveArray* array;
    int firstElement;
    int lastElement;
    short* indices;
    int gridIndex;

public:
    SFMeshData(SFPrimitiveArray* array);

    ~SFMeshData();
    
    SFMeshData(SFPrimitiveArray* array, int firstElement, int lastElement,
            int dataIndex);

    SFMeshData(SFPrimitiveArray* array, short* indices, int gridIndex);

    int getGridN();

    SFPrimitive* getPrimitive();

    SFValuenf* generateSample();

    int getIndex(int index);

    void setElement(int index,SFValuenf* value);

    void getElement(int index,SFValuenf* value);

    SFArray<SFValuenf>* getArrayData();

    SFPrimitiveArray* getArray();

    void setArray(SFPrimitiveArray* array) ;

    int getFirstElement();

    void setFirstElement(int firstElement) ;

    int getLastElement();

    void setLastElement(int lastElement);

    int getGridIndex();

    void setGridIndex(int gridIndex);

    void setIndices(short* indices);

    int getSize();
};

}

#endif // SFMESHDATA_H
