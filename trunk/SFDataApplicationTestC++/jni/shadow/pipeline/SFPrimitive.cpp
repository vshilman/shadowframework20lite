
#include "SFPrimitive.h"

#include "SFMemory.h"

namespace sf{

	SFPrimitive::SFPrimitive() {
		this->blocksSize=0;

		this->gridBlocksIndex=0;
		this->indicesPositions=0;
		this->indicesCount=0;
		this->indicesSizes=0;
		this->types=0;
		this->gridModel=0;
	}

	SFPrimitive::SFPrimitive(string name,SFGridModel* gridModel):SFProgramModule(name) {
		this->blocksSize=0;

		this->gridBlocksIndex=0;
		this->indicesPositions=0;
		this->indicesCount=0;
		this->indicesSizes=0;
		this->types=0;
		this->gridModel=gridModel;
	}

	SFPrimitive::~SFPrimitive() {
        dealloc(indicesPositions);
        dealloc(indicesSizes);
        //for(unsigned int i=0;i<blocks.size();i++){
        //    delete blocks[i];
        //}
        for(unsigned int i=0;i<grids.size();i++){
            dealloc(grids[i]);
        }
        dealloc(gridBlocksIndex);
        dealloc(types);
	}

	void SFPrimitive::setPrimitiveElements(vector<SFPrimitiveBlock*> blocks,vector<SFProgramComponent*> components){
		this->blocks=blocks;
		this->components=components;
		generateGridInstances();
	}


	void SFPrimitive::generateGridInstances(){
		/*C++:
		 * if(gridInstances!=null)
		 * 		delete gridInstances;
		 * */
		vector<int> gridBlocksIndex;

		vector<short> types;
		for (unsigned int i = 0; i < blocks.size(); i++) {
			vector<SFPipelineGrid*> grid=getComponents()[i]->getGrid();
			for (unsigned int j=0;j<grid.size();j++){
            //for (SFPipelineGrid sfPipelineGridInstance : grid) {
				grids.push_back(grid[j]);
				gridBlocksIndex.push_back(i);
				short type=grid[j]->getParameterType(0);
				if(type==SFParameteri::GLOBAL_GENERIC)
					type=blocks[i]->getType();
				types.push_back(type);
			}
		}
		this->gridBlocksIndex=new int[gridBlocksIndex.size()];
		for (unsigned int i = 0; i < gridBlocksIndex.size(); i++) {
			this->gridBlocksIndex[i]=gridBlocksIndex[i];
		}
		this->types=new short[types.size()];
		for (unsigned int i = 0; i < types.size(); i++) {
			this->types[i]=types[i];
		}
		generateIndicesSizes();
        gridBlocksIndex.clear();
        types.clear();
	}

	/**
	 * @return -1 if there is no Txo Block
	 */
	int SFPrimitive::findBlockPosition(SFPrimitiveBlock* block){
		for (unsigned int i = 0; i < blocks.size(); i++) {
			if(blocks[i]==block){
				return i;
			}
		}
		return -1;
	}

	vector<SFProgramComponent*> SFPrimitive::getComponents(){
		return components;
	}

	vector<SFPipelineGrid*> SFPrimitive::getGrids() {
		return grids;
	}

	SFGridModel* SFPrimitive::getGridModel() {
		return gridModel;
	}

	int SFPrimitive::getGridsCount(){
		return grids.size();
	}

	SFPrimitiveBlock* SFPrimitive::getBlock(int gridIndex){
		return blocks[gridBlocksIndex[gridIndex]];
	}

	int SFPrimitive::getBlocksSize(){
		return blocksSize;
	}

	SFPipelineGrid* SFPrimitive::getGrid(int gridIndex){
		return grids[gridIndex];
	}

	short SFPrimitive::getType(int gridIndex){
		return types[gridIndex];
	}

	int SFPrimitive::getGridBlocksIndex(int gridIndex){
		return gridBlocksIndex[gridIndex];
	}

	vector<SFPrimitiveBlock*> SFPrimitive::getBlocks() {
		return blocks;
	}

	SFPrimitiveBlock* SFPrimitive::getBlock(SFPipelineRegister reg) {
		string registerName=reg.getName();
		const char* rName=registerName.c_str();

		SFPrimitiveBlock* block=SFPrimitiveBlock::POSITION;
		if(rName[0]=='N'){
			block=SFPrimitiveBlock::NORMAL;
		}else if(registerName.length()>1 && rName[0]=='T' && rName[1]=='x'){
			block=SFPrimitiveBlock::TXO;
		}else if(registerName.length()>1 && rName[0]=='d' && rName[1]=='u'){
			block=SFPrimitiveBlock::DU;
		}else if(registerName.length()>1 && rName[0]=='d' && rName[1]=='v'){
			block=SFPrimitiveBlock::DV;
		}
		return block;
	}

	int* SFPrimitive::getIndicesPositions() {
		return indicesPositions;
	}

	int SFPrimitive::getIndicesCount() {
		return indicesCount;
	}

	int* SFPrimitive::getIndicesSizes() {
		return indicesSizes;
	}

	bool SFPrimitive::isQuad(){
		return gridModel->getGridSize==SFGridModel_Quad.getGridSize;
	}

	void SFPrimitive::generateIndicesSizes() {
		indicesPositions=new int[this->grids.size()];
		indicesSizes=new int[this->grids.size()];
		indicesCount=0;
        cout << "generateIndicesSizes "<< this->grids.size() << endl;
		for (unsigned int i = 0; i < this->grids.size(); i++) {
			indicesPositions[i]=indicesCount;
			indicesSizes[i]=this->grids[i]->getGridSize();
			indicesCount+=indicesSizes[i];
		}
	}


	SFPrimitive* SFPrimitive::getConstructionPrimitive(){
		SFPrimitive* primitive=new SFPrimitive("",this->gridModel);

		for (unsigned int i = 0; i < blocks.size(); i++) {
			if(blocks[i]==SFPrimitiveBlock::POSITION){
				vector<SFProgramComponent*> components;//={this->components[i]};
				components.push_back(this->components[i]);
				vector<SFPrimitiveBlock*> blocks;
                blocks.push_back(SFPrimitiveBlock::UV);
				primitive->setPrimitiveElements(blocks, components);
			}
		}

		return primitive;
	}
}
