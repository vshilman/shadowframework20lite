//
//  SFPrimitiveBlock.cpp
//  
//
//  Created by Alessandro Martinelli on 18/10/12.
//  Copyright (c) 2012 Alessandro Martinelli. All rights reserved.
//

#include "SFPrimitiveBlock.h"


namespace sf{

SFPrimitiveBlock* SFPrimitiveBlock::POSITION/*(SFPipelineRegister::getFromName("P"))*/;
SFPrimitiveBlock* SFPrimitiveBlock::NORMAL;
SFPrimitiveBlock* SFPrimitiveBlock::DU;
SFPrimitiveBlock* SFPrimitiveBlock::DV;
SFPrimitiveBlock* SFPrimitiveBlock::TXO;
SFPrimitiveBlock* SFPrimitiveBlock::UV;
SFPrimitiveBlock* SFPrimitiveBlock::UVP;

SFPrimitiveBlock** SFPrimitiveBlock::values;

int SFPrimitiveBlock::initialized=0;

	SFPrimitiveBlock::SFPrimitiveBlock() {
		this->reg = 0;
        this->ordinal=0;
	}

    SFPrimitiveBlock::SFPrimitiveBlock(SFPipelineRegister* reg,int ordinal) {
		this->reg = reg;
        this->ordinal=ordinal;
	}
    
    void SFPrimitiveBlock::init(){
        if(initialized==0){
            POSITION=new SFPrimitiveBlock(SFPipelineRegister::getFromName("P"),0);
            NORMAL=new SFPrimitiveBlock(SFPipelineRegister::getFromName("N"),1);
            DU=new SFPrimitiveBlock(SFPipelineRegister::getFromName("du"),2);
            DV=new SFPrimitiveBlock(SFPipelineRegister::getFromName("dv"),3);
            TXO=new SFPrimitiveBlock(SFPipelineRegister::getFromName("Tx0"),4);
            UV=new SFPrimitiveBlock(SFPipelineRegister::getFromName("uv"),5);
            UVP=new SFPrimitiveBlock(SFPipelineRegister::getFromName("uvP"),6);
            values=new SFPrimitiveBlock*[7];
            values[0]=POSITION;
            values[1]=NORMAL;
            values[2]=DU;
            values[3]=DV;
            values[4]=TXO;
            values[5]=UV;
            values[6]=UVP;
            initialized=1;
        }
    }


    SFPipelineRegister* SFPrimitiveBlock::getRegister(){
		return reg;
	}
	
	short SFPrimitiveBlock::getType(){
        return reg->getType();
	}
	
	SFPrimitiveBlock* SFPrimitiveBlock::getBlock(SFPipelineRegister* reg){
        init();
		SFPrimitiveBlock** blocks=SFPrimitiveBlock::values;
		for (int i = 0; i < VALUES_SIZE; i++) {
			if(blocks[i]->getRegister()==reg){
				return blocks[i];
			}
		}
		return POSITION;
	}

    map<string,SFPrimitiveBlock*> SFPrimitiveBlock::blocks;

    SFPrimitiveBlock* SFPrimitiveBlock::valueOf(string name){
        if(blocks.size()==0){
            blocks["POSITION"]=SFPrimitiveBlock::POSITION;
            blocks["NORMAL"]=SFPrimitiveBlock::NORMAL;
            blocks["TXO"]=SFPrimitiveBlock::TXO;
            blocks["UV"]=SFPrimitiveBlock::UV;
            blocks["UVP"]=SFPrimitiveBlock::UVP;
            blocks["DU"]=SFPrimitiveBlock::DU;
            blocks["DV"]=SFPrimitiveBlock::DV;
        }
        return blocks[name];
    }
    
}
