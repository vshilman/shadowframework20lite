//
//  SFPrimitive.h
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#ifndef SFPrimitive_H
#define SFPrimitive_H

#include "SFProgramModule.h"
#include "SFPrimitiveBlock.h"

namespace sf{

class SFPrimitive : public SFProgramModule{
    
private:
	int* indicesPositions;
	int* indicesSizes;
	int indicesCount;
	SFGridModel* gridModel;
	
	vector<SFPrimitiveBlock*> blocks;
    int blocksSize;
	vector<SFPipelineGrid*> grids;
	int* gridBlocksIndex;
	short* types;
	
public:
	SFPrimitive();
	
	SFPrimitive(string name,SFGridModel* gridModel);
    
    ~SFPrimitive();
	
	void setPrimitiveElements(vector<SFPrimitiveBlock*> blocks,vector<SFProgramComponent*> components);
	
	void generateGridInstances();
	
	/**
	 * @return -1 if there is no Txo Block
	 */
	int findBlockPosition(SFPrimitiveBlock* block);
	
	vector<SFProgramComponent*> getComponents();
	
	vector<SFPipelineGrid*> getGrids();
	
	SFGridModel* getGridModel();
    
	int getGridsCount();
	
	SFPrimitiveBlock* getBlock(int gridIndex);

	int getBlocksSize();
    
	SFPipelineGrid* getGrid(int gridIndex);
    
	short getType(int gridIndex);
	
	int getGridBlocksIndex(int gridIndex);
	
	vector<SFPrimitiveBlock*> getBlocks() ;
    
	SFPrimitiveBlock* getBlock(SFPipelineRegister reg);
	
	int* getIndicesPositions();
	
	int getIndicesCount();
	
	int* getIndicesSizes();
	
	bool isQuad();
    
	void generateIndicesSizes();
	
	SFPrimitive* getConstructionPrimitive();
};

}

#endif /* defined(SFPrimitive__) */
