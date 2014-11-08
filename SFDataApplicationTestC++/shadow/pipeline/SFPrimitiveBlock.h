
#ifndef SFPrimitiveBlock_H
#define SFPrimitiveBlock_H

#include "parameters/SFPipelineRegister.h"



namespace sf{

class SFPrimitiveBlock {

private:
	SFPipelineRegister* reg;
    int ordinal;
    static int initialized;
    static map<string,SFPrimitiveBlock*> blocks;
    
    SFPrimitiveBlock(SFPipelineRegister* reg,int ordinal);
    
    //no need to destroy a register, will be destroyed by SFPipeline
    
    
    static void destroy(){
    
    }
    
public:
    SFPrimitiveBlock();
    
    static SFPrimitiveBlock* POSITION/*(SFPipelineRegister::getFromName("P"))*/;
	static SFPrimitiveBlock* NORMAL;
	static SFPrimitiveBlock* DU;
	static SFPrimitiveBlock* DV;
	static SFPrimitiveBlock* TXO;
	static SFPrimitiveBlock* UV;
	static SFPrimitiveBlock* UVP;
    
	static SFPrimitiveBlock** values;
    static const int VALUES_SIZE=7;
    
    static void init();
    
    SFPipelineRegister* getRegister();
	
	short getType();
	
	static SFPrimitiveBlock* getBlock(SFPipelineRegister* reg);
    
	int getIndex();
    
	static SFPrimitiveBlock* getBlock(int index);
    
    static SFPrimitiveBlock* valueOf(string name);
};



/*
	private SFPrimitiveBlock(SFPipelineRegister register) {
		this.register = register;
	}
	
	SFPipelineRegister getRegister(){
		return register;
	}
	
	short getType(){
		return register.getType();
	}
	
	static SFPrimitiveBlock getBlock(SFPipelineRegister register){
		SFPrimitiveBlock[] blocks=SFPrimitiveBlock.values();
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i].getRegister()==register){
				return blocks[i];
			}
		}
		return POSITION;
	}
    
	int getIndex() {
		return ordinal();
	}
    
	static SFPrimitiveBlock getBlock(int index){
		return values()[index];
	}
};*/

}

#endif /* defined(SFPrimitiveBlock__) */
