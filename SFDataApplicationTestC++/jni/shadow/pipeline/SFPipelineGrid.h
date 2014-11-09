
#ifndef SFPipelineGrid__
#define SFPipelineGrid__

#include <vector>
#include "SFGridModel.h"
#include "SFFunction.h"

using namespace std;


namespace sf{

class SFPipelineGrid {
	
private:
    int n;
	SFGridModel model;
	SFParameteri** params;
	int paramsLength;
    
public:
	SFPipelineGrid(int n,SFGridModel model,
			SFParameteri** params,int paramsLength);
	
    ~SFPipelineGrid();
    
	int getN();
    
	SFParameteri** getParameters();

	int getGridSize();
	
	int size();
	
	bool isTriangular();
	
	short getParameterType(int parameterIndex);
};

}


#endif /* defined(SFPipelineGrid__) */
