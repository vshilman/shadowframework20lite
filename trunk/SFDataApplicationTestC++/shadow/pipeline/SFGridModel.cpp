
#include "SFGridModel.h"

namespace sf{
int SFGridModel_TriangleGridSize(int n){
    return ((n+1)*n)>>1;
}

int SFGridModel_QuadGridSize(int n){
    return n*n;
}

int SFGridModel_LineGridSize(int n){
    return n;
}

SFGridModel::SFGridModel(int (*getGridSize)(int)){
    this->getGridSize=getGridSize;
}

map<string,SFGridModel> blocks;

SFGridModel SFGridModel::valueOf(string name){
    if(blocks.size()==0){
        blocks["Triangle"]=SFGridModel_Quad;
        blocks["Quad"]=SFGridModel_Triangle;
        blocks["Line"]=SFGridModel_Line;
    }
    return blocks[name];
}

}
