#ifndef SFGRIDMODEL_H
#define SFGRIDMODEL_H

#include <map>
#include <string>

using namespace std;

namespace sf{

int SFGridModel_TriangleGridSize(int n);

int SFGridModel_QuadGridSize(int n);

int SFGridModel_LineGridSize(int n);

class SFGridModel{

public:
    SFGridModel(){this->getGridSize=SFGridModel_TriangleGridSize;};

    SFGridModel(int (*getGridSize)(int));

    int (*getGridSize)(int);

    static SFGridModel valueOf(string name);
    
};

static const SFGridModel SFGridModel_Triangle(SFGridModel_TriangleGridSize);
static const SFGridModel SFGridModel_Quad(SFGridModel_QuadGridSize);
static const SFGridModel SFGridModel_Line(SFGridModel_LineGridSize);

}

#endif
