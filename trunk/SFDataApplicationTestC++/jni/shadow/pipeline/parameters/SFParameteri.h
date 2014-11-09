#ifndef SFPARAMETERI_H
#define SFPARAMETERI_H

#include <string>
#include "SFValuenf.h"
#include "SFValue1f.h"
#include "SFVertex2f.h"
#include "SFVertex3f.h"
#include "SFVertex4f.h"
#include "SFMatrix2f.h"
#include "SFMatrix3f.h"
#include "SFMatrix4f.h"

using namespace std;

namespace sf{

class SFParameteri{

public:
    static const short GLOBAL_GENERIC = 100;
    static const short GLOBAL_FLOAT = 0;
    static const short GLOBAL_FLOAT2 = 1;
    static const short GLOBAL_FLOAT3 = 2;
    static const short GLOBAL_FLOAT4 = 3;
    static const short GLOBAL_MATRIX2 = 4;
    static const short GLOBAL_MATRIX3 = 5;
    static const short GLOBAL_MATRIX4 = 6;
    static const short GLOBAL_TEXTURE = 9;

    virtual ~SFParameteri(){};

    virtual string getName()=0;
    virtual short getType()=0;

    //static SFValue* generateValue(SFParameteri* sfParameteri);

    static SFValue* generateValue(short type);

    static int getExpressionDimension(short type);

    static int getTypeDimension(short type);
};

}

#endif // SFPARAMETERI_H
