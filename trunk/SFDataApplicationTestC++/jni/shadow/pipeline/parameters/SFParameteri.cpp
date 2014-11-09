#include "SFParameteri.h"


namespace sf{
//SFValue* SFParameteri::generateValue(SFParameteri* sfParameteri){
//    short type=sfParameteri->getType();
//    return generateValue(type);
//}

//NOTE: this is generating values, you know..
SFValue* SFParameteri::generateValue(short type) {
    switch(type){
        //case GLOBAL_UNIDENTIFIED: TODO: what's happening to GLOBAL_UNIDENTIFIED??
        case GLOBAL_FLOAT: return new SFValue1f(0.0);
        case GLOBAL_FLOAT2: return new SFVertex2f(0.0,0.0);
        case GLOBAL_FLOAT3: return new SFVertex3f(0.0,0.0,0.0);
        case GLOBAL_FLOAT4: return new SFVertex4f(0.0,0.0,0.0,0.0);
        case GLOBAL_MATRIX2: return new SFMatrix2f();
        case GLOBAL_MATRIX3: return new SFMatrix3f();
        case GLOBAL_MATRIX4: return new SFMatrix4f();
        case GLOBAL_TEXTURE: return new SFValue1f(0.0); //TODO is Global_TEXTURE going to be correct this way?
    }
    return 0;
}


int SFParameteri::getExpressionDimension(short type){
    switch(type){
        case GLOBAL_GENERIC: return 0;
        case GLOBAL_FLOAT: return 1;
        case GLOBAL_FLOAT2: return 2;
        case GLOBAL_FLOAT3: return 3;
        case GLOBAL_FLOAT4: return 4;
        case GLOBAL_MATRIX2: return 2;
        case GLOBAL_MATRIX3: return 3;
        case GLOBAL_MATRIX4: return 4;
        case GLOBAL_TEXTURE: return 1;
    }
    return 0;
}


int SFParameteri::getTypeDimension(short type){
    switch(type){
        case GLOBAL_GENERIC: return 0;
        case GLOBAL_FLOAT: return 1;
        case GLOBAL_FLOAT2: return 2;
        case GLOBAL_FLOAT3: return 3;
        case GLOBAL_FLOAT4: return 4;
        case GLOBAL_MATRIX2: return 4;
        case GLOBAL_MATRIX3: return 9;
        case GLOBAL_MATRIX4: return 16;
        case GLOBAL_TEXTURE: return 1;
    }
    return 0;
}
}
